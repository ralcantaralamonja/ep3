package pe.isil.Saturno_1431.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pe.isil.Saturno_1431.model.Usuario;
import pe.isil.Saturno_1431.repository.UsuarioRepository;
import pe.isil.Saturno_1431.security.AppUserDetails;

import java.util.Optional;

@Controller
@RequestMapping("/updatepassword")
public class NuevoPassword {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("")
    public String nuevo() {
        return "nuevopasword";
    }

    @PostMapping("/guardar")
    public String guardarPassword(
            @RequestParam String actualpassword,
            @RequestParam String nuevopassword,
            @RequestParam String repitepassword,
            Model model
    ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AppUserDetails appUserDetails = (AppUserDetails) authentication.getPrincipal();
        String currentPassword = appUserDetails.getPassword();
        int ok = 0;

        // Validar la contraseña actual
        if (!passwordEncoder.matches(actualpassword, currentPassword) || actualpassword.isEmpty()) {
            ok++;
            model.addAttribute("errorActual", "La clave ingresada no es correcta o está vacía");
            return "nuevopasword";
        }

        // Validar que las contraseñas nuevas coincidan y no estén vacías
        if (!nuevopassword.equals(repitepassword) || nuevopassword.isEmpty()) {
            ok++;
            model.addAttribute("errorNueva", "Las claves nuevas no coinciden o están vacías");
            return "nuevopasword";
        }

        // Obtener el usuario de la base de datos
        Optional<Usuario> optionalUsuario = usuarioRepository.findById(appUserDetails.getId());

        // Verificar si el usuario existe
        if (optionalUsuario.isPresent()) {
            // Extraer el objeto Usuario del Optional
            Usuario usuario = optionalUsuario.get();

            // Actualizar la contraseña del usuario solo si las nuevas contraseñas no están vacías
            if (!nuevopassword.isEmpty()) {
                usuario.setPassword(passwordEncoder.encode(nuevopassword));
            }

            // Guardar la entidad actualizada en la base de datos
            //usuarioRepository.save(usuario);

            optionalUsuario.ifPresent(usuarioExistente -> {
                // Log para verificar si llega a este punto
                System.out.println("Dentro de ifPresent");

                usuarioExistente.setPassword(passwordEncoder.encode(nuevopassword));

                // Log para verificar el estado de la entidad antes de guardar
                System.out.println("Antes de guardar: " + usuarioExistente);

                // Guardar la entidad actualizada en la base de datos
                usuarioRepository.save(usuarioExistente);

                // Log después de guardar para verificar si se ha guardado correctamente
                System.out.println("Después de guardar: " + usuarioExistente);
            });

            // Resto del código después de guardar la contraseña (si es necesario)
        } else {
            // Manejar el caso en el que el usuario no se encuentra
            return "redirect:/error";  // Puedes redirigir a una página de error o hacer algo más
        }

        System.out.println(ok);
        return "redirect:/";
    }


}

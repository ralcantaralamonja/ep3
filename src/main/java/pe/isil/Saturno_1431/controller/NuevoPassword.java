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
import pe.isil.Saturno_1431.repository.UsuarioRepository;
import pe.isil.Saturno_1431.security.AppUserDetails;

@Controller
@RequestMapping("/updatepassword")
public class NuevoPassword {

    @Autowired
    private PasswordEncoder passwordEncoder;

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
            model.addAttribute("errorActual", "La clave ingresada no es correcta o vacia");
            return "nuevopasword";
        }

        // Validar que las contraseñas nuevas coincidan
        if (!nuevopassword.equals(repitepassword) || nuevopassword.isEmpty()) {
            ok++;
            model.addAttribute("errorNueva", "Las claves nuevas no coinciden o vacias");
            return "nuevopasword";
        }

        System.out.println(ok);
        return "redirect:/updatepassword";
    }
}

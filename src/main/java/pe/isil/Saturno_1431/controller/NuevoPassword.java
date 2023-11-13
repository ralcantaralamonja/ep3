package pe.isil.Saturno_1431.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
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
    public String Nuevo() {
        return "nuevopasword";
    }

    @PostMapping("/guardar")
    public String guardarPassword(@RequestParam String actualpassword) {
        // Obtener la autenticación actual
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Obtener la instancia de AppUserDetails del principal
        AppUserDetails appUserDetails = (AppUserDetails) authentication.getPrincipal();

        // Obtener la contraseña actual del usuario
        String currentPassword = appUserDetails.getPassword();

        System.out.println("Pass ingresada :"+ actualpassword);
        System.out.println("Pass actual :"+ currentPassword);

        // Imprimir la contraseña actual del usuario
        if (passwordEncoder.matches(actualpassword, currentPassword)) {
            System.out.println("Coinciden");
            // La contraseña actual es correcta
            // Puedes realizar acciones adicionales aquí

            // Redirigir a una página de éxito o mostrar un mensaje de éxito
            return "redirect:/updatepassword";
        } else {
            // La contraseña actual no es correcta, mostrar un mensaje de error
            System.out.println("No coinciden");
            return "redirect:/updatepassword";
        }


        // Puedes realizar acciones adicionales según tu lógica

     }
}

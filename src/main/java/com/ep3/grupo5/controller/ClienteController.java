package com.ep3.grupo5.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@Controller
@RequestMapping("/cliente")
public class ClienteController {

























    @GetMapping("/editar")
    public String editar(@RequestParam("id") Long id, Model model){
        model.addAttribute("alumno", clienteService.obtenerPorId(id));
        return "cliente/editar";
    }

    @DeleteMapping("/eliminar")
    public String eliminar(@ModelAttribute("id") Long id){
        clienteService.eliminar(id);
        return "redirect:/cliente";
    }

}

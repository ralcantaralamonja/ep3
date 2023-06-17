package com.ep3.grupo5.controller;

import com.ep3.grupo5.entity.Cliente;
import com.ep3.grupo5.service.ClienteService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@Controller
@RequestMapping("/cliente")
public class ClienteController {

    private final ClienteService clienteService;

    @GetMapping("")
    public String index(Model model){
        model.addAttribute("clientes", clienteService.obtenerTodos());
        return "/cliente/index";
    }

    @PostMapping("")
    public String registrar(@ModelAttribute("cliente")Cliente cliente){
        clienteService.registrar(cliente);
        return "redirect:/cliente";
    }

    @GetMapping("/editar")
    public String editar(@RequestParam("id") Long id, Model model){
        model.addAttribute("cliente", clienteService.obtenerporId(id));
        return "/cliente/editar";
    }

    @PostMapping("/editar")
    public String actualizar(@ModelAttribute("cliente") Cliente cliente){
        clienteService.registrar(cliente);
        return "redirect:/cliente";
    }

    @DeleteMapping("/eliminar")
    public String eliminar(@ModelAttribute("id") Long id){
        clienteService.eliminar(id);
        return "redirect:/cliente";
    }

}

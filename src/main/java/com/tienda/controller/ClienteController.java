package com.tienda.controller;

import com.tienda.domain.Cliente;
import com.tienda.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

 @GetMapping("/cliente/listado")
    public String inicio(Model model) {
        var clientes=clienteService.getClientes();
        
        var limiteTotal=0;
        for (var c: clientes) {
            limiteTotal+=c.getCredito().getLimite();
        }
        model.addAttribute("limiteTotal",limiteTotal);
        model.addAttribute("totalClientes",clientes.size());
        
        model.addAttribute("clientes",clientes);
        return "/cliente/listado";
    }

    @GetMapping("/cliente/nuevo")
    public String nuevoCliente(Cliente cliente) {
        return "/cliente/modificar";
    }

    @GetMapping("/cliente/buscar")
    public String buscarClienteMain(Cliente cliente) {
        return "/cliente/buscar";
    }

    @GetMapping("/cliente/buscarPorApellidos")
    public String buscarCliente(Model model, String apellidos) {
//        var clientes = clienteService.getPorApellidos("Castro Mora");
        var clientes = clienteService.getPorApellidos(apellidos.toLowerCase());
        model.addAttribute("clientes", clientes);
        return "/cliente/buscarPorApellidos";
    }

    @PostMapping("/cliente/guardar")
    public String guardarCliente(Cliente cliente) {
        clienteService.save(cliente);
        return "redirect:/cliente/listado";
    }

    @GetMapping("/cliente/modificar/{idCliente}")
    public String modificarCliente(Cliente cliente, Model model) {
        cliente = clienteService.getCliente(cliente);
        model.addAttribute("cliente", cliente);
        return "/cliente/modificar";
    }

    @GetMapping("/eliminarCliente/{idCliente}")
    public String elimnarCliente(Cliente cliente) {
        clienteService.delete(cliente);
        return "redirect:/cliente/listado";
    }

}

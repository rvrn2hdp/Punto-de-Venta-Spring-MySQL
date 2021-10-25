package com.analistas.pdv.controller;

import com.analistas.pdv.model.entities.Cliente;
import com.analistas.pdv.model.service.IClienteService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;



@Controller
@RequestMapping("/clientes")
@SessionAttributes("cliente")
public class ClienteController {
    
    @Autowired
    IClienteService clienteService;
    
    @GetMapping("/listado")
    public String listarClientes(Model model) {
    
        model.addAttribute("titulo", "Listado de Clientes");
        model.addAttribute("clientes", clienteService.buscarTodos());
        
        return "clientes/list";
    }
    
    @GetMapping("/nuevo")
    public String nuevoCliente(Model model) {
        model.addAttribute("titulo", "Nuevo Cliente");
        model.addAttribute("subtitulo", "Completar los Campos");
        model.addAttribute("cliente", new Cliente());
        
        return "clientes/clienteForm";
    }
    
    @GetMapping("/editar/{id}")
    public String edidarCliente(@PathVariable("id") long id, Model model) {
        
        Cliente cliente = (Cliente) clienteService.buscarPorId(id);
        
        model.addAttribute("cliente", cliente);
        
        return "clientes/clienteForm";
    }
    
    @PostMapping("/guardar")
    public String guardarCliente(@Valid Cliente cliente, BindingResult result,
            Model model, RedirectAttributes redirect) {
        
        //Verificar errores en los atributos:
        if(result.hasErrors()){
            model.addAttribute("danger", "Datos erróneos...");
            model.addAttribute("subtitulo", "Corrija los errores");
            return "clientes/clienteForm";
        }
        
        if (cliente.getId() == null || cliente.getId() == 0) {
            redirect.addFlashAttribute("success", "Cliente agregado con exito.");
        } else {
            redirect.addFlashAttribute("warning", "Cliente modificado con exito.");
        }
        
        clienteService.guardar(cliente);
        
        return "redirect:/clientes/listado";
    }
    
    @GetMapping("/delete/{id}")
    public String borrarCliente(@PathVariable("id") long id, RedirectAttributes redirect) {
        Cliente cliente = (Cliente) clienteService.buscarPorId(id);
        
        redirect.addFlashAttribute("danger", "Cliente Eliminado.");
        clienteService.borrarPorId(id);
        
        return "redirect:/clientes/listado";
    }
}

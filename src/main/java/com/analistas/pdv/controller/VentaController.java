/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.analistas.pdv.controller;

import com.analistas.pdv.model.entities.Venta;
import com.analistas.pdv.model.service.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author pcc
 */

@Controller
@RequestMapping("/ventas")
public class VentaController {
    
    @Autowired
    IClienteService clienteService;
    
    @GetMapping("/listado")
    public String listarVentas() {
    
        return "ventas/list";
    }
    
    @GetMapping("/nueva")
    public String nuevaVenta(Model model) {
        
        Venta venta = new Venta();
        
        model.addAttribute("venta", venta);
        model.addAttribute("clientes", clienteService.buscarTodos());
        
        return "ventas/form";
    }
}

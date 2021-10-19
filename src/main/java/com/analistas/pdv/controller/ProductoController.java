/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.analistas.pdv.controller;

import com.analistas.pdv.model.service.IProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author ander
 */
@Controller
@RequestMapping("/productos")
public class ProductoController {
    
    @Autowired
    IProductoService productoService;
    
    @GetMapping("/listado")
    public String listarProductos(Model model) {
    
        model.addAttribute("productos", productoService.buscarTodos());
        
        return "productos/list";
    }
}

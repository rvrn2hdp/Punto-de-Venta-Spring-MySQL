/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.analistas.pdv.controller;

import com.analistas.pdv.model.entities.Producto;
import com.analistas.pdv.model.entities.Venta;
import com.analistas.pdv.model.service.IClienteService;
import com.analistas.pdv.model.service.IProductoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author pcc
 */

@Controller
@RequestMapping("/ventas")
public class VentaController {
    
    @Autowired
    IClienteService clienteService;
    
    @Autowired
    IProductoService productoService;
    
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
    
    @GetMapping(value="/buscar-productos/{criterio}", produces = { "application/json" })
    public @ResponseBody List<Producto> cargarProductos(@PathVariable("criterio")String criterio) {
        return productoService.buscarPor(criterio);
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.analistas.pdv.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author pcc
 */

@Controller
@RequestMapping("/ventas")
public class VentaController {
    
    @GetMapping("/listado")
    public String listarVentas() {
    
        return "ventas/list";
    }
}

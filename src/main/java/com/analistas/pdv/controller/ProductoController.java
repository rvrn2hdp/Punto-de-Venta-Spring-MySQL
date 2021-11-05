/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.analistas.pdv.controller;

import com.analistas.pdv.model.entities.Producto;
import com.analistas.pdv.model.service.IProductoService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author ander
 */
@Controller
@RequestMapping("/productos")
@SessionAttributes("producto")
public class ProductoController {

    @Autowired
    IProductoService productoService;

    @GetMapping("/listado")
    public String listarProductos(Model model) {

        model.addAttribute("titulo", "Listado de Productos");
        model.addAttribute("productos", productoService.buscarTodos());

        return "productos/list";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("titulo", "Nuevo Producto");
        model.addAttribute("tituloForm", "Completar los Campos");
        model.addAttribute("producto", new Producto());

        return "productos/productoForm";
    }

    @GetMapping("/editar/{id}")
    public String edidar(@PathVariable("id") long id, Model model) {

        Producto producto = (Producto) productoService.buscarPorId(id);

        model.addAttribute("producto", producto);

        return "productos/productoForm";
    }

    @PostMapping("/guardar")
    public String guardar(@Valid Producto producto, BindingResult result, Model model, RedirectAttributes redirect) {

        //Verificar errores en los atributos:
        if(result.hasErrors()) {
            model.addAttribute("danger", "Datos erróneos...");
            model.addAttribute("subtitulo", "Corrija los errores");
            return "productos/productoForm";
        }
        
        if (producto.getId() == null || producto.getId() == 0) {
            redirect.addFlashAttribute("success", "Producto añadido con éxito...");
        } else {
            redirect.addFlashAttribute("warning", "Producto modificado con éxito...");
        }
        productoService.guardar(producto);

        return "redirect:/productos/listado";
    }

    @GetMapping("/delete/{id}")
    public String borrar(@PathVariable("id") long id, RedirectAttributes redirect) {
        Producto producto = (Producto) productoService.buscarPorId(id);
        
        redirect.addFlashAttribute("danger", "Producto eliminado...");
        productoService.borrarPorId(id);

        return "redirect:/productos/listado";
    }
}

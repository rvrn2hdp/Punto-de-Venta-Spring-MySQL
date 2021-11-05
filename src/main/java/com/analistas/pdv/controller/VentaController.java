/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.analistas.pdv.controller;

import com.analistas.pdv.model.entities.LineaVenta;
import com.analistas.pdv.model.entities.Producto;
import com.analistas.pdv.model.entities.Venta;
import com.analistas.pdv.model.service.IClienteService;
import com.analistas.pdv.model.service.IProductoService;
import com.analistas.pdv.model.service.IVentaService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author pcc
 */

@Controller
@RequestMapping("/ventas")
@SessionAttributes("venta")
public class VentaController {
    
    @Autowired
    IClienteService clienteService;
    
    @Autowired
    IProductoService productoService;
    
    @Autowired
    IVentaService ventaService;
    
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
    
    @PostMapping("/guardar")
    public String guardarVenta(
            @Valid Venta venta,
            BindingResult result,
            @RequestParam(name = "item_id[]", required = true) List<String> itemId,
            @RequestParam(name = "cantidad[]", required = true) List<String> cantidad,
            Model model, RedirectAttributes flash, SessionStatus status
    ) {
    
        //Verificar si hay errores...
        if(result.hasErrors()) {
            model.addAttribute("error", "Corrija los errores");
            return "ventas/form";
        }
        
        if(itemId == null || itemId.size() == 1) {
             model.addAttribute("warning", "Añadir productos a la venta");
            return "ventas/form";
        }
        
        //Si no hay errores...
        LineaVenta linea;
        Producto producto;
        
        //Crear las lineas de la venta...
        for(int i = 1; i < itemId.size(); i++) {
            linea = new LineaVenta();
            Long id = Long.parseLong(itemId.get(i));
            producto = productoService.buscarPorId(id);
            
            linea.setProducto(producto);
            linea.setCantidad(Integer.parseInt(cantidad.get(i)));
            
            venta.addLineaVenta(linea);
        }
        
        //Guardar la venta:
        ventaService.guardar(venta);
        status.setComplete();
        flash.addFlashAttribute("success", "Venta registrada.");
        
        return "redirect:/ventas/nueva";
    }
    
    @GetMapping(value="/buscar-productos/{criterio}", produces = { "application/json" })
    public @ResponseBody List<Producto> cargarProductos(@PathVariable("criterio")String criterio) {
        return productoService.buscarPor(criterio);
    }
}

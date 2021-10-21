/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.analistas.pdv.model.service;

import com.analistas.pdv.model.entities.Producto;
import java.util.List;

/**
 *
 * @author ander
 */
public interface IProductoService {
    
    public List<Producto> buscarTodos();
    
    public List<Producto> buscarPor(String criterio);
    
    public Producto buscarPorId(Long id);
    
    public void guardar(Producto producto);
    
    public void borrarPorId(Long id);
}

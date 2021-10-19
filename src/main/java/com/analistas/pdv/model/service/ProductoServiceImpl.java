/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.analistas.pdv.model.service;

import com.analistas.pdv.model.entities.Producto;
import com.analistas.pdv.model.repository.IProductoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ander
 */
@Service
public class ProductoServiceImpl implements IProductoService {
    
    //Forma tradicional es:
    //IProductoRepository productoRepo = new IProductoRepository(); NO SE USARá
    
    //DI (Dependence Injection) (IoC)
    //Recordar el principio de Hollywood (no me llames...yo te llamaré)
    @Autowired
    IProductoRepository productoRepo;

    @Override
    @Transactional(readOnly = true)
    public List<Producto> buscarTodos() {
        return productoRepo.findAll();
    }

    @Override
    public Producto buscarPor(String criterio) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Producto buscarPorId(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional(readOnly = false)
    public void guardar(Producto producto) {
        productoRepo.save(producto);
    }

    @Override
    public void borrarPorId(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

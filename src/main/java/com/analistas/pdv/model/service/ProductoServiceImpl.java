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
 * @author pcc
 */

@Service
public class ProductoServiceImpl implements IProductoService {
    
    //forma tradicional es:
    //IProductoRepository ProductoRepo = new IProductoRepository(); No se usara

    //DI (Dependence Injection) (IoC)
    //Recordar el principio de Hollywood
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
    public void guardar(Producto producto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void borrarPorId(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

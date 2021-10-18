/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.analistas.pdv.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.analistas.pdv.model.entities.Producto;

/**
 *
 * @author pcc
 */
public interface IProductoRepository extends JpaRepository<Producto, Long> {
    
}

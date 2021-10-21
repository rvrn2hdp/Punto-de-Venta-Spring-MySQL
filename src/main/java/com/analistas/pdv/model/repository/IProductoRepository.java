/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.analistas.pdv.model.repository;

import com.analistas.pdv.model.entities.Producto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author ander
 */
public interface IProductoRepository extends JpaRepository<Producto, Long> {
    
    //select * 
    @Query("select p from Producto p where p.codigoBarras like %:criterio% or p.descripcion like %:criterio%"
            + " and p.activo = true")
    List<Producto> buscarPorCriterio(@Param("criterio") String criterio);
}

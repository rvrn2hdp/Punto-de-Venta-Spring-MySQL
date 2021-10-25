package com.analistas.pdv.model.repository;

import com.analistas.pdv.model.entities.Cliente;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author pcc
 */
public interface IClienteRepository extends JpaRepository<Cliente, Long> {
    
    @Query("select c from Cliente c where c.apellido like %:criterio%"
            + " and c.activo = true")
    List<Cliente> buscarClientePorCriterio(@Param("criterio") String criterio);
}

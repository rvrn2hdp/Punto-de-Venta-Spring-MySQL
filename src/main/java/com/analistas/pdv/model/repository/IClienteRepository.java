/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.analistas.pdv.model.repository;

import com.analistas.pdv.model.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author ander
 */
public interface IClienteRepository extends JpaRepository<Cliente, Long> {
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.analistas.pdv.model.service;

import com.analistas.pdv.model.entities.Cliente;
import com.analistas.pdv.model.repository.IClienteRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 *
 * @author pcc
 */
@Service
public class ClienteServiceImpl implements IClienteService {
    
    @Autowired
    IClienteRepository clienteRepo;

    @Override
    @Transactional(readOnly = true)
    public List<Cliente> buscarTodos() {
        return clienteRepo.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Cliente> buscarPor(String criterio) {
        return clienteRepo.buscarClientePorCriterio(criterio);
    }

    @Override
    @Transactional(readOnly = true)
    public Cliente buscarPorId(Long id) {
        return clienteRepo.findById(id).orElse(null);
    }

    @Override
    @Transactional(readOnly = false)
    public void guardar(Cliente cliente) {
        clienteRepo.save(cliente);
    }

    @Override
    public void borrarPorId(Long id) {
        clienteRepo.deleteById(id);
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.analistas.pdv.model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 *
 * @author ander
 */
@Entity   //Convertimos nuestra clase en una "CLASE DE ENTIDAD"
@Table(name="clientes")
public class Cliente {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "El apellido es requerido...")  //Not null
    @Size(max=30)  //varchar(65)
    private String apellido;
    
    @NotBlank(message = "El nombre es requerido...")  //Not null
    @Size(max=30)  
    private String nombre;
     
    @NotBlank(message = "El documento es requerido...")  //Not null
    @Size(max=10)  
    @Column(name = "dni")
    private String nroDocumento;
    
    @NotBlank(message = "El teléfono es requerido...")  //Not null
    @Size(max=20)  
    private String telefono;
    
    @Size(max=65)
    private String email;
    
    @Size(max=110)
    private String domicilio;
    
    @Size(max=200)
    private String nota;
    
    @Column(name = "act", columnDefinition = "bit default 1")
    private boolean activo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNroDocumento() {
        return nroDocumento;
    }

    public void setNroDocumento(String nroDocumento) {
        this.nroDocumento = nroDocumento;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    @Override
    public String toString() {
        return apellido + ", " + nombre + " - D.N.I: " + nroDocumento;
    }
    
    
}

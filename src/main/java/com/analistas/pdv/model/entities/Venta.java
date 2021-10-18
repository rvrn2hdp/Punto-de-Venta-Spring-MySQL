/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.analistas.pdv.model.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;


@Entity
@Table(name = "ventas")
public class Venta {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull(message = "la fecha y hora es requerida...")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm z")
    @Column(name = "fecha_hora")
    private LocalDateTime fechaHora;
    
    @Size(max=65)
    private String descripcion;
    
    @ManyToOne(fetch = FetchType.LAZY) //investigar mejor...
    @JoinColumn(name = "id_cliente") //clave Foranea
    private Cliente cliente;
    
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL,
            orphanRemoval = true)
    @JoinColumn(name = "id_venta")
    private List<LineaVenta> lineas;
    
    public Venta() {
        lineas = new ArrayList<>();
        descripcion = "NINGUNA";
        fechaHora = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<LineaVenta> getLineas() {
        return lineas;
    }

    public void setLineas(List<LineaVenta> lineas) {
        this.lineas = lineas;
    }
    
    public void addLineaVenta(LineaVenta linea) {
        lineas.add(linea);
    }
    
    public Double getTotal() {
        Double total = 0.0;
        
        for(LineaVenta ln : lineas) {
            total += ln.getSubtotal();
        }
        
        return total;
    }
}

package com.proyecto.capruso.Entity;

import java.time.LocalDateTime;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "Productos")
public class Producto {
    
    @Id
    private String codigo;
    private String descripcion;
    private Double costo;
    private Double venta;
    private int stock;

    @ManyToOne()
    @JoinColumn(name = "ruc_proveedor")
    private Proveedor proveedor;    
    private LocalDateTime fecharegistro;
}

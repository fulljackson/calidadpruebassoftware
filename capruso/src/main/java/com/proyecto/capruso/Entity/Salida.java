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
@Table(name = "Salidas")
public class Salida {

    @Id
    private String codigo;

    @ManyToOne()
    @JoinColumn(name = "dni_usuario")
    private Usuario usuario;

    @ManyToOne()
    @JoinColumn(name = "codigo_producto")
    private Producto producto;

    private int cantidad;
    private LocalDateTime fecharegistro;
}

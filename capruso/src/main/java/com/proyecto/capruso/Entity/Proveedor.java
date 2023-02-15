package com.proyecto.capruso.Entity;

import java.time.LocalDateTime;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "Proveedores")
public class Proveedor {
    
    @Id
    private String ruc;
    private String razon;
    private String celular;
    private String direccion;
    private LocalDateTime fecharegistro;
}
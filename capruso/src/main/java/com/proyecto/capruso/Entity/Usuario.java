package com.proyecto.capruso.Entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "Usuarios")
public class Usuario {
    
    @Id
    @Column(name = "dni")
    private String username;
    @Column(name = "contrasena")
    private String password;
    private String nombres;
    private String apellidos;
    private String celular;
    private String perfil;
    private LocalDateTime fecharegistro;
}

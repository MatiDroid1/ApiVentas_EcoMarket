package com.ventas.duoc.ecomarket.cl.Ventas.model;

import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "cupones")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cupon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cuponId;

    @Column(nullable = false, unique = true)
    private String codigo;

    private String tipo; // "porcentaje" o "monto"
    
    private Double descuento;

    @Column(name = "fecha_inicio")
    private LocalDate fechaInicio;

    @Column(name = "fecha_fin")
    private LocalDate fechaFin;

    private Boolean activo;
}

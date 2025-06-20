package com.ventas.duoc.ecomarket.cl.Ventas.model;

import java.time.LocalDate;

// ✅ Y USÁ ESTE:
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "ventas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ventaId;

    @Column(name = "pedido_id", nullable = false)
    private Long pedidoId;

    private Double total;
    private String metodoPago;
    private String estado;
    private LocalDate fechaVenta;
}
package com.ventas.duoc.ecomarket.cl.Ventas.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class VentaRequestDTO {
    private Long pedidoId;
    private String metodoPago;
    private String estado;
    private LocalDate fechaVenta;
    private String cuponCodigo;
}

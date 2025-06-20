package com.ventas.duoc.ecomarket.cl.Ventas.dto;
import lombok.Data;
import java.time.LocalDate;

@Data
public class VentaDetalleDTO {
    private Long ventaId;
    private Long pedidoId;
    private Long clienteId;
    private String nombreCliente;
    private Double total;
    private String metodoPago;
    private String estado;
    private LocalDate fechaVenta;
}
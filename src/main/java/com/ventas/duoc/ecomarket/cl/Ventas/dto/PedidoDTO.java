package com.ventas.duoc.ecomarket.cl.Ventas.dto;

import lombok.Data;

@Data
public class PedidoDTO {
    private Long id;
    private Long clienteId;
    private Double total;
}


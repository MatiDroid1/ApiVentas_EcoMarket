package com.ventas.duoc.ecomarket.cl.Ventas.service;

import org.springframework.http.HttpHeaders;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ventas.duoc.ecomarket.cl.Ventas.dto.PedidoDTO;
import com.ventas.duoc.ecomarket.cl.Ventas.dto.UsuarioDTO;
import com.ventas.duoc.ecomarket.cl.Ventas.dto.VentaDetalleDTO;
import com.ventas.duoc.ecomarket.cl.Ventas.model.Venta;
import com.ventas.duoc.ecomarket.cl.Ventas.repository.VentaRepository;

import org.springframework.http.HttpMethod;
import org.springframework.beans.factory.annotation.Value;

@Service
public class VentaService {

    @Autowired
    private VentaRepository repo;
    @Autowired
    private RestTemplate restTemplate;

    @Value("${url.api.usuarios}")
    private String urlUsuarios;

    @Value("${url.api.pedidos}")
    private String urlPedidos;

    @Value("${api.key.usuarios}")
    private String apiKeyUsuarios;

    @Value("${api.key.pedidos}")
    private String apiKeyPedidos;

    public List<VentaDetalleDTO> listarDetalle() {
        List<Venta> ventas = repo.findAll();
        List<VentaDetalleDTO> lista = new ArrayList<>();

        for (Venta v : ventas) {
            VentaDetalleDTO dto = new VentaDetalleDTO();
            dto.setVentaId(v.getVentaId());
            dto.setPedidoId(v.getPedidoId());
            dto.setMetodoPago(v.getMetodoPago());
            dto.setEstado(v.getEstado());
            dto.setFechaVenta(v.getFechaVenta());

            try {
                PedidoDTO pedido = restTemplate.exchange(
                        urlPedidos + "/" + v.getPedidoId(),
                        HttpMethod.GET,
                        new HttpEntity<>(createHeaders(apiKeyPedidos)),
                        PedidoDTO.class).getBody();

                if (pedido != null) {
                    dto.setTotal(pedido.getTotal());
                    dto.setClienteId(pedido.getClienteId());

                    UsuarioDTO usuario = restTemplate.exchange(
                            urlUsuarios + pedido.getClienteId(),
                            HttpMethod.GET,
                            new HttpEntity<>(createHeaders(apiKeyUsuarios)),
                            UsuarioDTO.class).getBody();

                    if (usuario != null) {
                        dto.setNombreCliente(usuario.getNombre());
                    }
                }
            } catch (Exception e) {
                dto.setNombreCliente("No disponible");
                dto.setTotal(0.0);
            }

            lista.add(dto);
        }

        return lista;
    }

    private HttpHeaders createHeaders(String apiKey) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-API-KEY", apiKey);
        return headers;
    }

    public List<Venta> listar() {
        return repo.findAll();
    }

    public Venta buscar(Long id) {
        return repo.findById(id).orElse(null);
    }

    public Venta guardar(Venta v) {
        v.setFechaVenta(LocalDate.now());
        return repo.save(v);
    }
}

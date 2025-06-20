package com.ventas.duoc.ecomarket.cl.Ventas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ventas.duoc.ecomarket.cl.Ventas.dto.VentaDetalleDTO;
import com.ventas.duoc.ecomarket.cl.Ventas.model.Venta;
import com.ventas.duoc.ecomarket.cl.Ventas.service.VentaService;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.RequestBody;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/ventas")
@Tag(name = "Ventas", description = "Operaciones relacionadas con las ventas")
@CrossOrigin
public class VentaController {

    @Autowired
    private VentaService service;

    @GetMapping
    @Operation(summary = "Listar todas las ventas")
    public ResponseEntity<List<Venta>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar venta por ID")
    public ResponseEntity<?> buscar(@PathVariable Long id) {
        Venta venta = service.buscar(id);
        return venta != null ? ResponseEntity.ok(venta) : ResponseEntity.notFound().build();
    }

    @PostMapping
    @Operation(summary = "crear venta")
public ResponseEntity<?> crear(@RequestBody Venta venta) {
    return ResponseEntity.status(HttpStatus.CREATED).body(service.guardar(venta));
}



    @GetMapping("/detalle")
    @Operation(summary = "Listado de ventas con información cruzada del cliente y total")
    public ResponseEntity<List<VentaDetalleDTO>> ventasConDetalles() {
        return ResponseEntity.ok(service.listarDetalle());
    }
}
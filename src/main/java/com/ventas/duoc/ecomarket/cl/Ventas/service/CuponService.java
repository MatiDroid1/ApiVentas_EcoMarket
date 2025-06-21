package com.ventas.duoc.ecomarket.cl.Ventas.service;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ventas.duoc.ecomarket.cl.Ventas.model.Cupon;
import com.ventas.duoc.ecomarket.cl.Ventas.repository.CuponRepository;

@Service
public class CuponService {
    @Autowired private CuponRepository cuponRepo;

    public Cupon validarCupon(String codigo) {
        Optional<Cupon> cuponOpt = cuponRepo.findByCodigoAndActivoTrue(codigo);
        if (cuponOpt.isPresent()) {
            Cupon cupon = cuponOpt.get();
            LocalDate hoy = LocalDate.now();
            if ( (hoy.isAfter(cupon.getFechaInicio()) || hoy.isEqual(cupon.getFechaInicio())) &&
                 (hoy.isBefore(cupon.getFechaFin()) || hoy.isEqual(cupon.getFechaFin())) ) {
                return cupon;
            }
        }
        return null; // cupón inválido
    }
}

package com.ventas.duoc.ecomarket.cl.Ventas.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ventas.duoc.ecomarket.cl.Ventas.model.Cupon;

public interface CuponRepository extends JpaRepository<Cupon, Long> {
    Optional<Cupon> findByCodigoAndActivoTrue(String codigo);
}

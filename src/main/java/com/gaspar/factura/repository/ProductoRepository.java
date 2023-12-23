package com.gaspar.factura.repository;

import com.gaspar.factura.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductoRepository extends JpaRepository<Producto,Integer> {

    Optional<Producto> findByCodigoA(String codigo);
}

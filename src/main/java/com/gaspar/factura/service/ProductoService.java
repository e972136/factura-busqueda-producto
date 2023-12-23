package com.gaspar.factura.service;

import com.gaspar.factura.entity.Producto;
import com.gaspar.factura.repository.ProductoRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductoService {

    private final ProductoRepository productoRepository;

    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    public Producto findByCodigoA(String codigo) {
        return productoRepository.findByCodigoA(codigo).orElse(null);
    }
}

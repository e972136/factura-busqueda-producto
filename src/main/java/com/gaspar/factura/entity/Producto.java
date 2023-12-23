package com.gaspar.factura.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "producto")
@Data
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
public class Producto {

    @Id
    @Column(name = "id_producto")
    Integer id;

    @Column(name = "codigo_a")
    String codigoA;

    String descripcion;

    @Column(name = "precio_venta")
    BigDecimal precioVenta;

    @Column(name = "precio_venta_1")
    BigDecimal precioVenta1;

    @Column(name = "precio_venta_2")
    BigDecimal precioVenta2;

    @Column(name = "precio_venta_3")
    BigDecimal precioVenta3;
}

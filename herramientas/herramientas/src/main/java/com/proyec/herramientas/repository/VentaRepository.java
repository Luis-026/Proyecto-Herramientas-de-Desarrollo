package com.proyec.herramientas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyec.herramientas.entity.Venta;

public interface VentaRepository extends JpaRepository<Venta, Long> {
}

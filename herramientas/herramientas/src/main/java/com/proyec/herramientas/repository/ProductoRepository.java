package com.proyec.herramientas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyec.herramientas.entity.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
}
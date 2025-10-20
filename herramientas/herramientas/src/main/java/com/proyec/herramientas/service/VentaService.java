package com.proyec.herramientas.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyec.herramientas.entity.Producto;
import com.proyec.herramientas.entity.Venta;
import com.proyec.herramientas.repository.ProductoRepository;
import com.proyec.herramientas.repository.VentaRepository;

@Service
public class VentaService {

    @Autowired
    private VentaRepository ventaRepository;

    @Autowired
    private ProductoRepository productoRepository;

    public List<Venta> listarVentas() {
        return ventaRepository.findAll();
    }

    public void realizarVenta(Long productoId, int cantidad) {
        Producto producto = productoRepository.findById(productoId).orElseThrow();

        if (producto.getStock() < cantidad) {
            throw new RuntimeException("Stock insuficiente");
        }

        // Descontar stock
        producto.setStock(producto.getStock() - cantidad);
        productoRepository.save(producto);

        // Registrar venta
        Venta venta = new Venta();
        venta.setProducto(producto);
        venta.setCantidad(cantidad);
        venta.setTotal(producto.getPrecio() * cantidad);
        venta.setFecha(LocalDateTime.now());

        ventaRepository.save(venta);
    }

}

package com.proyec.herramientas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.proyec.herramientas.service.ProductoService;
import com.proyec.herramientas.service.VentaService;

@Controller
@RequestMapping("/ventas")
public class VentaController {

    @Autowired
    private ProductoService productoService;

    @Autowired
    private VentaService ventaService;

    @GetMapping
    public String mostrarFormularioVenta(Model model) {
        model.addAttribute("productos", productoService.listarTodos());
        return "ventas";
    }

    @PostMapping("/realizar")
    public String realizarVenta(@RequestParam Long productoId,
                                @RequestParam int cantidad) {
        ventaService.realizarVenta(productoId, cantidad);
        return "redirect:/ventas";
    }
}
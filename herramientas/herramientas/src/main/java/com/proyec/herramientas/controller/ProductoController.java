package com.proyec.herramientas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.proyec.herramientas.entity.Categoria;
import com.proyec.herramientas.entity.Producto;
import com.proyec.herramientas.service.CategoriaService;
import com.proyec.herramientas.service.ProductoService;

@Controller
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    public String listarProductos(Model model) {
        List<Producto> productos = productoService.listarTodos();
        List<Categoria> categorias = categoriaService.listarTodas();
        model.addAttribute("productos", productos);
        model.addAttribute("categorias", categorias);
        return "productos";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Producto producto) {
        productoService.guardar(producto);
        return "redirect:/productos";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        productoService.eliminar(id);
        return "redirect:/productos";
    }

    @GetMapping("/activar/{id}")
    public String activar(@PathVariable Long id) {
        productoService.activar(id);
        return "redirect:/productos";
    }

    @GetMapping("/anular/{id}")
    public String anular(@PathVariable Long id) {
        productoService.anular(id);
        return "redirect:/productos";
    }

    @GetMapping("/editar/{id}")
    @ResponseBody
    public Producto editar(@PathVariable Long id) {
        return productoService.buscarPorId(id);
    }
}
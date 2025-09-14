package com.proyec.herramientas.controller;

import com.proyec.herramientas.entity.Usuario;
import com.proyec.herramientas.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public String listarUsuarios(Model model) {
        List<Usuario> lista = usuarioService.listarTodos();
        model.addAttribute("usuarios", lista);
        return "usuarios"; // busca usuarios.html en templates
    }
    @PostMapping("/guardar")
    public String guardar(@RequestParam(value = "id", required = false) Long id,
                          @RequestParam("usuario") String usuario,
                          @RequestParam("clave") String clave,
                          @RequestParam("rol") String rol,
                          @RequestParam(value = "activo", required = false) String activoFlag) {

        Usuario u;

        if (id != null) {
            u = usuarioService.buscarPorId(id);
            if (u == null) {
                u = new Usuario();
                u.setId(id);
            }
        } else {
            u = new Usuario();
        }

        u.setUsuario(usuario);
        u.setClave(clave);
        u.setRol(rol);
        u.setActivo(activoFlag != null);

        usuarioService.guardar(u);
        return "redirect:/usuarios";
    }



    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        usuarioService.eliminar(id);
        return "redirect:/usuarios";
    }

    @GetMapping("/activar/{id}")
    public String activar(@PathVariable Long id) {
        usuarioService.activar(id);
        return "redirect:/usuarios";
    }

    @GetMapping("/anular/{id}")
    public String anular(@PathVariable Long id) {
        usuarioService.anular(id);
        return "redirect:/usuarios";
    }
}

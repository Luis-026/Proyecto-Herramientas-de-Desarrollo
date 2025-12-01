package com.proyec.herramientas.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.proyec.herramientas.entity.Usuario;
import com.proyec.herramientas.repository.UsuarioRepository;

@Controller
public class AuthController {

  private final UsuarioRepository usuarioRepository;

  public AuthController(UsuarioRepository usuarioRepository) {
    this.usuarioRepository = usuarioRepository;
  }

  @GetMapping("/registro")
  public String mostrarFormularioRegistro() {
    return "registro";
  }

  @PostMapping("/registro")
  public String registrarUsuario(
      @RequestParam("usuario") String usuario,
      @RequestParam("clave") String clave,
      @RequestParam("rol") String rol,
      RedirectAttributes redirectAttributes) {

    Usuario nuevo = new Usuario();
    nuevo.setUsuario(usuario);
    nuevo.setClave(clave);
    nuevo.setRol(rol);
    nuevo.setActivo(true);

    usuarioRepository.save(nuevo);

    redirectAttributes.addFlashAttribute(
        "mensaje", "Usuario registrado correctamente. Ahora puede iniciar sesión.");

    return "redirect:/login";
  }
}
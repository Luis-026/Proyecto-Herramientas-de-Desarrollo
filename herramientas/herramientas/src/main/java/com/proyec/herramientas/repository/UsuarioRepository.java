package com.proyec.herramientas.repository;
import com.proyec.herramientas.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByUsuarioAndClave(String usuario, String clave);
}

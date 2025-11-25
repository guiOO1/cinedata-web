package com.cinedata.repository;

import com.cinedata.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByNickname(String nickname);
    Optional<Usuario> findByDadosEmail(String email);
    boolean existsByDadosEmail(String email);
    List<Usuario> findByAtivoTrue();
}
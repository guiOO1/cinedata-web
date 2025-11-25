package com.cinedata.repository;

import com.cinedata.model.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long> {
    List<Reserva> findBySessaoIdAndValidaTrue(Long sessaoId);
    List<Reserva> findByUsuarioIdAndValidaTrue(Long usuarioId);
    boolean existsBySessaoIdAndValidaTrue(Long sessaoId);
    long countBySessaoIdAndValidaTrue(Long sessaoId);
}
package com.cinedata.repository;

import com.cinedata.model.Sessao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface SessaoRepository extends JpaRepository<Sessao, Long> {
    List<Sessao> findByAtivaTrueAndDataHoraAfter(LocalDateTime dataHora);
    List<Sessao> findByFilmeIdAndAtivaTrue(Long filmeId);
    
    @Query("SELECT s FROM Sessao s WHERE s.dataHora BETWEEN :inicio AND :fim AND s.ativa = true")
    List<Sessao> findSessoesEntreDatas(LocalDateTime inicio, LocalDateTime fim);
}
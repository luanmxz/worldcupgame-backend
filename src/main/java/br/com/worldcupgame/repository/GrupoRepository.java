package br.com.worldcupgame.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.worldcupgame.model.Grupo;

public interface GrupoRepository extends JpaRepository<Grupo, Integer> {

}

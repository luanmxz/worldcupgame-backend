package br.com.worldcupgame.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.worldcupgame.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

	Set<Role> findRoleById(Long id);
}

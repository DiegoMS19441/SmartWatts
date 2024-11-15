package br.com.fiap.smartwatts.repositories;

import br.com.fiap.smartwatts.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Long> {

    Optional<Role> findByName(String nome);
}

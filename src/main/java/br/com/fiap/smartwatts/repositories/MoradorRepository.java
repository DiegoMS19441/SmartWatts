package br.com.fiap.smartwatts.repositories;

import br.com.fiap.smartwatts.model.Morador;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MoradorRepository extends JpaRepository<Morador, Long> {


}

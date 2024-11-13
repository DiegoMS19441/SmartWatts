package br.com.fiap.smartwattts.repositories;

import br.com.fiap.smartwattts.model.Morador;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MoradorRepository extends JpaRepository<Morador, Long> {

}

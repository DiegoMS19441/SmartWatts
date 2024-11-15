package br.com.fiap.smartwatts.repositories;

import br.com.fiap.smartwatts.model.Fatura;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FaturaRepository extends JpaRepository<Fatura, Long> {
}

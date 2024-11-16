package br.com.fiap.smartwatts.repositories;

import br.com.fiap.smartwatts.model.Bandeira;
import br.com.fiap.smartwatts.model.Fatura;
import br.com.fiap.smartwatts.model.Residencia;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
@TestPropertySource(locations = "classpath:application-test.properties")
class FaturaRepositoryTest {

    @Autowired
    private FaturaRepository faturaRepository;

    @Autowired
    private ResidenciaRepository residenciaRepository;

    @Test
    void testSalvarFaturaComDadosValidos() {
        // Criar residência válida
        Residencia residencia = new Residencia();
        residencia.setMoradores(4);
        residencia.setAndares(2);
        residenciaRepository.save(residencia);

        // Criar fatura com dados válidos
        Fatura fatura = new Fatura();
        fatura.setValor(150.0);  // Valor da fatura
        fatura.setKWh(350.0);    // Consumo em kWh
        fatura.setMesReferencia(LocalDate.of(2024, 11, 1));  // Mês da fatura
        fatura.setBandeira(Bandeira.VERMELHA);  // Bandeira tarifária
        fatura.setResidencia(residencia);  // Associar à residência criada

        // Salvar fatura
        Fatura faturaSalva = faturaRepository.save(fatura);

        // Verificar se a fatura foi salva corretamente
        assertNotNull(faturaSalva.getId());  // Verificar se o ID foi gerado
    }


}

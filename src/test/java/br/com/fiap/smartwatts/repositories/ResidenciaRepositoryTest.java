package br.com.fiap.smartwatts.repositories;

import br.com.fiap.smartwatts.model.Endereco;
import br.com.fiap.smartwatts.model.Residencia;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ActiveProfiles("test")
@DataJpaTest
@TestPropertySource(locations = "classpath:application-test.properties")
class ResidenciaRepositoryTest {

    @Autowired
    private ResidenciaRepository residenciaRepository;

    @Test
    void testSalvarERetornarResidencia() {
        Endereco endereco = new Endereco();
        endereco.setLogradouro("Rua Exemplo");
        endereco.setNumero("123");
        endereco.setComplemento("Apto 101");
        endereco.setCep("12345678");

        Residencia residencia = new Residencia();
        residencia.setMoradores(4);
        residencia.setAndares(2);
        residencia.setEndereco(endereco);

        residencia = residenciaRepository.save(residencia);

        assertNotNull(residencia.getId(), "A residência não foi salva corretamente");
        assertNotNull(residencia.getEndereco(), "O endereço da residência não foi salvo corretamente");
    }


}

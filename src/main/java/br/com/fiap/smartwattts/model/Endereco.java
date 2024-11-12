package br.com.fiap.smartwattts.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter@Setter
@Table(name = "TB_ENDERECO")
public class Endereco {

    @Id
    @GeneratedValue
    private Long id;

    private String logradouro;

    private String numero;

    private String complemento;

    private Estado estado;

    private String cep;

}

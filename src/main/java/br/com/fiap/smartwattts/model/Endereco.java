package br.com.fiap.smartwattts.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter@Setter
@Table(name = "TB_ENDERECO")
public class Endereco {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "logradouro")
    @NotBlank(message = "Verifique o campo")
    private String logradouro;

    @Column(name = "numero")
    @NotBlank(message = "Verifique o campo")
    private String numero;

    @Column(name = "complemento")
    @NotBlank(message = "Verifique o campo")
    private String complemento;

    @Column(name = "estado")
    private Estado estado;

    @Column(name = "cep")
    @NotBlank(message = "Verifique o campo")
    @Size(min = 8, max = 8, message = "Verifique a quantidade de caracteres")
    private String cep;

}

package br.com.fiap.smartwatts.model;

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

    @Column(name = "logradouro", nullable = false)
    @NotBlank(message = "Verifique o campo")
    @Size(min = 5, max = 90)
    private String logradouro;

    @Column(name = "numero", nullable = false)
    @NotBlank(message = "Verifique o campo")
    private String numero;

    @Column(name = "complemento", nullable = false)
    @NotBlank(message = "Verifique o campo")
    private String complemento;

    @Column(name = "cep", nullable = false)
    @NotBlank(message = "Verifique o campo")
    @Size(min = 8, max = 8, message = "Verifique a quantidade de caracteres")
    private String cep;

}

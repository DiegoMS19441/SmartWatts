package br.com.fiap.smartwatts.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter@Setter@Entity
@Table(name = "TB_MORADOR")
public class Morador {

    @Id
    @GeneratedValue
    @Column(name = "id_morador")
    private Long id;

    @Column(name = "nm_morador", nullable = false)
    @NotBlank(message = "Esse campo precisa de atenção")
    @Size(min = 3, max = 100)
    private String nome;

    @Column(name = "cpf_morador", unique = true, nullable = false)
    @NotBlank(message = "Esse campo precisa de atenção")
    @Size(min = 11, max = 11, message = "humm, parece que você forneceu numeros a menos ou a mais")
    private String cpf;

    @Column(name = "dt_nascimento", nullable = false)
    @Past
    private LocalDate dataNascimento;

    @ManyToOne
    @JoinColumn(name = "id_residencia", referencedColumnName = "id")
    private Residencia residencia;

}

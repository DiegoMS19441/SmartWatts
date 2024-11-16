package br.com.fiap.smartwatts.model;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "TB_RESIDENCIA")
@Getter@Setter
public class Residencia {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "qnt_moradores", nullable = false)
    @Min(value=0, message = "O Valor não pode ser menor que zero")
    @NotNull(message = "Por favor preencha o campo.")
    private Integer moradores;

    @Column(name = "andares", nullable = false)
    @Min(value=1, message = "Entrada inválida")
    @NotNull(message = "Por favor preencha o campo.")
    private Integer andares;


    @OneToMany(mappedBy = "residencia")
    private List<Fatura> faturas;

    @OneToMany(mappedBy = "residencia", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Post> posts;

    @Valid
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_endereco")
    private Endereco endereco;

}

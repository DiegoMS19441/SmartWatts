package br.com.fiap.smartwatts.model;

import jakarta.persistence.*;
import jakarta.validation.Valid;
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

    @Column(name = "qnt_moradores")
    private Integer moradores;

    @Column(name = "andares")
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

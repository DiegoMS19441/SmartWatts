package br.com.fiap.smartwattts.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "TB_RESIDENCIA")
@Getter@Setter
public class Residencia {

    @Id
    private Long id;

    @Column(name = "nm_rua")
    private String rua;

    @Column(name = "qnt_moradores")
    private Integer moradores;

    @Column(name = "andares")
    private Integer andares;


}

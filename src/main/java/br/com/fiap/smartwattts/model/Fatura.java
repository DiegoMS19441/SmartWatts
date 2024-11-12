package br.com.fiap.smartwattts.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter@Setter
@Table(name = "TB_FATURA")
public class Fatura {

    @Id@GeneratedValue
    @Column(name="id_fatura")
    private Long id;

    @Column(name = "valor_fatura")
    private Double valor;

    private Bandeira bandeira;
}

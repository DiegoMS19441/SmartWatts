package br.com.fiap.smartwattts.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
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
    @Min(value = 0, message = "Humm, não me parece certo um valor negativo")
    @NotNull(message = "O campo não pode ficar vazio")
    private Double valor;

    @Column(name = "bandeira_tarifaria")
    private Bandeira bandeira;
}

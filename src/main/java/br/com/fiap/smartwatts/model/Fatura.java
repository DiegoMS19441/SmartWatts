package br.com.fiap.smartwatts.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter@Setter
@Table(name = "TB_FATURA")
public class Fatura {

    @Id@GeneratedValue
    @Column(name="id_fatura")
    private Long id;

    @Column(name = "valor_fatura")
    @Min(value = 0, message = "Humm, não me parece certo um valor negativo.")
    @NotNull(message = "O campo não pode ficar vazio.")
    private Double valor;

    @Column(name="watts")
    @NotNull(message="O campo não pode ficar vazio.")
    @Min(value = 0, message = "Esse campo não aceita números negativos.")
    private Double kWh;

    @Column(name = "mes_fatura", nullable = false, unique = true)
    @NotNull(message = "Por favor preencha o campo.")
    private LocalDate mesReferencia;

    @Column(name = "bandeira_tarifaria")
    @NotNull(message = "Por favor selecione a bandeira vigente.")
    private Bandeira bandeira;

    @ManyToOne
    @JoinColumn(name = "id_residencia", referencedColumnName = "id")
    private Residencia residencia;
}

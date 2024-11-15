package br.com.fiap.smartwatts.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name="TB_POST")
@Getter
@Setter
public class Post {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "dt_post")
    private LocalDate dataPost = LocalDate.now();

    @Column(name = "msg_post")
    @NotBlank(message = "O campo não pode estar vazio.")
    private String mensagem;

    @ManyToOne
    @JoinColumn(name = "id_usuario", referencedColumnName = "id")
    private Usuario usuario;


    @ManyToOne
    @JoinColumn(name = "id_residencia", referencedColumnName = "id")
    private Residencia residencia;
}

package dev.java10x.CadastroDeNinjas.Missoes;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MissaoDTO {

    private Long id;
    private String nome;
    private String dificuldade;

    private LocalDateTime criadoEm;
    private LocalDateTime atualizadoEm;
}

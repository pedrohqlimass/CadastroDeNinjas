package dev.java10x.CadastroDeNinjas.Missoes;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/missoes")
public class MissoesController {

    // GET -- Mandar uma requisição para LISTAR as missões
    @GetMapping("/listar")
    public String listarMissoes() {
        return "Missoes listadas com sucesso";
    }

    // POST -- Mandar uma requisição para CRIAR as missões
    @PostMapping("/criar")
    public String criarMissao() {
        return "Missao criada com sucesso";
    }

    // PUT -- Mandar uma requisição para ALTERAR as missões
    @PutMapping("/alterar")
    public String alterarMissao() {
       return "Missao alterada com sucesso";
    }

    // DELETE -- Mandar uma requisição para DELETAR as missões
    @DeleteMapping("/deletar")
    public String deletarMissao() {
        return "Missao deletada com sucesso";
    }
}

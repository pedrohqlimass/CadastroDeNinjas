package dev.java10x.CadastroDeNinjas.Missoes;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/missoes")
public class MissaoController {

    private MissaoService missaoService;

    public MissaoController(MissaoService missaoService) {
        this.missaoService = missaoService;
    }

    // POST -- Mandar uma requisição para CRIAR as missões
    @PostMapping("/criar")
    public ResponseEntity<String> criarMissao(@RequestBody MissaoDTO missao) {

        MissaoDTO novaMissao = missaoService.criarMissao(missao);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Missão criada com sucesso: " + novaMissao.getNome() + " (ID) " + novaMissao.getId());
    }

    // GET -- Mandar uma requisição para LISTAR as missões
    @GetMapping("/listar")
    public ResponseEntity<List<MissaoDTO>> listarMissoes() {

        List<MissaoDTO> missoes = missaoService.listarMissoes();

        if (missoes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(missoes);
    }

    // Listar missao por ID
    @GetMapping("/listar/{id}")
    public ResponseEntity<?> listarMissaoPorId(@PathVariable Long id) {

        MissaoDTO missao = missaoService.listarMissaoPorId(id);

        if (missao != null) {
            return ResponseEntity.ok(missao);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Missão com o ID " + id + " não existe nos nossos resgistros.");
        }
    }

    // PUT -- Mandar uma requisição para ALTERAR as missões
    @PutMapping("/alterar/{id}")
    public ResponseEntity<String> alterarMissaoPorId(@PathVariable Long id, @RequestBody MissaoDTO missaoAtualizada) {

        if (missaoService.listarMissaoPorId(id) != null) {
            missaoService.alterarMissaoPorId(id, missaoAtualizada);
            return ResponseEntity.ok("Missão de ID " + id + " foi alterada com sucesso.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Missão com o ID " + id + " não existe nos nossos resgistros.");
        }
    }

    // DELETE -- Mandar uma requisição para DELETAR as missões
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deletarMissaoPorId(@PathVariable Long id) {

        if (missaoService.listarMissaoPorId(id) != null) {
            missaoService.deletarMissaoPorId(id);
            return ResponseEntity.ok("Missão com o ID " + id + " deletada com sucesso");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Missão com o ID " + id + " não existe nos nossos resgistros.");
        }
    }
}

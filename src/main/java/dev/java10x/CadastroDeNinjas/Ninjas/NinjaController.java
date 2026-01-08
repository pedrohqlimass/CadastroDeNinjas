package dev.java10x.CadastroDeNinjas.Ninjas;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class NinjaController {

    @GetMapping("/boasvindas")
    public String boasVindas() {
        return "Essa e minha primeira mensagem nessa rota";
    }

    //Adicionar ninja (CREATE)
    @PostMapping("/criar")
    public String criarNinja() {
        return "Ninja criado";
    }

    //Mostrar todos os ninjas (READ)
    @GetMapping("/todos")
    public String mostrarTodosOsNinjas() {
        return "Mostrar todos os ninjas";
    }

    //Mostrar ninja por ID (READ)
    @GetMapping("/ninjaID")
    public String mostrarNinjaPorId() {
        return "Mostrar ninja por id";
    }

    //Alterar dados dos ninjas (UPDATE)
    @PutMapping("/atualizarID")
    public String atualizarNinjaPorId() {
        return "Ninja atualizado por id";
    }

    //Deletar ninja (DELETE)
    @DeleteMapping("/deletarID")
    public String deletarNinjaPorId() {
        return "Ninja deletado por id";
    }
}

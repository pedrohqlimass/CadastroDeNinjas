package dev.java10x.CadastroDeNinjas.Ninjas;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/ninjas/ui")
public class NinjaControllerUi {

    private final NinjaService ninjaService;

    public NinjaControllerUi(NinjaService ninjaService) {
        this.ninjaService = ninjaService;
    }

    @GetMapping("/listar")
    public String listarNinjas(Model model) {
        List<NinjaDTO> ninjas = ninjaService.listarNinjas();
        model.addAttribute("ninjas", ninjas);
        return "listarNinjas"; // tem que retornar o nome exato do doc que renderiza
    }

    @GetMapping("/deletar/{id}")
    public String deletarNinjaPorId(@PathVariable Long id) {
        ninjaService.deletarNinjaPorId(id);
        return "redirect:/ninjas/ui/listar";
    }

    @GetMapping("/alterar/{id}")
    public String exibirFormularioAlteracao(@PathVariable Long id, Model model) {
        NinjaDTO ninja = ninjaService.listarNinjaPorId(id);
        model.addAttribute("ninja", ninja);
        return "formAlterarNinja";
    }

    @PostMapping("/alterar")
    public String salvarAlteracao(@ModelAttribute NinjaDTO ninjaAtualizado, RedirectAttributes redirectAttributes) {
        ninjaService.alterarNinjaPorId(ninjaAtualizado.getId(), ninjaAtualizado);
        redirectAttributes.addFlashAttribute("mensagem", "Ninja atualizado com sucesso!");
        return "redirect:/ninjas/ui/listar";
    }

    // detalhes do ninja
    @GetMapping("/listar/{id}")
    public String listarNinjaPorId(@PathVariable Long id, Model model) {
        NinjaDTO ninja = ninjaService.listarNinjaPorId(id);
        if (ninja != null) {
            model.addAttribute("ninja", ninja);
            return "detalhesNinja";
        } else {
            model.addAttribute("mensagem", "Ninja não encontrado");
            return "listarNinjas";
        }
    }

    @GetMapping("/adicionar")
    public String exibirFormularioAdicionarNinja(Model model) {
        model.addAttribute("ninja", new NinjaDTO());
        return "adicionarNinja";
    }

    @PostMapping("/salvar")
    public String salvarNinja(@ModelAttribute NinjaDTO ninja, RedirectAttributes redirectAttributes) {
        ninjaService.criarNinja(ninja);
        redirectAttributes.addFlashAttribute("mensagem", "Ninja cadastrado com sucesso!");
        return "redirect:/ninjas/ui/listar";
    }
}

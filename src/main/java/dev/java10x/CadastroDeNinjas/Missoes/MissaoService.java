package dev.java10x.CadastroDeNinjas.Missoes;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MissaoService {

    private MissaoRepository missaoRepository;
    private MissaoMapper missaoMapper;

    public MissaoService(MissaoRepository missaoRepository, MissaoMapper missaoMapper) {
        this.missaoRepository = missaoRepository;
        this.missaoMapper = missaoMapper;
    }

    // Listar todas as missoes
    public List<MissaoDTO> listarMissoes() {
        List<MissaoModel> missoes = missaoRepository.findAll();
        return missoes.stream()
                .map(missaoMapper::map)
                .collect(Collectors.toList());
    }

    // Listar missao por ID
    public MissaoDTO listarMissaoPorId(Long id) {
        Optional<MissaoModel> missaoPorId = missaoRepository.findById(id);
        return missaoPorId.map(missaoMapper::map).orElse(null);
    }

    // Criar uma missao
    public MissaoDTO criarMissao(MissaoDTO missaoDTO) {
        MissaoModel missao = missaoMapper.map(missaoDTO);
        missao = missaoRepository.save(missao);
        return missaoMapper.map(missao);
    }

    // Deletar missao por ID
    public void deletarMissaoPorId(Long id) {
        missaoRepository.deleteById(id);
    }

    // Atualizar missao
    public MissaoDTO alterarMissaoPorId(Long id, MissaoDTO missaoDTO) {
        Optional<MissaoModel> missaoExistente = missaoRepository.findById(id);
        if (missaoExistente.isPresent()) {
            MissaoModel missaoAtualizada = missaoMapper.map(missaoDTO);
            missaoAtualizada.setId(id);
            MissaoModel missaoSalva = missaoRepository.save(missaoAtualizada);
            return missaoMapper.map(missaoSalva);
        }

        return null;
    }
}

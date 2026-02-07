package dev.java10x.CadastroDeNinjas.Missoes;

import org.springframework.stereotype.Service;

import java.util.List;

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
        return missaoMapper.toDTOList(missaoRepository.findAll());
    }

    // Listar missao por ID
    public MissaoDTO listarMissaoPorId(Long id) {
        return missaoRepository.findById(id)
                .map(missaoMapper::toDTO)
                .orElse(null);
    }

    // Criar uma missao
    public MissaoDTO criarMissao(MissaoDTO dto) {
        MissaoModel model = missaoMapper.toModel(dto);
        return missaoMapper.toDTO(missaoRepository.save(model));
    }

    // Deletar missao por ID
    public void deletarMissaoPorId(Long id) {
        missaoRepository.deleteById(id);
    }

    // Atualizar missao
    public MissaoDTO alterarMissaoPorId(Long id, MissaoDTO dto) {
        return missaoRepository.findById(id)
                .map(missaoExistente -> {
                    MissaoModel missaoAtualizada = missaoMapper.toModel(dto);
                    missaoAtualizada.setId(id);
                    return missaoMapper.toDTO(missaoRepository.save(missaoAtualizada));
                })
                .orElse(null);
    }
}

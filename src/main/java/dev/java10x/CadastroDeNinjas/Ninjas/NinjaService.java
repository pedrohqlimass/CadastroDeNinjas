package dev.java10x.CadastroDeNinjas.Ninjas;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NinjaService {

    private final NinjaRepository ninjaRepository;
    private final NinjaMapper ninjaMapper;

    public NinjaService(NinjaRepository ninjaRepository, NinjaMapper ninjaMapper) {
        this.ninjaRepository = ninjaRepository;
        this.ninjaMapper = ninjaMapper;
    }

    // Listar todos os meus ninjas
    public List<NinjaDTO> listarNinjas() {
        return ninjaMapper.toDTOList(ninjaRepository.findAll());
    }

    // Listar ninja por ID
    public NinjaDTO listarNinjaPorId(Long id) {
        return ninjaRepository.findById(id)
                .map(ninjaMapper::toDTO)
                .orElse(null);
    }

    // Criar um novo ninja
    public NinjaDTO criarNinja(NinjaDTO dto) {
        NinjaModel model = ninjaMapper.toModel(dto);
        return ninjaMapper.toDTO(ninjaRepository.save(model));
    }

    // Deletar o ninja - TEM QUE SER UM METODO VOID
    public void deletarNinjaPorId(Long id) {
        ninjaRepository.deleteById(id);
    }

    // Atualiazar ninja
    public NinjaDTO alterarNinjaPorId(Long id, NinjaDTO dto) {
        return ninjaRepository.findById(id)
                .map(ninjaExistente -> {
                    NinjaModel ninjaAtualizado = ninjaMapper.toModel(dto);
                    ninjaAtualizado.setId(id);
                    return ninjaMapper.toDTO(ninjaRepository.save(ninjaAtualizado));
                })
                .orElse(null);

    }
}

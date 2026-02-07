package dev.java10x.CadastroDeNinjas.Ninjas;

import dev.java10x.CadastroDeNinjas.Missoes.MissaoMapper;
import org.springframework.stereotype.Component;
import java.util.stream.Collectors;

import java.util.List;

@Component
public class NinjaMapper {

    private final MissaoMapper missaoMapper;

    public NinjaMapper(MissaoMapper missaoMapper) {
        this.missaoMapper = missaoMapper;
    }

    public NinjaModel toModel(NinjaDTO dto) {
        if (dto == null) return null;

        NinjaModel model = new NinjaModel();
        model.setId(dto.getId());
        model.setNome(dto.getNome());
        model.setEmail(dto.getEmail());
        model.setIdade(dto.getIdade());
        model.setImgUrl(dto.getImgUrl());
        model.setRank(dto.getRank());
        model.setMissao(missaoMapper.toModel(dto.getMissoes()));
        model.setCriadoEm(dto.getCriadoEm());
        model.setAtualizadoEm(dto.getAtualizadoEm());

        return model;
    }

    public NinjaDTO toDTO(NinjaModel model) {
        if (model == null) return null;

        NinjaDTO dto = new NinjaDTO();
        dto.setId(model.getId());
        dto.setNome(model.getNome());
        dto.setEmail(model.getEmail());
        dto.setIdade(model.getIdade());
        dto.setImgUrl(model.getImgUrl());
        dto.setRank(model.getRank());
        dto.setMissoes(missaoMapper.toDTO(model.getMissao()));
        dto.setCriadoEm(model.getCriadoEm());
        dto.setAtualizadoEm(model.getAtualizadoEm());

        return dto;
    }

    public List<NinjaDTO> toDTOList(List<NinjaModel> models) {
        if (models == null) return List.of();

        return models.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public List<NinjaModel> toModelList(List<NinjaDTO> dtos) {
        if (dtos == null) return List.of();

        return dtos.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }
}
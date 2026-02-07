package dev.java10x.CadastroDeNinjas.Missoes;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MissaoMapper {

    // DTO -> Model
    public MissaoModel toModel(MissaoDTO dto) {
        if (dto == null) return null;

        MissaoModel model = new MissaoModel();
        model.setId(dto.getId());
        model.setNome(dto.getNome());
        model.setDificuldade(dto.getDificuldade());
        model.setCriadoEm(dto.getCriadoEm());
        model.setAtualizadoEm(dto.getAtualizadoEm());

        return model;
    }

    // Model -> DTO
    public MissaoDTO toDTO(MissaoModel model) {
        if (model == null) return null;

        MissaoDTO dto = new MissaoDTO();
        dto.setId(model.getId());
        dto.setNome(model.getNome());
        dto.setDificuldade(model.getDificuldade());
        dto.setCriadoEm(model.getCriadoEm());
        dto.setAtualizadoEm(model.getAtualizadoEm());

        return dto;
    }

    public List<MissaoDTO> toDTOList(List<MissaoModel> models) {
        if (models == null) return List.of();

        return models.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public List<MissaoModel> toModelList(List<MissaoDTO> dtos) {
        if (dtos == null) return List.of();

        return dtos.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }
}
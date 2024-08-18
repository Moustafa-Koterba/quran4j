package quran4j.admin.ayat.mapper;

import org.mapstruct.AnnotateWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;
import quran4j.admin.ayat.dto.AyatDTO;
import quran4j.library.ayat.business.model.Ayat;

import java.util.List;

@AnnotateWith(value = Component.class, elements = @AnnotateWith.Element(strings = "AdminAyatModelMapperImpl"))
@Mapper(componentModel = "spring")
public interface AyatModelMapper {
    @Mapping(target = "index", source = "verse")
    @Mapping(target = "text", source = "text")
    Ayat toModel(AyatDTO dto);

    default List<Ayat> toModels(List<AyatDTO> dtos) {
        return dtos.stream().map(this::toModel).toList();
    }
}

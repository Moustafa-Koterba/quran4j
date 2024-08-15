package quran4j.api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import quran4j.api.dto.AyatDTO;
import quran4j.api.model.Ayat;

@Mapper(componentModel = "spring")
public interface AyatModelMapper {
    @Mapping(source = "verse", target = "index")
    Ayat toModel(AyatDTO dto);
}

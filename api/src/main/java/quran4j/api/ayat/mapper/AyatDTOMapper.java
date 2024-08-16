package quran4j.api.ayat.mapper;

import org.mapstruct.Mapper;
import quran4j.api.ayat.dto.AyatDTO;
import quran4j.library.ayat.business.model.Ayat;

@Mapper(componentModel = "spring")
public interface AyatDTOMapper {
    AyatDTO toDTO(Ayat model);
}

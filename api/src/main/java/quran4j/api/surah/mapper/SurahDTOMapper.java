package quran4j.api.surah.mapper;

import org.mapstruct.Mapper;
import quran4j.api.ayat.mapper.AyatDTOMapper;
import quran4j.api.surah.dto.SurahDTO;
import quran4j.library.surah.business.model.Surah;

@Mapper(componentModel = "spring", uses = AyatDTOMapper.class)
public interface SurahDTOMapper {
    SurahDTO toDTO(Surah model);
}

package quran4j.api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import quran4j.api.dto.AyatDTO;
import quran4j.api.model.Ayat;
import quran4j.api.model.Quran;
import quran4j.api.model.Surah;

import java.util.List;
import java.util.Locale;
import java.util.Map;

@Mapper(componentModel = "spring")
public interface QuranModelMapper {

    /**
     * convert dto to model
     *
     * @param language current language, must be in IETF format
     * @param dto dto to convert
     * @return a model
     */
    default Quran toModel(String language, Map<String, List<AyatDTO>> dto) {
        List<Surah> surahs = dto.entrySet().stream()
                .map(entry -> new Surah(Integer.parseInt(entry.getKey()), null, entry.getValue().stream().map(this::ayatDTOtoModel).toList()))
                .toList();
        return new Quran(Locale.forLanguageTag(language), surahs);
    }

    default Ayat ayatDTOtoModel(AyatDTO dto) {
        return Mappers.getMapper(AyatModelMapper.class).toModel(dto);
    }
}

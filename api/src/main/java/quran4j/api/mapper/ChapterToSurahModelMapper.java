package quran4j.api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import quran4j.api.dto.SurahChapterDTO;
import quran4j.api.model.Origin;
import quran4j.api.model.Surah;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ChapterToSurahModelMapper {
    @Mapping(target = "index", source = "id")
    @Mapping(target = "title", source = "translation")
    @Mapping(target = "origin", expression = "java(toOrigin(dto.type()))")
    @Mapping(target = "ayats", ignore = true)
    Surah toSurahModel(SurahChapterDTO dto);

    default List<Surah> toSurahModels(List<SurahChapterDTO> dtos) {
        return dtos.stream().map(this::toSurahModel).toList();
    }

    default Origin toOrigin(String type) {
        return Origin.fromString(type);
    }
}

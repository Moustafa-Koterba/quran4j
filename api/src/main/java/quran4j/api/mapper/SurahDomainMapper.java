package quran4j.api.mapper;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import quran4j.api.domain.SurahDomain;
import quran4j.api.model.Surah;

import java.util.Locale;

@Mapper(componentModel = "spring", uses = AyatDomainMapper.class)
public interface SurahDomainMapper {
    @Mapping(target = "locale", source = "locale")
    SurahDomain toDomain(Surah model, Locale locale);

    Surah toModel(SurahDomain domain);

    @AfterMapping
    default void setSurahToAyats(@MappingTarget SurahDomain domain) {
        domain.getAyats().forEach(ayat -> ayat.setSurah(domain));
    }

}

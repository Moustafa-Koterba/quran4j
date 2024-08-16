package quran4j.library.surah.persistence.mapper;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import quran4j.library.ayat.persistence.mapper.AyatDomainMapper;
import quran4j.library.surah.persistence.domain.SurahDomain;
import quran4j.library.surah.business.model.Surah;

import java.util.Locale;

@Mapper(componentModel = "spring", uses = AyatDomainMapper.class)
public interface SurahDomainMapper {
    @Mapping(target = "locale", source = "locale")
    SurahDomain toDomain(Surah model, Locale locale);

    @AfterMapping
    default void setSurahToAyats(@MappingTarget SurahDomain domain) {
        domain.getAyats().forEach(ayat -> ayat.setSurah(domain));
    }

}

package quran4j.library.surah.business.mapper;

import org.mapstruct.Mapper;
import quran4j.library.surah.business.model.Surah;
import quran4j.library.surah.persistence.domain.SurahDomain;

@Mapper(componentModel = "spring")
public interface SurahModelMapper {
    Surah toModel(SurahDomain domain);
}

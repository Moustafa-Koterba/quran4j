package quran4j.library.ayat.persistence.mapper;

import org.mapstruct.Mapper;
import quran4j.library.ayat.business.model.Ayat;
import quran4j.library.ayat.persistence.domain.AyatDomain;

@Mapper(componentModel = "spring")
public interface AyatModelMapper {
    Ayat toModel(AyatDomain domain);
}

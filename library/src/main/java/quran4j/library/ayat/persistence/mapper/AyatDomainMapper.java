package quran4j.library.ayat.persistence.mapper;

import org.mapstruct.Mapper;
import quran4j.library.ayat.persistence.domain.AyatDomain;
import quran4j.library.ayat.business.model.Ayat;

@Mapper(componentModel = "spring")
public interface AyatDomainMapper {
    AyatDomain toDomain(Ayat model);
}

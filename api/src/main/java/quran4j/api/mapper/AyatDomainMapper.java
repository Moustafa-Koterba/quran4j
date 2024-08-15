package quran4j.api.mapper;

import org.mapstruct.Mapper;
import quran4j.api.domain.AyatDomain;
import quran4j.api.model.Ayat;

@Mapper(componentModel = "spring")
public interface AyatDomainMapper {
    AyatDomain toDomain(Ayat model);
}

package quran4j.api.surah.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import quran4j.api.surah.controller.SurahController;
import quran4j.api.surah.dto.SurahDTORepresentationModel;
import quran4j.library.surah.business.model.Surah;

import java.util.Locale;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
@RequiredArgsConstructor
public class SurahDTORepresentationModelMapper {
    private final SurahDTOMapper dtoMapper;

    public SurahDTORepresentationModel toDTO(Surah model, Locale locale) {
        SurahDTORepresentationModel dto = new SurahDTORepresentationModel(dtoMapper.toDTO(model));
        dto.add(linkTo(methodOn(SurahController.class).findByIndexAndLocale(model.getIndex(), locale)).withSelfRel());

        if (model.getIndex() > 1) {
            dto.add(linkTo(methodOn(SurahController.class).findByIndexAndLocale(model.getIndex() - 1, locale)).withRel("previous"));
        }

        if (model.getIndex() < 114) {
            dto.add(linkTo(methodOn(SurahController.class).findByIndexAndLocale(model.getIndex() + 1, locale)).withRel("next"));
        }
        return dto;
    }
}

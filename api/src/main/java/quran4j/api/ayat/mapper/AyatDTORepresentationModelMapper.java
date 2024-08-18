package quran4j.api.ayat.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import quran4j.api.ayat.controller.AyatController;
import quran4j.api.ayat.dto.AyatDTORepresentationModel;
import quran4j.api.surah.controller.SurahController;
import quran4j.library.ayat.business.model.Ayat;

import java.util.Locale;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
@RequiredArgsConstructor
public class AyatDTORepresentationModelMapper {
    private final AyatDTOMapper dtoMapper;

    public AyatDTORepresentationModel toRepresentationModel(Ayat model, int surahIndex, Locale language, int ayatCount) {
        AyatDTORepresentationModel representationModel = new AyatDTORepresentationModel(dtoMapper.toDTO(model));
        if (model.getIndex() > 1) {
            representationModel.add(linkTo(methodOn(AyatController.class).findBySurahIndexAndAyatIndexAndLanguage(surahIndex, model.getIndex() - 1, language)).withRel("previous"));
        }

        if (model.getIndex() < ayatCount) {
            representationModel.add(linkTo(methodOn(AyatController.class).findBySurahIndexAndAyatIndexAndLanguage(surahIndex, model.getIndex() + 1, language)).withRel("next"));
        }

        representationModel.add(linkTo(methodOn(SurahController.class).findByIndexAndLocale(surahIndex, language)).withRel("complete"));
        return representationModel;
    }
}

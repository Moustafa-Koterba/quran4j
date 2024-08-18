package quran4j.api.ayat.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import quran4j.api.ayat.dto.AyatDTORepresentationModel;
import quran4j.api.ayat.mapper.AyatDTORepresentationModelMapper;
import quran4j.library.ayat.business.model.Ayat;
import quran4j.library.ayat.business.service.AyatService;

import java.util.Locale;

@RestController
@RequestMapping(path = "/surah")
@RequiredArgsConstructor
public class AyatController {
    private final AyatService service;
    private final AyatDTORepresentationModelMapper representationModelMapper;

    @GetMapping(path = "/{surahIndex}/ayat/{ayatIndex}")
    public AyatDTORepresentationModel findBySurahIndexAndAyatIndexAndLanguage(@PathVariable int surahIndex, @PathVariable int ayatIndex, @RequestParam Locale language) {
        Ayat model = service.findBySurahIndexAndAyatIndexAndLanguage(surahIndex, ayatIndex, language);
        if (model != null) {
            int ayatCount = service.countBySurahIndexAndLanguage(surahIndex, language);
            return representationModelMapper.toRepresentationModel(model, surahIndex, language, ayatCount);
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
}

package quran4j.api.surah.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import quran4j.api.surah.dto.SurahDTORepresentationModel;
import quran4j.api.surah.mapper.SurahDTORepresentationModelMapper;
import quran4j.library.surah.business.service.SurahService;

import java.util.Locale;

@RestController
@RequestMapping(path = "/surah")
@RequiredArgsConstructor
public class SurahController {
    private final SurahService service;
    private final SurahDTORepresentationModelMapper dtoRepresentationModelMapper;

    @GetMapping(path = "/{index}")
    public SurahDTORepresentationModel findByIndexAndLocale(@PathVariable int index, @RequestParam Locale language) {
        var model = service.findByIndex(index, language);
        return dtoRepresentationModelMapper.toDTO(model, language);
    }
}

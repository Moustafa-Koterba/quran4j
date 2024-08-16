package quran4j.admin.surah.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import quran4j.admin.ayat.dto.AyatDTO;
import quran4j.admin.surah.dto.SurahDTO;
import quran4j.admin.surah.mapper.SurahModelMapper;
import quran4j.library.surah.business.service.SurahService;

import java.util.List;
import java.util.Locale;
import java.util.Map;

@RestController
@RequestMapping(path = "/admin/surah")
@RequiredArgsConstructor
public class SurahAdminController {
    private final SurahModelMapper modelMapper;
    private final SurahService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestParam String language, @RequestBody Map<String, List<AyatDTO>> dto) {
        var models = modelMapper.toModels(dto);
        service.saveAll(models, Locale.forLanguageTag(language));
    }

    @PatchMapping
    @ResponseStatus(HttpStatus.OK)
    public void patch(@RequestParam String language, @RequestBody List<SurahDTO> dtos) {
        var models = modelMapper.toModels(dtos);
        service.patchTitleAndOrigin(models, Locale.forLanguageTag(language));
    }
}

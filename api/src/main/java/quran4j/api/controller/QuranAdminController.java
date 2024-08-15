package quran4j.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import quran4j.api.dto.AyatDTO;
import quran4j.api.dto.SurahChapterDTO;
import quran4j.api.mapper.ChapterToSurahModelMapper;
import quran4j.api.mapper.QuranModelMapper;
import quran4j.api.service.QuranService;
import quran4j.api.service.SurahService;

import java.util.List;
import java.util.Locale;
import java.util.Map;

@RestController
@RequestMapping(path = "/admin/quran")
@RequiredArgsConstructor
public class QuranAdminController {
    private final QuranModelMapper quranModelMapper;
    private final QuranService service;

    private final ChapterToSurahModelMapper chapterToSurahModelMapper;
    private final SurahService surahService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestParam String language, @RequestBody Map<String, List<AyatDTO>> dto) {
        var quran = quranModelMapper.toModel(language, dto);
        service.save(quran);
    }

    @PatchMapping
    @ResponseStatus(HttpStatus.OK)
    public void patch(@RequestParam String language, @RequestBody List<SurahChapterDTO> dtos) {
        var surahs = chapterToSurahModelMapper.toSurahModels(dtos);
        surahService.patchTitleAndOrigin(surahs, Locale.forLanguageTag(language));
    }
}

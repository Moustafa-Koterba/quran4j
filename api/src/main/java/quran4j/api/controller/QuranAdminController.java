package quran4j.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import quran4j.api.dto.AyatDTO;
import quran4j.api.mapper.QuranModelMapper;
import quran4j.api.service.QuranService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/admin/quran")
@RequiredArgsConstructor
public class QuranAdminController {
    private final QuranModelMapper quranModelMapper;
    private final QuranService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestParam String language, @RequestBody Map<String, List<AyatDTO>> dto) {
        var quran = quranModelMapper.toModel(language, dto);
        service.save(quran);
    }
}

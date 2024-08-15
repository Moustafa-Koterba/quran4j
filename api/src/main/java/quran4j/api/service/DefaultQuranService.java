package quran4j.api.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import quran4j.api.model.Quran;

@Service
@RequiredArgsConstructor
public class DefaultQuranService implements QuranService {
    private final SurahService surahService;

    @Override
    public Quran save(Quran quran) {
        var surahs = surahService.saveAll(quran.getSurahs(), quran.getLocale());
        return new Quran(quran.getLocale(), surahs);
    }
}

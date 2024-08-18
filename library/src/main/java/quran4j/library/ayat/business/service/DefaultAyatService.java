package quran4j.library.ayat.business.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import quran4j.library.ayat.business.model.Ayat;
import quran4j.library.ayat.persistence.mapper.AyatModelMapper;
import quran4j.library.ayat.persistence.repository.AyatRepository;

import java.util.Locale;

@Service
@RequiredArgsConstructor
public class DefaultAyatService implements AyatService {
    private final AyatRepository repository;
    private final AyatModelMapper modelMapper;

    @Override
    public Ayat findBySurahIndexAndAyatIndexAndLanguage(int surahIndex, int ayatIndex, Locale language) {
        return repository.findBySurahIndexAndIndexAndSurahLanguage(surahIndex, ayatIndex, language)
                .map(modelMapper::toModel)
                .orElse(null);
    }

    @Override
    public int countBySurahIndexAndLanguage(int surahIndex, Locale language) {
        return repository.countBySurahIndexAndSurahLanguage(surahIndex, language);
    }
}

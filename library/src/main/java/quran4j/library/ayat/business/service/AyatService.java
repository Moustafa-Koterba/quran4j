package quran4j.library.ayat.business.service;

import quran4j.library.ayat.business.model.Ayat;

import java.util.Locale;

public interface AyatService {
    Ayat findBySurahIndexAndAyatIndexAndLanguage(int surahIndex, int ayatIndex, Locale language);
    int countBySurahIndexAndLanguage(int surahIndex, Locale language);
}

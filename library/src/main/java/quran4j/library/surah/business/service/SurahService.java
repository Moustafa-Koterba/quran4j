package quran4j.library.surah.business.service;

import quran4j.library.surah.business.model.Surah;

import java.util.List;
import java.util.Locale;

public interface SurahService {
    List<Surah> saveAll(List<Surah> surahs, Locale locale);
    List<Surah> patchTitleAndOrigin(List<Surah> surahs, Locale locale);
    Surah findByIndex(int index, Locale locale);
}

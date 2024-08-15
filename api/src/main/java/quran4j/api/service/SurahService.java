package quran4j.api.service;

import quran4j.api.model.Surah;

import java.util.List;
import java.util.Locale;

public interface SurahService {
    List<Surah> saveAll(List<Surah> surahs, Locale locale);
    List<Surah> patchTitleAndOrigin(List<Surah> surahs, Locale locale);
}

package quran4j.library.ayat.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import quran4j.library.ayat.persistence.domain.AyatDomain;

import java.util.Locale;
import java.util.Optional;

public interface AyatRepository extends JpaRepository<AyatDomain, Integer> {
    Optional<AyatDomain> findBySurahIndexAndIndexAndSurahLanguage(int surahIndex, int ayatIndex, Locale language);
    int countBySurahIndexAndSurahLanguage(int surahIndex, Locale language);
}

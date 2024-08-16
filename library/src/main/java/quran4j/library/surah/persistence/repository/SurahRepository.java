package quran4j.library.surah.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import quran4j.library.surah.persistence.domain.SurahDomain;

import java.util.List;
import java.util.Locale;

public interface SurahRepository extends JpaRepository<SurahDomain, Integer> {
    List<SurahDomain> findByIndexInAndLocale(List<Integer> indexes, Locale locale);

    SurahDomain findByIndexAndLocale(int index, Locale locale);
}

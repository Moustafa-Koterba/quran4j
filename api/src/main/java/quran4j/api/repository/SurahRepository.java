package quran4j.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import quran4j.api.domain.SurahDomain;

import java.util.List;
import java.util.Locale;

public interface SurahRepository extends JpaRepository<SurahDomain, Integer> {
    List<SurahDomain> findByIndexInAndLocale(List<Integer> indexes, Locale locale);
}

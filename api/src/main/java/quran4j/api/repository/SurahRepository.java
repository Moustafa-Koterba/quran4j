package quran4j.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import quran4j.api.domain.SurahDomain;

public interface SurahRepository extends JpaRepository<SurahDomain, Integer> {
}

package quran4j.api.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import quran4j.api.domain.SurahDomain;
import quran4j.api.mapper.SurahDomainMapper;
import quran4j.api.model.Surah;
import quran4j.api.repository.SurahRepository;

import java.util.List;
import java.util.Locale;

@Service
@RequiredArgsConstructor
public class DefaultSurahService implements SurahService {
    private final SurahRepository repository;
    private final SurahDomainMapper domainMapper;

    @Transactional
    @Override
    public List<Surah> saveAll(List<Surah> surahs, Locale locale) {
        var domains = surahs.stream()
                .map(model -> domainMapper.toDomain(model, locale))
                .toList();
        return repository.saveAll(domains).stream().map(domainMapper::toModel).toList();
    }

    @Transactional
    @Override
    public List<Surah> patchTitleAndOrigin(List<Surah> surahs, Locale locale) {
        List<SurahDomain> domains = repository.findByIndexInAndLocale(surahs.stream().map(Surah::getIndex).toList(), locale);
        domains.forEach(domain -> {
            Surah surah = surahs.stream().filter(model -> model.getIndex() == domain.getIndex()).findFirst().orElseThrow();
            domain.setTitle(surah.getTitle());
            domain.setOrigin(surah.getOrigin());
        });

        return repository.saveAll(domains).stream().map(domainMapper::toModel).toList();
    }
}

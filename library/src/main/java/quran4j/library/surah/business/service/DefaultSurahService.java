package quran4j.library.surah.business.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import quran4j.library.surah.business.mapper.SurahModelMapper;
import quran4j.library.surah.business.model.Surah;
import quran4j.library.surah.persistence.domain.SurahDomain;
import quran4j.library.surah.persistence.mapper.SurahDomainMapper;
import quran4j.library.surah.persistence.repository.SurahRepository;

import java.util.List;
import java.util.Locale;

@Service
@RequiredArgsConstructor
public class DefaultSurahService implements SurahService {
    private final SurahRepository repository;
    private final SurahDomainMapper domainMapper;
    private final SurahModelMapper modelMapper;

    @Transactional
    @Override
    public List<Surah> saveAll(List<Surah> surahs, Locale locale) {
        var domains = surahs.stream()
                .map(model -> domainMapper.toDomain(model, locale))
                .toList();
        return repository.saveAll(domains).stream().map(modelMapper::toModel).toList();
    }

    @Transactional
    @Override
    public List<Surah> patchTitleAndOrigin(List<Surah> models, Locale locale) {
        List<SurahDomain> domains = repository.findByIndexInAndLocale(models.stream().map(Surah::getIndex).toList(), locale);
        domains.forEach(domain -> {
            Surah surah = models.stream().filter(model -> model.getIndex() == domain.getIndex()).findFirst().orElseThrow();
            domain.setTitle(surah.getTitle());
            domain.setOrigin(surah.getOrigin());
        });

        return repository.saveAll(domains).stream().map(modelMapper::toModel).toList();
    }

    @Override
    public Surah findByIndex(int index, Locale locale) {
        return modelMapper.toModel(repository.findByIndexAndLocale(index, locale));
    }
}

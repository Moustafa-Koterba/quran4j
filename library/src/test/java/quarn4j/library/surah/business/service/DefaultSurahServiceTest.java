package quarn4j.library.surah.business.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import quran4j.library.surah.business.mapper.SurahModelMapper;
import quran4j.library.surah.business.model.Origin;
import quran4j.library.surah.business.model.Surah;
import quran4j.library.surah.business.service.DefaultSurahService;
import quran4j.library.surah.business.service.SurahService;
import quran4j.library.surah.persistence.domain.SurahDomain;
import quran4j.library.surah.persistence.mapper.SurahDomainMapper;
import quran4j.library.surah.persistence.repository.SurahRepository;

import java.util.List;
import java.util.Locale;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DefaultSurahServiceTest {
    private final SurahRepository repository = mock(SurahRepository.class);
    private final SurahDomainMapper domainMapper = mock(SurahDomainMapper.class);
    private final SurahModelMapper modelMapper = mock(SurahModelMapper.class);
    private final SurahService service = new DefaultSurahService(repository, domainMapper, modelMapper);

    @Test
    public void saveAll_should_correctly_save_all() {
        SurahDomain domain = domain(1);
        SurahDomain domain2 = domain(2);
        Surah model = Surah.builder().index(1).build();
        Surah model2 = Surah.builder().index(2).build();
        Locale locale = Locale.FRENCH;
        List<Surah> models = List.of(model, model2);
        List<SurahDomain> domains = List.of(domain, domain2);

        when(modelMapper.toModel(domain)).thenReturn(model);
        when(modelMapper.toModel(domain2)).thenReturn(model2);

        when(domainMapper.toDomain(model, locale)).thenReturn(domain);
        when(domainMapper.toDomain(model2, locale)).thenReturn(domain2);

        when(repository.saveAll(any())).thenReturn(domains);

        var surahs = service.saveAll(models, locale);

        assert surahs.size() == 2;
        assert surahs.stream().anyMatch(surah -> surah.getIndex() == model.getIndex());
        assert surahs.stream().anyMatch(surah -> surah.getIndex() == model2.getIndex());

        verify(modelMapper, times(1)).toModel(domain);
        verify(modelMapper, times(1)).toModel(domain2);
        verify(domainMapper, times(1)).toDomain(model, locale);
        verify(domainMapper, times(1)).toDomain(model2, locale);
        verify(repository, times(1)).saveAll(domains);
    }

    @Test
    public void patchTitleAndOrigin_should_correctly_patch_title_and_origin() {
        SurahDomain domain = domain(1);
        SurahDomain domain2 = domain(2);
        Surah model = Surah.builder().index(1).title("Al Fatiha").origin(Origin.MECCAN).build();
        Surah model2 = Surah.builder().index(2).title("Al Baqara").origin(Origin.MEDINAN).build();
        Locale locale = Locale.FRENCH;
        List<Surah> models = List.of(model, model2);
        List<SurahDomain> domains = List.of(domain, domain2);

        when(modelMapper.toModel(domain)).thenReturn(model);
        when(modelMapper.toModel(domain2)).thenReturn(model2);

        when(repository.findByIndexInAndLanguage(any(), eq(locale))).thenReturn(domains);
        when(repository.saveAll(any())).thenReturn(domains);

        var surahs = service.patchTitleAndOrigin(models, locale);

        assert surahs.size() == 2;
        assert surahs.stream().anyMatch(surah -> surah.getIndex() == model.getIndex());
        assert surahs.stream().anyMatch(surah -> surah.getIndex() == model2.getIndex());
        assert model.getTitle().contentEquals(domain.getTitle());
        assert model.getOrigin() == domain.getOrigin();
        assert model2.getTitle().contentEquals(domain2.getTitle());
        assert model2.getOrigin() == domain2.getOrigin();

        verify(modelMapper, times(1)).toModel(domain);
        verify(modelMapper, times(1)).toModel(domain2);
        verify(repository, times(1)).saveAll(domains);
        verify(repository, times(1)).findByIndexInAndLanguage(any(), eq(locale));
    }

    @Test
    public void findByIndex_should_return_correct_model() {
        int index = 1;
        Locale locale = Locale.FRENCH;
        SurahDomain domain = domain(index);
        Surah model = Surah.builder().index(index).build();

        when(repository.findByIndexAndLanguage(eq(index), eq(locale))).thenReturn(domain);
        when(modelMapper.toModel(domain)).thenReturn(model);

        var surah = service.findByIndex(index, locale);
        assert surah.getIndex() == model.getIndex();

        verify(repository, times(1)).findByIndexAndLanguage(index, locale);
        verify(modelMapper, times(1)).toModel(domain);
    }

    private static SurahDomain domain(int index) {
        SurahDomain domain = new SurahDomain();
        domain.setIndex(index);
        return domain;
    }
}

package quarn4j.library.ayat.business.service;

import org.junit.jupiter.api.Test;
import quran4j.library.ayat.business.model.Ayat;
import quran4j.library.ayat.business.service.AyatService;
import quran4j.library.ayat.business.service.DefaultAyatService;
import quran4j.library.ayat.persistence.domain.AyatDomain;
import quran4j.library.ayat.persistence.mapper.AyatModelMapper;
import quran4j.library.ayat.persistence.repository.AyatRepository;

import java.util.Locale;
import java.util.Optional;

import static org.mockito.Mockito.*;

public class DefaultAyatServiceTest {

    private final AyatRepository repository = mock(AyatRepository.class);
    private final AyatModelMapper modelMapper = mock(AyatModelMapper.class);
    private final AyatService service = new DefaultAyatService(repository, modelMapper);

    @Test
    public void findBySurahIndexAndAyatIndexAndLanguage_should_return_model_when_repository_found() {
        AyatDomain domain = new AyatDomain();
        Ayat model = new Ayat(1, "a");

        when(repository.findBySurahIndexAndIndexAndSurahLanguage(1, 1, Locale.FRENCH)).thenReturn(Optional.of(domain));
        when(modelMapper.toModel(domain)).thenReturn(model);

        Ayat result = service.findBySurahIndexAndAyatIndexAndLanguage(1, 1, Locale.FRENCH);
        assert result == model;

        verify(repository, times(1)).findBySurahIndexAndIndexAndSurahLanguage(1, 1, Locale.FRENCH);
        verify(modelMapper, times(1)).toModel(domain);
    }

    @Test
    public void findBySurahIndexAndAyatIndexAndLanguage_should_return_null_when_repository_do_not_found() {
        when(repository.findBySurahIndexAndIndexAndSurahLanguage(1, 1, Locale.FRENCH)).thenReturn(Optional.empty());

        Ayat result = service.findBySurahIndexAndAyatIndexAndLanguage(1, 1, Locale.FRENCH);
        assert result == null;

        verify(repository, times(1)).findBySurahIndexAndIndexAndSurahLanguage(1, 1, Locale.FRENCH);
        verify(modelMapper, never()).toModel(any());
    }

    @Test
    public void countBySurahIndexAndLanguage_should_return_correct_result() {
        when(repository.countBySurahIndexAndSurahLanguage(1, Locale.FRENCH)).thenReturn(2);

        int result = service.countBySurahIndexAndLanguage(1, Locale.FRENCH);
        assert result == 2;

        verify(repository, times(1)).countBySurahIndexAndSurahLanguage(1, Locale.FRENCH);
    }
}

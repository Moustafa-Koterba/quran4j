package quran4j.api.ayat.mapper;

import org.junit.jupiter.api.Test;
import quran4j.api.ayat.dto.AyatDTO;
import quran4j.api.ayat.dto.AyatDTORepresentationModel;
import quran4j.library.ayat.business.model.Ayat;

import java.util.Locale;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AyatDTORepresentationModelMapperTest {
    private final AyatDTOMapper dtoMapper = mock(AyatDTOMapper.class);
    private final AyatDTORepresentationModelMapper mapper = new AyatDTORepresentationModelMapper(dtoMapper);

    @Test
    public void toRepresentationModel_should_return_correct_result() {
        int surahIndex = 1;
        int ayatIndex = 2;
        Locale language = Locale.FRENCH;
        int ayatCount = 3;
        Ayat model = new Ayat(ayatIndex, null);
        AyatDTO dto = new AyatDTO(ayatIndex, null);

        when(dtoMapper.toDTO(model)).thenReturn(dto);

        AyatDTORepresentationModel result = mapper.toRepresentationModel(model, surahIndex, language, ayatCount);
        assert result.getContent() == dto;
        assert result.getLinks().hasSize(3);
        assert result.getLink("next").get().getHref().contentEquals("/surah/1/ayat/3?language=fr");
        assert result.getLink("previous").get().getHref().contentEquals("/surah/1/ayat/1?language=fr");
        assert result.getLink("complete").get().getHref().contentEquals("/surah/1?language=fr");
    }

    @Test
    public void toRepresentationModel_should_not_return_next_rel_when_current_ayat_index_equals_ayat_count() {
        int surahIndex = 1;
        int ayatIndex = 3;
        Locale language = Locale.FRENCH;
        int ayatCount = 3;
        Ayat model = new Ayat(ayatIndex, null);
        AyatDTO dto = new AyatDTO(ayatIndex, null);

        when(dtoMapper.toDTO(model)).thenReturn(dto);

        AyatDTORepresentationModel result = mapper.toRepresentationModel(model, surahIndex, language, ayatCount);
        assert result.getContent() == dto;
        assert result.getLinks().hasSize(2);
        assert result.getLink("previous").get().getHref().contentEquals("/surah/1/ayat/2?language=fr");
        assert result.getLink("complete").get().getHref().contentEquals("/surah/1?language=fr");
    }

    @Test
    public void toRepresentationModel_should_not_return_next_previous_when_current_ayat_index_equals_one() {
        int surahIndex = 1;
        int ayatIndex = 1;
        Locale language = Locale.FRENCH;
        int ayatCount = 3;
        Ayat model = new Ayat(ayatIndex, null);
        AyatDTO dto = new AyatDTO(ayatIndex, null);

        when(dtoMapper.toDTO(model)).thenReturn(dto);

        AyatDTORepresentationModel result = mapper.toRepresentationModel(model, surahIndex, language, ayatCount);
        assert result.getContent() == dto;
        assert result.getLinks().hasSize(2);
        assert result.getLink("next").get().getHref().contentEquals("/surah/1/ayat/2?language=fr");
        assert result.getLink("complete").get().getHref().contentEquals("/surah/1?language=fr");
    }
}

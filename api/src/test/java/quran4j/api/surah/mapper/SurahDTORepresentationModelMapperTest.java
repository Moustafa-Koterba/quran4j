package quran4j.api.surah.mapper;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import quran4j.api.surah.dto.SurahDTO;
import quran4j.api.surah.dto.SurahDTORepresentationModel;
import quran4j.library.surah.business.model.Surah;

import java.util.Locale;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SurahDTORepresentationModelMapperTest {
    private final SurahDTOMapper dtoMapper = mock(SurahDTOMapper.class);
    private final SurahDTORepresentationModelMapper mapper = new SurahDTORepresentationModelMapper(dtoMapper);

    @Test
    public void toDTO_should_correctly_convert_model_to_dto() {
        Surah model = Surah.builder().index(2).build();
        SurahDTO dto = new SurahDTO(0, null, null, null);

        when(dtoMapper.toDTO(model)).thenReturn(dto);

        SurahDTORepresentationModel representationModel = mapper.toDTO(model, Locale.FRENCH);
        assert representationModel.getContent() == dto;
        assert representationModel.hasLink("previous");
        assert representationModel.hasLink("self");
        assert representationModel.hasLink("next");
        assert representationModel.getLink("previous").get().getHref().contentEquals("/surah/1?language=fr");
        assert representationModel.getLink("self").get().getHref().contentEquals("/surah/2?language=fr");
        assert representationModel.getLink("next").get().getHref().contentEquals("/surah/3?language=fr");
    }

    @Test
    public void toDTO_should_not_set_next_when_index_is_114() {
        Surah model = Surah.builder().index(114).build();
        SurahDTO dto = new SurahDTO(0, null, null, null);

        when(dtoMapper.toDTO(model)).thenReturn(dto);

        SurahDTORepresentationModel representationModel = mapper.toDTO(model, Locale.FRENCH);
        assert representationModel.getContent() == dto;
        assert representationModel.getLinks().hasSize(2);
        assert representationModel.hasLink("previous");
        assert representationModel.hasLink("self");
        assert representationModel.getLink("previous").get().getHref().contentEquals("/surah/113?language=fr");
        assert representationModel.getLink("self").get().getHref().contentEquals("/surah/114?language=fr");
    }

    @Test
    public void toDTO_should_not_set_previous_when_index_is_1() {
        Surah model = Surah.builder().index(1).build();
        SurahDTO dto = new SurahDTO(0, null, null, null);

        when(dtoMapper.toDTO(model)).thenReturn(dto);

        SurahDTORepresentationModel representationModel = mapper.toDTO(model, Locale.FRENCH);
        assert representationModel.getContent() == dto;
        assert representationModel.getLinks().hasSize(2);
        assert representationModel.hasLink("next");
        assert representationModel.hasLink("self");
        assert representationModel.getLink("next").get().getHref().contentEquals("/surah/2?language=fr");
        assert representationModel.getLink("self").get().getHref().contentEquals("/surah/1?language=fr");
    }
}

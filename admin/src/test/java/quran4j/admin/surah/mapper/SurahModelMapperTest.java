package quran4j.admin.surah.mapper;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import quran4j.admin.ayat.dto.AyatDTO;
import quran4j.admin.ayat.mapper.AyatModelMapper;
import quran4j.admin.surah.dto.SurahDTO;
import quran4j.library.ayat.business.model.Ayat;
import quran4j.library.surah.business.model.Origin;
import quran4j.library.surah.business.model.Surah;

import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SurahModelMapperTest {
    private final AyatModelMapper ayatModelMapper = mock(AyatModelMapper.class);
    private final SurahModelMapper mapper = new SurahModelMapper(ayatModelMapper);

    @Test
    public void toModels_should_return_correct_result() {
        List<AyatDTO> ayatDTOs = List.of(
                new AyatDTO(1, 1, "a"),
                new AyatDTO(1, 2, "b")
        );
        List<Ayat> ayatModels = List.of(
                new Ayat(1, "a"),
                new Ayat(2, "b")
        );

        List<AyatDTO> ayatDTOs2 = List.of(
                new AyatDTO(2, 1, "c"),
                new AyatDTO(2, 2, "d"),
                new AyatDTO(2, 3, "e")
        );
        List<Ayat> ayatModels2 = List.of(
                new Ayat(1, "c"),
                new Ayat(2, "d"),
                new Ayat(3, "e")
        );

        when(ayatModelMapper.toModels(ayatDTOs)).thenReturn(ayatModels);
        when(ayatModelMapper.toModels(ayatDTOs2)).thenReturn(ayatModels2);

        Map<String, List<AyatDTO>> dtos = Map.of(
                "1", ayatDTOs,
                "2", ayatDTOs2
        );

        List<Surah> models = mapper.toModels(dtos);

        assert models.size() == 2;
        assert models.stream().anyMatch(surah -> surah.getIndex() == 1 && surah.getAyats().size() == 2);
        assert models.stream().anyMatch(surah -> surah.getIndex() == 2 && surah.getAyats().size() == 3);
        assert models.stream().flatMap(surah -> surah.getAyats().stream()).anyMatch(ayat -> ayat.getIndex() == 1 && ayat.getText().contentEquals("a"));
        assert models.stream().flatMap(surah -> surah.getAyats().stream()).anyMatch(ayat -> ayat.getIndex() == 2 && ayat.getText().contentEquals("b"));
        assert models.stream().flatMap(surah -> surah.getAyats().stream()).anyMatch(ayat -> ayat.getIndex() == 1 && ayat.getText().contentEquals("c"));
        assert models.stream().flatMap(surah -> surah.getAyats().stream()).anyMatch(ayat -> ayat.getIndex() == 2 && ayat.getText().contentEquals("d"));
        assert models.stream().flatMap(surah -> surah.getAyats().stream()).anyMatch(ayat -> ayat.getIndex() == 3 && ayat.getText().contentEquals("e"));

    }

    @Test
    public void toModel_should_return_correct_result() {
        SurahDTO dto = new SurahDTO(1, "a", "meccan");

        Surah model = mapper.toModel(dto);

        assert model.getIndex() == dto.id();
        assert model.getTitle().contentEquals(dto.translation());
        assert model.getOrigin() == Origin.fromString(dto.type());
    }

    @Test
    public void toModels_should_return_correct_result_for_surah_dto() {
        SurahDTO dto = new SurahDTO(1, "a", "meccan");
        SurahDTO dto2 = new SurahDTO(2, "b", "medinan");

        List<Surah> models = mapper.toModels(List.of(dto, dto2));

        assert models.size() == 2;
        assert models.stream().anyMatch(model ->
                model.getIndex() == dto.id()
                        && model.getTitle().contentEquals(dto.translation())
                        && model.getOrigin() == Origin.fromString(dto.type())
        );
        assert models.stream().anyMatch(model ->
                model.getIndex() == dto2.id()
                        && model.getTitle().contentEquals(dto2.translation())
                        && model.getOrigin() == Origin.fromString(dto2.type())
        );
    }
}

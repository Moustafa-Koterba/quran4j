package quran4j.admin.surah.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import quran4j.admin.ayat.dto.AyatDTO;
import quran4j.admin.ayat.mapper.AyatModelMapper;
import quran4j.admin.surah.dto.SurahDTO;
import quran4j.library.surah.business.model.Origin;
import quran4j.library.surah.business.model.Surah;

import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class SurahModelMapper {
    private final AyatModelMapper ayatModelMapper;

    public List<Surah> toModels(Map<String, List<AyatDTO>> dto) {
        return dto.entrySet().stream()
                .map(entry -> toSurah(Integer.parseInt(entry.getKey()), entry.getValue()))
                .toList();
    }

    public Surah toModel(SurahDTO dto) {
        return Surah.builder()
                .index(dto.id())
                .title(dto.translation())
                .origin(toOrigin(dto.type()))
                .build();
    }

    public List<Surah> toModels(List<SurahDTO> dtos) {
        return dtos.stream().map(this::toModel).toList();
    }

    private Surah toSurah(int index, List<AyatDTO> dtos) {
        return Surah.builder().index(index).ayats(ayatModelMapper.toModels(dtos)).build();
    }

    private static Origin toOrigin(String type) {
        return Origin.fromString(type);
    }
}

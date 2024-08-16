package quran4j.api.surah.dto;

import quran4j.api.ayat.dto.AyatDTO;
import quran4j.library.surah.business.model.Origin;

import java.util.List;

public record SurahDTO(int index, String title, Origin origin, List<AyatDTO> ayats) {
}

package quran4j.api.dto;

import java.util.List;
import java.util.Map;

public record QuranDTO(Map<String, List<AyatDTO>> ayatsBySurahIndex) {
}

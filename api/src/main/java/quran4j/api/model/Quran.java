package quran4j.api.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Locale;

@Getter
@RequiredArgsConstructor
public class Quran {
    private final Locale locale;
    private final List<Surah> surahs;
}

package quran4j.api.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class Surah {
    private final int index;
    private final String title;
    private final Origin origin;
    private final List<Ayat> ayats;
}

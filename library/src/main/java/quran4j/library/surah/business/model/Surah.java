package quran4j.library.surah.business.model;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import quran4j.library.ayat.business.model.Ayat;

import java.util.List;

@Getter
@Builder
public class Surah {
    private final int index;
    private final String title;
    private final Origin origin;
    private final List<Ayat> ayats;
}

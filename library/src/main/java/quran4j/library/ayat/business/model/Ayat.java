package quran4j.library.ayat.business.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Ayat {
    private final int index;
    private final String text;
}

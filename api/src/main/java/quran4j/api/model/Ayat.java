package quran4j.api.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Ayat {
    private final int index;
    private final String text;
}

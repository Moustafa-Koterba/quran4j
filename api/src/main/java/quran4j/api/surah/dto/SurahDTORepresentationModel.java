package quran4j.api.surah.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;

@Getter
public class SurahDTORepresentationModel extends RepresentationModel<SurahDTORepresentationModel> {
    private final SurahDTO content;

    @JsonCreator
    public SurahDTORepresentationModel(@JsonProperty("content") SurahDTO content) {
        this.content = content;
    }
}

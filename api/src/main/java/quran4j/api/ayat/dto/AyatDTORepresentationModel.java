package quran4j.api.ayat.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;

@Getter
public class AyatDTORepresentationModel extends RepresentationModel<AyatDTORepresentationModel> {
    private final AyatDTO content;

    @JsonCreator
    public AyatDTORepresentationModel(@JsonProperty("content") AyatDTO content) {
        this.content = content;
    }
}

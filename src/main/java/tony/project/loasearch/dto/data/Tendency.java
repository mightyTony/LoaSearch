package tony.project.loasearch.dto.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Tendency {
    @JsonProperty("Type")
    private String type;

    @JsonProperty("Point")
    private Integer point;

    @JsonProperty("MaxPoint")
    private Integer maxPoint;
}
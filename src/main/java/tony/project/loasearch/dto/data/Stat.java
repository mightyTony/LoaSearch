package tony.project.loasearch.dto.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class Stat {
    @JsonProperty("Type")
    private String type;

    @JsonProperty("Value")
    private String value;

    @JsonProperty("Tooltip")
    private List<String> tooltip;
}
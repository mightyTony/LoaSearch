package tony.project.loasearch.dto.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Decoration {
    @JsonProperty("Symbol")
    private String symbol;

    @JsonProperty("Emblems")
    private List<String> emblems;
}


package tony.project.loasearch.dto.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ArmoryAvatar {

    @JsonProperty("Type")
    private String type;

    @JsonProperty("Name")
    private String name;

    @JsonProperty("Icon")
    private String icon;

    @JsonProperty("Grade")
    private String grade;

    @JsonProperty("IsSet")
    private boolean isSet;

    @JsonProperty("IsInner")
    private boolean isInner;

    @JsonProperty("Tooltip")
    private String tooltip;
}

package tony.project.loasearch.dto.data.ArmorySkill;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class ArmorySkill {

    @JsonProperty("Name")
    private String name;

    @JsonProperty("Icon")
    private String icon;

    @JsonProperty("Level")
    private int level;

    @JsonProperty("Type")
    private String type;

    @JsonProperty("SkillType")
    private int skillType;

    @JsonProperty("Tripods")
    private List<SkillTripod> tripods;

    @JsonProperty("Rune")
    private SkillRune rune;

    @JsonProperty("Tooltip")
    private String tooltip;
}

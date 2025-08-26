package tony.project.loasearch.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import tony.project.loasearch.dto.data.ArmoryEquipment;
import tony.project.loasearch.dto.data.ArmoryProfile;
import tony.project.loasearch.dto.data.CharacterInfo;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CharacterInfoResponse {
    @JsonProperty("Characters")
    private List<CharacterInfo> characters;
    @JsonProperty("ArmoryProfile")
    private ArmoryProfile armoryProfile;
    @JsonProperty("ArmoryEquipment")
    private List<ArmoryEquipment> equipments;

}

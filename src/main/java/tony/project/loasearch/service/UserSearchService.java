package tony.project.loasearch.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import tony.project.loasearch.dto.data.ArmoryAvatar;
import tony.project.loasearch.dto.data.ArmoryEquipment;
import tony.project.loasearch.dto.data.ArmoryProfile;
import tony.project.loasearch.dto.data.ArmorySkill.ArmorySkill;
import tony.project.loasearch.dto.data.CharacterInfo;
import tony.project.loasearch.dto.response.CharacterInfoResponse;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserSearchService {
    private final WebClient webClient;

    public Mono<List<CharacterInfo>> getSiblingInfo(String characterName) {
        Mono<List<CharacterInfo>> characterList = webClient.get()
                .uri("/characters/{name}/siblings", characterName)
                .retrieve()
                .bodyToFlux(CharacterInfo.class)
                .collectList()
                .map(list -> list.stream()
                        .sorted(Comparator.comparingDouble(this::parseItemAvgLevel).reversed()) // 내림차순
                        .toList()
                ).doOnError(error -> log.error("[에러] 원정대 정보 불러오기 실패 : 캐릭터명 : {}, 에러 : {}", characterName, error.getMessage()));

        return characterList;
    }

    private double parseItemAvgLevel(CharacterInfo info) {
        try {
            return Double.parseDouble(info.getItemAvgLevel().replace(",", ""));
        } catch (Exception e) {
            return 0.0;
        }
    }

    public Mono<CharacterInfoResponse> getCharacterInfo(String characterName) {
        Mono<List<CharacterInfo>> siblings = callList("/characters/{name}/siblings", characterName, CharacterInfo.class);
        Mono<ArmoryProfile> profile = call("/armories/characters/{name}/profiles", characterName, ArmoryProfile.class);
        Mono<List<ArmoryEquipment>> equipment = callList("/armories/characters/{name}/equipment", characterName, ArmoryEquipment.class);
        Mono<List<ArmoryAvatar>> avatars = callList("/armories/characters/{name}/avatars", characterName, ArmoryAvatar.class);
        Mono<List<ArmorySkill>> skills = callList("/armories/characters/{name}/combat-skills", characterName, ArmorySkill.class);

        return Mono.zip(siblings, profile,equipment, avatars, skills)
                .map(tuple -> new CharacterInfoResponse(
                        tuple.getT1(),
                        tuple.getT2(),
                        tuple.getT3(),
                        tuple.getT4(),
                        tuple.getT5())
                )
                .doOnError(error -> log.error("[에러] 캐릭터 프로필 정보 불러오기 실패 : 캐릭터명 : {}, 에러 : {}", characterName, error.getMessage()));
    }



    private <T> Mono<T> call(String uri, String name, Class<T> clazz) {
        return webClient.get()
                .uri(uri, name)
                .retrieve()
                .bodyToMono(clazz)
                .doOnError(e -> log.error("[에러] API 호출 실패 [{}]: {}", uri, e.getMessage()));
    }
    private <T> Mono<List<T>> callList(String uri, String name, Class<T> clazz) {
        return webClient.get()
                .uri(uri, name)
                .retrieve()
                .bodyToFlux(clazz)
                .collectList()
                .doOnError(e -> log.error("[에러] API 호출 실패 [{}]: {}", uri, e.getMessage()));
    }
}

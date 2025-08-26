package tony.project.loasearch.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import tony.project.loasearch.dto.data.CharacterInfo;
import tony.project.loasearch.dto.response.CharacterInfoResponse;
import tony.project.loasearch.service.UserSearchService;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserSearchController {
    private final UserSearchService userSearchService;

    @GetMapping("/search/{characterName}/siblings")
    public Mono<List<CharacterInfo>> getSiblingInfo(@PathVariable String characterName) {
        return userSearchService.getSiblingInfo(characterName);
    }

    @GetMapping("/search/profile/{characterName}")
    public Mono<CharacterInfoResponse> getCharacterInfo(@PathVariable String characterName) {
        return userSearchService.getCharacterInfo(characterName);
    }
}

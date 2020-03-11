package pl.poznan.put.cs.project.spotifypartyplanner.rest;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.poznan.put.cs.project.spotifypartyplanner.rest.model.response.ExploreSearchResponse;
import pl.poznan.put.cs.project.spotifypartyplanner.service.ExploreService;

import java.util.stream.Collectors;

@RestController
@RequestMapping("explore")
public class ExploreController {

    private final ExploreService service;

    public ExploreController(ExploreService service) {
        this.service = service;
    }

    @GetMapping(value = "search", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ExploreSearchResponse> getSearchResults(@RequestParam String query) {
        var tracks = service.search(query).collect(Collectors.toList());
        if (tracks.isEmpty()) return ResponseEntity.noContent().build();
        else return ResponseEntity.ok(new ExploreSearchResponse(tracks, query));
    }
}
package com.kodilla.sportscentre.youtube;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Component
@RequiredArgsConstructor
public class YouTubeClient {

    private final RestTemplate restTemplate;
    private final YouTubeConfig youTubeConfig;

    public YouTubeDto getYouTubeVideoInfo(int videoIndex) {
        URI url = getYouTubeInfoUrlAddress(youTubeConfig.getVideos().get(videoIndex));
        YouTubeDto youTubeDto = restTemplate.getForObject(url, YouTubeDto.class);
        return youTubeDto;
    }

    private URI getYouTubeInfoUrlAddress(String videoId) {
        return UriComponentsBuilder.fromHttpUrl(youTubeConfig.getYouTubeEndpoint())
                .queryParam("id", videoId)
                .queryParam("key", youTubeConfig.getYouTubeKey())
                .queryParam("part", "snippet,statistics")
                .build()
                .encode()
                .toUri();
    }

    public URI getYouTubeVideoUrlAddress(String videoId) {
        return UriComponentsBuilder.fromHttpUrl(youTubeConfig.getYouTubeVideo())
                .queryParam("v", videoId)
                .build()
                .encode()
                .toUri();
    }
}

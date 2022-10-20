package com.kodilla.sportscentre.youtube;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Getter
@Component
public class YouTubeConfig {

    @Value("${youtube.api.endpoint}")
    private String youTubeEndpoint;

    @Value("${youtube.video.endpoint}")
    private String youTubeVideo;

    @Value("${youtube.key}")
    private String youTubeKey;

    @Value("${youtube.videos}")
    private List<String> videos;
}

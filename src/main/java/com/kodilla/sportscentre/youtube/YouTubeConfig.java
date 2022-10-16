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

    @Value("${youtube.video.0}")
    private String videoId0;

    @Value("${youtube.video.1}")
    private String videoId1;

    @Value("${youtube.video.2}")
    private String videoId2;

    @Value("${youtube.video.3}")
    private String videoId3;

    @Value("${youtube.video.4}")
    private String videoId4;

    @Value("${youtube.video.5}")
    private String videoId5;

    @Value("${youtube.video.6}")
    private String videoId6;

    @Value("${youtube.video.7}")
    private String videoId7;

    @Value("${youtube.video.8}")
    private String videoId8;

    @Value("${youtube.video.9}")
    private String videoId9;

    public List<String> getVideosIdList() {
        List<String> videos = new ArrayList<>();
        videos.add(videoId0);
        videos.add(videoId1);
        videos.add(videoId2);
        videos.add(videoId3);
        videos.add(videoId4);
        videos.add(videoId5);
        videos.add(videoId6);
        videos.add(videoId7);
        videos.add(videoId8);
        videos.add(videoId9);
        return videos;
    }
}

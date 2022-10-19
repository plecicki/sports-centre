package com.kodilla.sportscentre.config;

import com.kodilla.sportscentre.youtube.YouTubeConfig;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class YouTubeConfigTest {

    @Autowired
    private YouTubeConfig youTubeConfig;

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

    @Test
    void youTubeConfigTest() {
        //Given & When
        List<String> videosList = youTubeConfig.getVideosIdList();
        //Then
        Assertions.assertEquals(youTubeEndpoint, youTubeConfig.getYouTubeEndpoint());
        Assertions.assertEquals(youTubeVideo, youTubeConfig.getYouTubeVideo());
        Assertions.assertEquals(youTubeKey, youTubeConfig.getYouTubeKey());
        Assertions.assertEquals(videoId0, youTubeConfig.getVideoId0());
        Assertions.assertEquals(videoId1, youTubeConfig.getVideoId1());
        Assertions.assertEquals(videoId2, youTubeConfig.getVideoId2());
        Assertions.assertEquals(videoId3, youTubeConfig.getVideoId3());
        Assertions.assertEquals(videoId4, youTubeConfig.getVideoId4());
        Assertions.assertEquals(videoId5, youTubeConfig.getVideoId5());
        Assertions.assertEquals(videoId6, youTubeConfig.getVideoId6());
        Assertions.assertEquals(videoId7, youTubeConfig.getVideoId7());
        Assertions.assertEquals(videoId8, youTubeConfig.getVideoId8());
        Assertions.assertEquals(videoId9, youTubeConfig.getVideoId9());
        Assertions.assertEquals(videoId0, videosList.get(0));
        Assertions.assertEquals(videoId1, videosList.get(1));
        Assertions.assertEquals(videoId2, videosList.get(2));
        Assertions.assertEquals(videoId3, videosList.get(3));
        Assertions.assertEquals(videoId4, videosList.get(4));
        Assertions.assertEquals(videoId5, videosList.get(5));
        Assertions.assertEquals(videoId6, videosList.get(6));
        Assertions.assertEquals(videoId7, videosList.get(7));
        Assertions.assertEquals(videoId8, videosList.get(8));
        Assertions.assertEquals(videoId9, videosList.get(9));
    }
}

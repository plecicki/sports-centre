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

    @Value("${youtube.videos}")
    private List<String> videos;

    @Test
    void youTubeConfigTest() {
        //Given & When
        List<String> videosList = youTubeConfig.getVideos();
        //Then
        Assertions.assertEquals(youTubeEndpoint, youTubeConfig.getYouTubeEndpoint());
        Assertions.assertEquals(youTubeVideo, youTubeConfig.getYouTubeVideo());
        Assertions.assertEquals(youTubeKey, youTubeConfig.getYouTubeKey());
        for (int i=0; i<videosList.size(); i++) {
            Assertions.assertEquals(videos.get(i), videosList.get(i));
        }
    }
}

package com.kodilla.sportscentre.services;

import com.kodilla.sportscentre.youtube.MyYouTubeDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class YouTubeServiceTest {

    @Autowired
    private YouTubeService youTubeService;

    @Test
    void getYouTubeVideosTest() {
        //Given & When
        List<MyYouTubeDto> videos = youTubeService.getYouTubeVideos();

        //Then
        Assertions.assertEquals(videos.size(), 10);
        Assertions.assertNotNull(videos.get(0));
        Assertions.assertNotNull(videos.get(1));
        Assertions.assertNotNull(videos.get(2));
        Assertions.assertNotNull(videos.get(3));
        Assertions.assertNotNull(videos.get(4));
        Assertions.assertNotNull(videos.get(5));
        Assertions.assertNotNull(videos.get(6));
        Assertions.assertNotNull(videos.get(7));
        Assertions.assertNotNull(videos.get(8));
        Assertions.assertNotNull(videos.get(9));
    }
}

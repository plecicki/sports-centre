package com.kodilla.sportscentre.controllers.tofront;

import com.kodilla.sportscentre.services.YouTubeService;
import com.kodilla.sportscentre.youtube.MyYouTubeDto;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

@SpringJUnitWebConfig
@WebMvcTest(YouTubeController.class)
public class YouTubeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private YouTubeService youTubeService;

    @Test
    void shouldFetchListWithYouTubeMoviesInformations() throws Exception {

        MyYouTubeDto myYouTubeDto1 = new MyYouTubeDto(
                LocalDateTime.of(2019, 11, 11, 17, 5, 0),
                "Jak TRENOWAĆ na siłowni żeby budować MIĘŚNIE?",
                "Tomek Grzymski",
                URI.create("https://i.ytimg.com/vi/Chj7_GC1QQk/mqdefault.jpg"),
                "179792",
                "6027",
                URI.create("https://www.youtube.com/watch?v=Chj7_GC1QQk")
        );
        MyYouTubeDto myYouTubeDto2 = new MyYouTubeDto(
                LocalDateTime.of(2021, 6, 20, 16, 0, 11),
                "Dlaczego WARTO PŁYWAĆ - 7 powodów",
                "Nieprzeciętne Życie",
                URI.create("https://i.ytimg.com/vi/SytnIgTKD-Q/mqdefault.jpg"),
                "16623",
                "736",
                URI.create("https://www.youtube.com/watch?v=SytnIgTKD-Q")
        );
        MyYouTubeDto myYouTubeDto3 = new MyYouTubeDto(
                LocalDateTime.of(2020, 6, 7, 7, 55, 47),
                "Pierwszy Raz Na Siłowni? MUSISZ TO WIEDZIEĆ",
                "Piotr 'Szmexy' Tomaszewski",
                URI.create("https://i.ytimg.com/vi/sfrP87la44Q/mqdefault.jpg"),
                "57371",
                "2587",
                URI.create("https://www.youtube.com/watch?v=sfrP87la44Q")
        );
        List<MyYouTubeDto> myYouTubeDtoList = Arrays.asList(
                myYouTubeDto1,
                myYouTubeDto2,
                myYouTubeDto3
        );

        when(youTubeService.getYouTubeVideos()).thenReturn(myYouTubeDtoList);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/youtube")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(3)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].publishedAt", Matchers.is("2019-11-11T17:05:00")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].title", Matchers.is("Jak TRENOWAĆ na siłowni żeby budować MIĘŚNIE?")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].channelTitle", Matchers.is("Tomek Grzymski")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].imageUrl", Matchers.is("https://i.ytimg.com/vi/Chj7_GC1QQk/mqdefault.jpg")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].viewCount", Matchers.is("179792")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].likeCount", Matchers.is("6027")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].videoUrl", Matchers.is("https://www.youtube.com/watch?v=Chj7_GC1QQk")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].publishedAt", Matchers.is("2021-06-20T16:00:11")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].title", Matchers.is("Dlaczego WARTO PŁYWAĆ - 7 powodów")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].channelTitle", Matchers.is("Nieprzeciętne Życie")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].imageUrl", Matchers.is("https://i.ytimg.com/vi/SytnIgTKD-Q/mqdefault.jpg")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].viewCount", Matchers.is("16623")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].likeCount", Matchers.is("736")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].videoUrl", Matchers.is("https://www.youtube.com/watch?v=SytnIgTKD-Q")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].publishedAt", Matchers.is("2020-06-07T07:55:47")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].title", Matchers.is("Pierwszy Raz Na Siłowni? MUSISZ TO WIEDZIEĆ")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].channelTitle", Matchers.is("Piotr 'Szmexy' Tomaszewski")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].imageUrl", Matchers.is("https://i.ytimg.com/vi/sfrP87la44Q/mqdefault.jpg")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].viewCount", Matchers.is("57371")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].likeCount", Matchers.is("2587")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].videoUrl", Matchers.is("https://www.youtube.com/watch?v=sfrP87la44Q")));
    }
}

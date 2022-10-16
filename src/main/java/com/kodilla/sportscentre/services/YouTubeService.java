package com.kodilla.sportscentre.services;

import com.kodilla.sportscentre.mappers.YouTubeMapper;
import com.kodilla.sportscentre.youtube.MyYouTubeDto;
import com.kodilla.sportscentre.youtube.YouTubeClient;
import com.kodilla.sportscentre.youtube.YouTubeConfig;
import com.kodilla.sportscentre.youtube.YouTubeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class YouTubeService {
    private final YouTubeClient youTubeClient;
    private final YouTubeConfig youTubeConfig;
    private final YouTubeMapper youTubeMapper;

    public List<MyYouTubeDto> getYouTubeVideos() {
        List<MyYouTubeDto> myYouTubeDtoList = new ArrayList<>();
        for (int i=0; i < youTubeConfig.getVideosIdList().size(); i++) {
            YouTubeDto youTubeDto = youTubeClient.getYouTubeVideoInfo(i);
            URI videoUri = youTubeClient.getYouTubeVideoUrlAddress(youTubeConfig.getVideosIdList().get(i));
            myYouTubeDtoList.add(youTubeMapper.mapYouTubeDtoToMyYouTubeDto(youTubeDto, videoUri));
        }
        return myYouTubeDtoList;
    }
}

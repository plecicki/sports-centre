package com.kodilla.sportscentre.mappers;

import com.kodilla.sportscentre.youtube.MyYouTubeDto;
import com.kodilla.sportscentre.youtube.YouTubeDto;
import org.springframework.stereotype.Service;

import java.net.URI;

@Service
public class YouTubeMapper {

    public MyYouTubeDto mapYouTubeDtoToMyYouTubeDto(YouTubeDto youTubeDto, URI videoUrl) {
        return new MyYouTubeDto(
                youTubeDto.getItems()[0].getSnippet().getPublishedAt(),
                youTubeDto.getItems()[0].getSnippet().getTitle(),
                youTubeDto.getItems()[0].getSnippet().getChannelTitle(),
                youTubeDto.getItems()[0].getSnippet().getThumbnails().getMedium().getUrl(),
                youTubeDto.getItems()[0].getStatistics().getViewCount(),
                youTubeDto.getItems()[0].getStatistics().getLikeCount(),
                videoUrl
        );
    }
}

package com.kodilla.sportscentre.youtube;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class SnippetDto {

    private LocalDateTime publishedAt;
    private String title;
    private ThumbnailsDto thumbnails;
    private String channelTitle;
}

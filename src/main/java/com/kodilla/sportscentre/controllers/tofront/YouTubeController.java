package com.kodilla.sportscentre.controllers.tofront;

import com.kodilla.sportscentre.services.YouTubeService;
import com.kodilla.sportscentre.youtube.MyYouTubeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("v1/youtube")
@RequiredArgsConstructor
@CrossOrigin("*")
public class YouTubeController {

    private final YouTubeService youTubeService;

    @GetMapping
    public ResponseEntity<List<MyYouTubeDto>> getYouTubeVideos() {
        return ResponseEntity.ok(youTubeService.getYouTubeVideos());
    }
}

package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/videos")
public class VideoController {

    @Autowired
    private VideoService videoService;

    // Add a new video
    @PostMapping
    public ResponseEntity<Video> addVideo(@RequestBody Video video) {
        return ResponseEntity.ok(videoService.saveVideo(video));
    }

    // List all videos
    @GetMapping
    public ResponseEntity<List<Video>> getAllVideos() {
        return ResponseEntity.ok(videoService.listAllVideos());
    }

    // Get a video by ID
    @GetMapping("/{id}")
    public ResponseEntity<Video> getVideoById(@PathVariable Long id) {
        return videoService.getVideoById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Soft delete a video
    @PutMapping("/{id}/delist")
    public ResponseEntity<String> delistVideo(@PathVariable Long id) {
        videoService.delistVideo(id);
        return ResponseEntity.ok("Video delisted successfully.");
    }

    // Delete a video
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVideo(@PathVariable Long id) {
        videoService.delistVideo(id);
        return ResponseEntity.ok().build();
    }

    // Play a video
    @GetMapping("/{id}/play")
    public ResponseEntity<String> playVideo(@PathVariable Long id) {
        return ResponseEntity.ok(videoService.playVideo(id));
    }

    // Serve video content
    @GetMapping("/{id}/content")
    public ResponseEntity<String> getVideoContent(@PathVariable Long id) {
        return ResponseEntity.ok("Streaming content for video ID: " + id);
    }

    // Get statistics for a video
    @GetMapping("/{id}/stats")
    public ResponseEntity<Statistics> getStatistics(@PathVariable Long id) {
        return ResponseEntity.ok(videoService.getStatistics(id));
    }


    // Search videos
    @GetMapping("/search")
    public ResponseEntity<List<Video>> searchVideos(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String director,
            @RequestParam(required = false) String genre,
            @RequestParam(required = false) Integer year) {
        return ResponseEntity.ok(videoService.searchVideos(title, director, genre, year));
    }
}

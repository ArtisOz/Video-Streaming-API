package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VideoService {

    @Autowired
    private VideoRepository videoRepository;

    @Autowired
    private StatisticsRepository statisticsRepository;

    // Add a video
    public Video saveVideo(Video video) {
        return videoRepository.save(video);
    }

    // List all videos
    public List<Video> listAllVideos() {
        return videoRepository.findAll();
    }

    // Get a video by ID
    public Optional<Video> getVideoById(Long id) {
        return videoRepository.findById(id);
    }

    // Soft delete a video
    public void delistVideo(Long id) {
        videoRepository.findById(id).ifPresent(video -> {
            video.setListed(false);
            videoRepository.save(video);
        });
    }

    // Play a video
    public String playVideo(Long id) {
        return videoRepository.findById(id)
                .map(video -> "Playing video: " + video.getTitle() + " - [Content Mocked]")
                .orElseThrow(() -> new RuntimeException("Video not found with ID: " + id));
    }

    // Get statistics for a video
    public Statistics getStatistics(Long id) {
        return statisticsRepository.findByVideoId(id)
                .orElseThrow(() -> new RuntimeException("Statistics not found for video ID: " + id));
    }
}

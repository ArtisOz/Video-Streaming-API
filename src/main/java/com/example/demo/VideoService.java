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
        Video savedVideo = videoRepository.save(video);
        Statistics statistics = new Statistics();
        statistics.setVideoId(savedVideo.getId());
        statistics.setImpressions(0);
        statistics.setViews(0);
        statisticsRepository.save(statistics);
        return savedVideo;
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

    // Get statistics for a video
    public Statistics getStatistics(Long videoId) {
        return statisticsRepository.findByVideoId(videoId)
                .orElseThrow(() -> new RuntimeException("Statistics not found for video ID: " + videoId));
    }
}
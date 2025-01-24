package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VideoRepository extends JpaRepository<Video, Long> {

    @Query("SELECT v FROM Video v WHERE " +
            "(:title IS NULL OR v.title LIKE %:title%) AND " +
            "(:director IS NULL OR v.director LIKE %:director%) AND " +
            "(:genre IS NULL OR v.genre LIKE %:genre%) AND " +
            "(:year IS NULL OR v.yearOfRelease = :year)")
    List<Video> searchVideos(
            @Param("title") String title,
            @Param("director") String director,
            @Param("genre") String genre,
            @Param("year") Integer yearOfRelease
    );
}

package com.teamhyeok.harmonyenglishacademy.api.repository;

import com.teamhyeok.harmonyenglishacademy.api.domain.EnglishTranscript;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface EnglishTranscriptRepository extends JpaRepository<EnglishTranscript, Long>{

    Page<EnglishTranscript> findAllByOrderByCreatedAtDesc(Pageable pageable);
    Page<EnglishTranscript> findByTitleContainingIgnoreCase(String title, Pageable pageable);

}


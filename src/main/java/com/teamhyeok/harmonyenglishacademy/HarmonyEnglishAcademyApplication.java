package com.teamhyeok.harmonyenglishacademy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class HarmonyEnglishAcademyApplication {

    public static void main(String[] args) {
        SpringApplication.run(HarmonyEnglishAcademyApplication.class, args);
    }

}

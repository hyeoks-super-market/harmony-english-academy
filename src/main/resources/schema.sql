CREATE TABLE IF NOT EXISTS english_transcript (
                                  id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                  title VARCHAR(255) NOT NULL,
                                  content TEXT,
                                  youtube_url VARCHAR(255) NOT NULL,
                                  created_at TIMESTAMP
                              );
-- V1__create_recordings_and_lines.sql

CREATE TABLE recordings (
    id VARCHAR(36) PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    author VARCHAR(255) NOT NULL,
    actor VARCHAR(255),
    audio_url VARCHAR(512) NOT NULL,
    start_time_sec INTEGER
);

CREATE TABLE recording_lines (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    recording_id VARCHAR(36) NOT NULL,
    line_text TEXT,
    FOREIGN KEY (recording_id) REFERENCES recordings(id) ON DELETE CASCADE
);

-- Indeksy przyspieszające wyszukiwanie
CREATE INDEX idx_recordings_title ON recordings(title);
CREATE INDEX idx_recordings_author ON recordings(author);
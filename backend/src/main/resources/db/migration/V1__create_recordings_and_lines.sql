CREATE TABLE recordings (
    id VARCHAR(36) PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    author VARCHAR(255) NOT NULL,
    actor VARCHAR(255),
    audio_url VARCHAR(512) NOT NULL,
    start_time_sec INTEGER,
    status VARCHAR(20) NOT NULL
);

CREATE TABLE recording_lines (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    recording_id VARCHAR(36) NOT NULL,
    line_text TEXT NOT NULL,
    line_order INTEGER NOT NULL,
    FOREIGN KEY (recording_id) REFERENCES recordings(id) ON DELETE CASCADE
);

-- Indeksy
CREATE INDEX idx_recordings_title ON recordings(title);
CREATE INDEX idx_recordings_author ON recordings(author);
CREATE INDEX idx_recordings_status ON recordings(status);
CREATE INDEX idx_lines_recording_id ON recording_lines(recording_id);
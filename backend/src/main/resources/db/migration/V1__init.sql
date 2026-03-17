CREATE TABLE poets (
    id VARCHAR(36) PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    bio VARCHAR(5000),
    birth_year INT,
    death_year INT,
    photo_url VARCHAR(255)
);

CREATE TABLE actors (
    id VARCHAR(36) PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    bio VARCHAR(5000),
    photo_url VARCHAR(255)
);

CREATE TABLE poems (
    id VARCHAR(36) PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    text TEXT,
    poet_id VARCHAR(36) NOT NULL,
    CONSTRAINT fk_poem_poet
        FOREIGN KEY (poet_id) REFERENCES poets(id)
        ON DELETE CASCADE
);

CREATE TABLE recordings (
    id VARCHAR(36) PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    audio_url VARCHAR(512) NOT NULL,
    start_time_sec INT,
    status VARCHAR(50) NOT NULL,
    poem_id VARCHAR(36) NOT NULL,
    actor_id VARCHAR(36),
    CONSTRAINT fk_recording_poem
        FOREIGN KEY (poem_id) REFERENCES poems(id)
        ON DELETE CASCADE,
    CONSTRAINT fk_recording_actor
        FOREIGN KEY (actor_id) REFERENCES actors(id)
        ON DELETE SET NULL
);

CREATE TABLE recording_lines (
    recording_id VARCHAR(36) NOT NULL,
    line_order INT NOT NULL,
    line_text TEXT,
    PRIMARY KEY (recording_id, line_order),
    CONSTRAINT fk_lines_recording
        FOREIGN KEY (recording_id) REFERENCES recordings(id)
        ON DELETE CASCADE
);

CREATE INDEX idx_poems_poet ON poems(poet_id);
CREATE INDEX idx_recordings_poem ON recordings(poem_id);
CREATE INDEX idx_recordings_actor ON recordings(actor_id);
CREATE INDEX idx_lines_recording ON recording_lines(recording_id);
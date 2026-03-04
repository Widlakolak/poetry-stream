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
    text CLOB,
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

-- =========================
-- DEMO DATA
-- =========================

-- Poet
INSERT INTO poets (id, name)
VALUES ('poet-1', 'Julian Tuwim');

-- Actor
INSERT INTO actors (id, name)
VALUES ('actor-1', 'Nieznany');

-- Poem
INSERT INTO poems (id, title, poet_id)
VALUES ('poem-1', 'Okulary', 'poet-1');

-- Recording
INSERT INTO recordings (
    id,
    title,
    audio_url,
    start_time_sec,
    status,
    poem_id,
    actor_id
)
VALUES (
    'demo-1',
    'Okulary',
    'https://dn720708.ca.archive.org/0/items/lokomotywa-tuwim/Tuwim-Okulary.mp3',
    15,
    'PUBLISHED',
    'poem-1',
    'actor-1'
);

-- Recording lines
INSERT INTO recording_lines (recording_id, line_order, line_text) VALUES
('demo-1', 1, 'Biega, krzyczy pan Hilary:'),
('demo-1', 2, '„Gdzie są moje okulary?”'),
('demo-1', 3, 'Szuka w spodniach i w surducie,'),
('demo-1', 4, 'W prawym bucie, w lewym bucie.'),
('demo-1', 5, 'Wszystko w szafach poprzewracał,'),
('demo-1', 6, 'Maca szlafrok, palto maca.'),
('demo-1', 7, '„Skandal! – krzyczy - nie do wiary!'),
('demo-1', 8, 'Ktoś mi ukradł okulary!”'),
('demo-1', 9, 'Pod kanapą, na kanapie,'),
('demo-1', 10, 'Wszędzie szuka, parska, sapie!'),
('demo-1', 11, 'Szpera w piecu i w kominie,'),
('demo-1', 12, 'W mysiej dziurze i w pianinie.'),
('demo-1', 13, 'Już podłogę chce odrywać,'),
('demo-1', 14, 'Już policję zaczął wzywać.'),
('demo-1', 15, 'Nagle - zerknął do lusterka…'),
('demo-1', 16, 'Nie chce wierzyć… Znowu zerka.'),
('demo-1', 17, 'Znalazł! Są! Okazało się,'),
('demo-1', 18, 'Że je ma na własnym nosie.');
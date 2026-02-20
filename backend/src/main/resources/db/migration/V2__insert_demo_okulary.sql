-- V2__insert_demo_okulary.sql

INSERT INTO recordings (id, title, author, actor, audio_url, start_time_sec, status)
VALUES (
    'demo-1',
    'Okulary',
    'Julian Tuwim',
    'Nieznany',
    'https://dn720708.ca.archive.org/0/items/lokomotywa-tuwim/Tuwim-Okulary.mp3',
    15,
    'PUBLISHED'
);

INSERT INTO recording_lines (recording_id, line_order, line_text)
VALUES
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
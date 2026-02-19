-- V2__insert_demo_okulary.sql

INSERT INTO recordings (id, title, author, actor, audio_url, start_time_sec)
VALUES (
    'demo-1',
    'Okulary',
    'Julian Tuwim',
    'Nieznany',
    'https://dn720708.ca.archive.org/0/items/lokomotywa-tuwim/Tuwim-Okulary.mp3',
    15
);

INSERT INTO recording_lines (recording_id, line_text)
VALUES
    ('demo-1', 'Biega, krzyczy pan Hilary:'),
    ('demo-1', '„Gdzie są moje okulary?”'),
    ('demo-1', 'Szuka w spodniach i w surducie,'),
    ('demo-1', 'W prawym bucie, w lewym bucie.'),
    ('demo-1', 'Wszystko w szafach poprzewracał,'),
    ('demo-1', 'Maca szlafrok, palto maca.'),
    ('demo-1', '„Skandal! – krzyczy - nie do wiary!'),
    ('demo-1', 'Ktoś mi ukradł okulary!”'),
    ('demo-1', 'Pod kanapą, na kanapie,'),
    ('demo-1', 'Wszędzie szuka, parska, sapie!'),
    ('demo-1', 'Szpera w piecu i w kominie,'),
    ('demo-1', 'W mysiej dziurze i w pianinie.'),
    ('demo-1', 'Już podłogę chce odrywać,'),
    ('demo-1', 'Już policję zaczął wzywać.'),
    ('demo-1', 'Nagle - zerknął do lusterka…'),
    ('demo-1', 'Nie chce wierzyć… Znowu zerka.'),
    ('demo-1', 'Znalazł! Są! Okazało się,'),
    ('demo-1', 'Że je ma na własnym nosie.');
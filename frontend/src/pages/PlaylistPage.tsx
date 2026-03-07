import { useEffect, useMemo, useState } from "react";
import { Link, useNavigate, useParams } from "react-router-dom";
import { poetryApi } from "../api/poetryApi";
import type { Poem, Recording } from "../types/domain";
import RecordingPlayer from "../components/RecordingPlayer";

export default function PlaylistPage() {
  const { id = "" } = useParams();
  const navigate = useNavigate();

  const [recordings, setRecordings] = useState<Recording[]>([]);
  const [poems, setPoems] = useState<Poem[]>([]);
  const [error, setError] = useState<string | null>(null);

  useEffect(() => {
    Promise.all([poetryApi.getRecordings(), poetryApi.getPoems()])
      .then(([recordingsPage, poemsData]) => {
        setRecordings(recordingsPage.content);
        setPoems(poemsData);
      })
      .catch((err: Error) => setError(err.message));
  }, []);

  const poemById = useMemo(() => new Map(poems.map((poem) => [poem.id, poem])), [poems]);

  const selectedRecording = useMemo(
    () => recordings.find((recording) => recording.id === id) ?? recordings[0] ?? null,
    [id, recordings],
  );

  const handleSelectRecording = (recordingId: string) => {
    navigate(`/playlists/${recordingId}`);
  };

  return (
    <div className="content-stack">
      <Link to="/" className="back-link">
        ← Powrót
      </Link>

      {error && <p className="error-text">{error}</p>}

      <RecordingPlayer
        key={selectedRecording?.id ?? "playlist-player"}
        recording={selectedRecording}
        heading="Playlista"
        description="Odtwarzanie utworów z wybranej playlisty."
      />

      <section className="panel">
        <header className="panel-header">
          <h2>Playlista wierszy</h2>
          <p>Wybierz utwór z listy, aby od razu przełączyć player.</p>
        </header>

        <div className="playlist-list">
          {recordings.map((recording) => {
            const isActive = selectedRecording?.id === recording.id;
            return (
              <button
                key={recording.id}
                className={`playlist-item ${isActive ? "is-active" : ""}`}
                onClick={() => handleSelectRecording(recording.id)}
              >
                <span>{recording.title}</span>
                <small>{poemById.get(recording.poemId)?.title ?? "Wiersz"}</small>
              </button>
            );
          })}
        </div>
      </section>
    </div>
  );
}
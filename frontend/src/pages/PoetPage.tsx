import { useEffect, useMemo, useState } from "react";
import { Link, useParams } from "react-router-dom";
import { poetryApi } from "../api/poetryApi";
import type { Actor, Poem, Poet, Recording } from "../types/domain";
import RecordingCard from "../components/RecordingCard";
import RecordingPlayer from "../components/RecordingPlayer";

export default function PoetPage() {
  const { id = "" } = useParams();
  const [poet, setPoet] = useState<Poet | null>(null);
  const [poems, setPoems] = useState<Poem[]>([]);
  const [actors, setActors] = useState<Actor[]>([]);
  const [recordings, setRecordings] = useState<Recording[]>([]);
  const [selectedRecordingId, setSelectedRecordingId] = useState<string | null>(null);
  const [error, setError] = useState<string | null>(null);

    useEffect(() => {
      Promise.all([poetryApi.getPoet(id), poetryApi.getPoems(), poetryApi.getActors(), poetryApi.getRecordings()])
        .then(([poetData, poemsData, actorsData, recordingPage]) => {
          setPoet(poetData);
          setPoems(poemsData);
          setActors(actorsData);
          setRecordings(recordingPage.content);
        })
        .catch((err: Error) => setError(err.message));
    }, [id]);

    const actorById = useMemo(() => new Map(actors.map((actor) => [actor.id, actor])), [actors]);

  const recordingsForPoet = useMemo(() => {
    const poetPoemIds = new Set(poems.filter((poem) => poem.poetId === id).map((poem) => poem.id));
    return recordings.filter((recording) => poetPoemIds.has(recording.poemId));
  }, [id, poems, recordings]);

  const selectedRecording = useMemo(
    () => recordingsForPoet.find((recording) => recording.id === selectedRecordingId) ?? recordingsForPoet[0] ?? null,
    [recordingsForPoet, selectedRecordingId],
  );

  const readerName = selectedRecording ? actorById.get(selectedRecording.actorId)?.name ?? "Nieznany lektor" : "";

  return (
    <div className="content-stack">
      <Link to="/" className="back-link">
        ← Powrót
      </Link>
      {error && <p className="error-text">{error}</p>}
      {poet && (
        <section className="person-page-card">
          <img src={poet.photoUrl} alt={poet.name} />
          <div>
            <h2>{poet.name}</h2>
            <p className="muted">
              {poet.birthYear} {poet.deathYear ? `- ${poet.deathYear}` : ""}
            </p>
            <p>{poet.bio}</p>
          </div>
        </section>
      )}

      <RecordingPlayer
        key={selectedRecording?.id ?? "details-player"}
        recording={selectedRecording}
        heading={selectedRecording?.title ?? "Nagranie poety"}
        description={selectedRecording ? `Czyta ${readerName}` : undefined}
        showRecordingTitle={false}
      />

      <section className="panel">
        <header className="panel-header">
          <h2>Nagrania poety</h2>
        </header>
        <div className="horizontal-scroll">
          {recordingsForPoet.map((recording) => (
            <RecordingCard key={recording.id} recording={recording} onSelect={(value) => setSelectedRecordingId(value.id)} />
          ))}
        </div>
      </section>
    </div>
  );
}
import { useEffect, useMemo, useState } from "react";
import { Link, useParams } from "react-router-dom";
import { poetryApi } from "../api/poetryApi";
import type { Actor, Poem, Poet, Recording } from "../types/domain";
import RecordingCard from "../components/RecordingCard";
import RecordingPlayer from "../components/RecordingPlayer";

export default function ActorPage() {
  const { id = "" } = useParams();
  const [actor, setActor] = useState<Actor | null>(null);
  const [recordings, setRecordings] = useState<Recording[]>([]);
  const [poems, setPoems] = useState<Poem[]>([]);
  const [poets, setPoets] = useState<Poet[]>([]);
  const [selectedRecordingId, setSelectedRecordingId] = useState<string | null>(null);
  const [error, setError] = useState<string | null>(null);

  useEffect(() => {
    Promise.all([poetryApi.getActor(id), poetryApi.getRecordings(), poetryApi.getPoems(), poetryApi.getPoets()])
      .then(([actorData, recordingPage, poemsData, poetsData]) => {
        setActor(actorData);
        setRecordings(recordingPage.content);
        setPoems(poemsData);
        setPoets(poetsData);
      })
      .catch((err: Error) => setError(err.message));
  }, [id]);

  const poemById = useMemo(() => new Map(poems.map((poem) => [poem.id, poem])), [poems]);
  const poetById = useMemo(() => new Map(poets.map((poet) => [poet.id, poet])), [poets]);

  const actorRecordings = useMemo(() => recordings.filter((recording) => recording.actorId === id), [id, recordings]);

  const selectedRecording = useMemo(
    () => actorRecordings.find((recording) => recording.id === selectedRecordingId) ?? actorRecordings[0] ?? null,
    [actorRecordings, selectedRecordingId],
  );

  const authorName = useMemo(() => {
    if (!selectedRecording) {
      return "";
    }
    const poem = poemById.get(selectedRecording.poemId);
    if (!poem) {
      return "Nieznany autor";
    }
    return poetById.get(poem.poetId)?.name ?? "Nieznany autor";
  }, [poemById, poetById, selectedRecording]);

  return (
    <div className="content-stack">
      <Link to="/" className="back-link">
        ← Powrót
      </Link>
      {error && <p className="error-text">{error}</p>}
      {actor && (
        <section className="person-page-card">
          <img src={actor.photoUrl} alt={actor.name} />
          <div>
            <h2>{actor.name}</h2>
            <p>{actor.bio}</p>
          </div>
        </section>
      )}

      <RecordingPlayer
        key={selectedRecording?.id ?? "details-player"}
        recording={selectedRecording}
        heading={selectedRecording?.title ?? "Nagranie artysty"}
        description={selectedRecording ? `Autor ${authorName}` : undefined}
        showRecordingTitle={false}
      />

      <section className="panel">
        <header className="panel-header">
          <h2>Nagrania artysty</h2>
        </header>
        <div className="horizontal-scroll">
          {actorRecordings.map((recording) => (
            <RecordingCard key={recording.id} recording={recording} onSelect={(value) => setSelectedRecordingId(value.id)} />
          ))}
        </div>
      </section>
    </div>
  );
}
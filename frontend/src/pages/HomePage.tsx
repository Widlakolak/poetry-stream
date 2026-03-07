import { useEffect, useMemo, useState } from "react";
import { useLocation, useNavigate } from "react-router-dom";
import { poetryApi } from "../api/poetryApi";
import type { Actor, Poet, Poem, Recording } from "../types/domain";
import RecordingPlayer from "../components/RecordingPlayer";
import PersonCard from "../components/PersonCard";
import RecordingCard from "../components/RecordingCard";

function pickRandom<T>(items: T[]): T | null {
  if (!items.length) {
    return null;
  }

  const index = Math.floor(Math.random() * items.length);
  return items[index];
}

export default function HomePage() {
  const navigate = useNavigate();
  const location = useLocation();

  const [recordings, setRecordings] = useState<Recording[]>([]);
  const [poets, setPoets] = useState<Poet[]>([]);
  const [actors, setActors] = useState<Actor[]>([]);
  const [poems, setPoems] = useState<Poem[]>([]);
  const [selectedRecording, setSelectedRecording] = useState<Recording | null>(null);
  const [error, setError] = useState<string | null>(null);

  useEffect(() => {
    Promise.all([poetryApi.getRecordings(), poetryApi.getPoets(), poetryApi.getActors(), poetryApi.getPoems()])
      .then(([recordingPage, poetData, actorData, poemData]) => {
        setRecordings(recordingPage.content);
        setPoets(poetData);
        setActors(actorData);
        setPoems(poemData);
        setSelectedRecording(pickRandom(recordingPage.content));
      })
      .catch((err: Error) => setError(err.message));
  }, []);

  useEffect(() => {
    if (location.hash === "#today") {
      document.getElementById("today")?.scrollIntoView({ behavior: "smooth", block: "start" });
    }
  }, [location.hash]);

  const poemById = useMemo(() => new Map(poems.map((poem) => [poem.id, poem])), [poems]);
  const poetById = useMemo(() => new Map(poets.map((poet) => [poet.id, poet])), [poets]);
  const actorById = useMemo(() => new Map(actors.map((actor) => [actor.id, actor])), [actors]);

  const todayMeta = useMemo(() => {
    if (!selectedRecording) {
      return undefined;
    }

    const poem = poemById.get(selectedRecording.poemId);
    const poetName = poem ? poetById.get(poem.poetId)?.name ?? "Nieznany autor" : "Nieznany autor";
    const actorName = actorById.get(selectedRecording.actorId)?.name ?? "Nieznany lektor";
    return `${selectedRecording.title} - autor ${poetName} - czyta ${actorName}`;
  }, [selectedRecording, poemById, poetById, actorById]);

  const handleOpenPlaylist = (recording: Recording) => {
    navigate(`/playlists/${recording.id}`);
  };

  return (
    <div className="home-snap-scroll">
      {error && <p className="error-text">{error}</p>}

      <section id="today" className="home-panel">
        <div className="panel panel-spacious">
          <RecordingPlayer
            key={selectedRecording?.id ?? "home-player"}
            recording={selectedRecording}
            embedded
            heading="Wiersz na dziś"
            description="Losowo wybrany utwór z bazy."
            titleOverride={todayMeta}
          />

        </div>
      </section>

      <section className="home-panel">
        <div className="panel panel-spacious">
          <header className="panel-header">
            <h2>Poeci</h2>
          </header>
          <div className="horizontal-scroll">
            {poets.map((poet) => (
              <PersonCard
                key={poet.id}
                name={poet.name}
                subtitle={`${poet.birthYear}${poet.deathYear ? ` - ${poet.deathYear}` : ""}`}
                photoUrl={poet.photoUrl}
                to={`/poets/${poet.id}`}
              />
            ))}
          </div>
        </div>
      </section>

      <section className="home-panel">
        <div className="panel panel-spacious">
          <header className="panel-header">
            <h2>Artyści</h2>
          </header>
          <div className="horizontal-scroll">
            {actors.map((actor) => (
              <PersonCard key={actor.id} name={actor.name} photoUrl={actor.photoUrl} to={`/actors/${actor.id}`} />
            ))}
          </div>
        </div>
      </section>

      <section className="home-panel">
        <div className="panel panel-spacious">
          <header className="panel-header">
            <h2>Wybrane dla Ciebie</h2>
          </header>
          <div className="horizontal-scroll">
            {recordings.map((recording) => (
              <div key={recording.id}>
                <RecordingCard recording={recording} onSelect={handleOpenPlaylist} />
                <p className="tile-subtitle">{poemById.get(recording.poemId)?.title ?? "Wiersz"}</p>
              </div>
            ))}
          </div>
        </div>
      </section>
    </div>
  );
}
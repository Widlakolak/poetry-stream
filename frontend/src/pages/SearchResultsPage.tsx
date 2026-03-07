import { useEffect, useMemo, useState } from "react";
import { Link, useSearchParams } from "react-router-dom";
import { poetryApi } from "../api/poetryApi";
import type { Actor, Poem, Poet, Recording } from "../types/domain";

export default function SearchResultsPage() {
  const [searchParams] = useSearchParams();
  const query = (searchParams.get("q") ?? "").toLowerCase().trim();

  const [recordings, setRecordings] = useState<Recording[]>([]);
  const [poems, setPoems] = useState<Poem[]>([]);
  const [poets, setPoets] = useState<Poet[]>([]);
  const [actors, setActors] = useState<Actor[]>([]);
  const [error, setError] = useState<string | null>(null);

  useEffect(() => {
    Promise.all([poetryApi.getRecordings(), poetryApi.getPoems(), poetryApi.getPoets(), poetryApi.getActors()])
      .then(([recordingPage, poemsData, poetsData, actorsData]) => {
        setRecordings(recordingPage.content);
        setPoems(poemsData);
        setPoets(poetsData);
        setActors(actorsData);
      })
      .catch((err: Error) => setError(err.message));
  }, []);

  const poemById = useMemo(() => new Map(poems.map((item) => [item.id, item])), [poems]);
  const poetById = useMemo(() => new Map(poets.map((item) => [item.id, item])), [poets]);
  const actorById = useMemo(() => new Map(actors.map((item) => [item.id, item])), [actors]);

  const filtered = useMemo(() => {
    if (!query) {
      return [];
    }

    return recordings.filter((recording) => {
      const poem = poemById.get(recording.poemId);
      const poet = poem ? poetById.get(poem.poetId) : undefined;
      const actor = actorById.get(recording.actorId);

      return [recording.title, poem?.title, poet?.name, actor?.name]
        .filter(Boolean)
        .some((value) => value!.toLowerCase().includes(query));
    });
  }, [actorById, poemById, poetById, query, recordings]);

  return (
    <section className="panel">
      <header className="panel-header">
        <h2>Wyniki wyszukiwania</h2>
        <p>Fraza: {query || "(pusta)"}</p>
      </header>

      {error && <p className="error-text">{error}</p>}

      <div className="library-list">
        {filtered.map((recording) => {
          const poem = poemById.get(recording.poemId);
          const poet = poem ? poetById.get(poem.poetId) : undefined;
          const actor = actorById.get(recording.actorId);

          return (
            <article key={recording.id} className="library-row">
              <div>
                <h3>{poem?.title ?? recording.title}</h3>
              </div>
              <p>{poet ? <Link to={`/poets/${poet.id}`}>{poet.name}</Link> : "—"}</p>
              <p>{actor ? <Link to={`/actors/${actor.id}`}>{actor.name}</Link> : "—"}</p>
              <p>
                <Link to={`/playlists/${recording.id}`}>Otwórz player</Link>
              </p>
            </article>
          );
        })}

        {!filtered.length && <p className="muted">Brak wyników dla podanej frazy.</p>}
      </div>
    </section>
  );
}
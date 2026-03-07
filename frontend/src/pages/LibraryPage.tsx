import { useEffect, useMemo, useState } from "react";
import { Link } from "react-router-dom";
import { poetryApi } from "../api/poetryApi";
import type { Actor, Poem, Poet, Recording } from "../types/domain";

type SortKey = "poet" | "actor" | "poem" | "year" | "epoch";
type SortOrder = "asc" | "desc";

interface LibraryRow {
  recording: Recording;
  poem?: Poem;
  poet?: Poet;
  actor?: Actor;
  year: number;
  epoch: string;
}

function inferEpoch(year: number): string {
  if (year < 1822) return "Oświecenie / wcześniej";
  if (year < 1864) return "Romantyzm";
  if (year < 1918) return "Pozytywizm / Młoda Polska";
  if (year < 1945) return "Dwudziestolecie";
  return "Współczesność";
}

export default function LibraryPage() {
  const [recordings, setRecordings] = useState<Recording[]>([]);
  const [poems, setPoems] = useState<Poem[]>([]);
  const [poets, setPoets] = useState<Poet[]>([]);
  const [actors, setActors] = useState<Actor[]>([]);
  const [sortKey, setSortKey] = useState<SortKey>("poet");
  const [sortOrder, setSortOrder] = useState<SortOrder>("asc");
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

  const rows = useMemo<LibraryRow[]>(() => {
    const poemById = new Map(poems.map((item) => [item.id, item]));
    const poetById = new Map(poets.map((item) => [item.id, item]));
    const actorById = new Map(actors.map((item) => [item.id, item]));

    return recordings.map((recording) => {
      const poem = poemById.get(recording.poemId);
      const poet = poem ? poetById.get(poem.poetId) : undefined;
      const actor = actorById.get(recording.actorId);
      const year = poet?.birthYear ?? 0;

      return {
        recording,
        poem,
        poet,
        actor,
        year,
        epoch: inferEpoch(year),
      };
    });
  }, [recordings, poems, poets, actors]);

  const sortedRows = useMemo(() => {
    const sorted = [...rows].sort((left, right) => {
      switch (sortKey) {
        case "poet":
          return (left.poet?.name ?? "").localeCompare(right.poet?.name ?? "");
        case "actor":
          return (left.actor?.name ?? "").localeCompare(right.actor?.name ?? "");
        case "poem":
          return (left.poem?.title ?? left.recording.title).localeCompare(right.poem?.title ?? right.recording.title);
        case "year":
          return left.year - right.year;
        case "epoch":
          return left.epoch.localeCompare(right.epoch);
      }
    });

    return sortOrder === "asc" ? sorted : sorted.reverse();
  }, [rows, sortKey, sortOrder]);

  return (
    <section className="panel">
      <header className="panel-header">
        <h2>Biblioteka</h2>
        <p>Sortowanie po poecie, aktorze, tytule, roku i epoce.</p>
        <p className="muted">Epoka jest wyznaczana heurystycznie na podstawie roku urodzenia poety.</p>
      </header>

      {error && <p className="error-text">{error}</p>}

      <div className="library-toolbar">
        <label htmlFor="sort">Sortuj po:</label>
        <select id="sort" value={sortKey} onChange={(event) => setSortKey(event.target.value as SortKey)}>
          <option value="poet">Poeta</option>
          <option value="actor">Aktor</option>
          <option value="poem">Tytuł wiersza</option>
          <option value="year">Rok</option>
          <option value="epoch">Epoka</option>
        </select>

        <label htmlFor="order">Kolejność:</label>
        <select id="order" value={sortOrder} onChange={(event) => setSortOrder(event.target.value as SortOrder)}>
          <option value="asc">Od początku (A-Z / rosnąco)</option>
          <option value="desc">Od końca (Z-A / malejąco)</option>
        </select>
      </div>

      <div className="library-list">
        {sortedRows.map((row) => (
          <article key={row.recording.id} className="library-row">
            <div>
              <h3>{row.poem?.title ?? row.recording.title}</h3>
            </div>
            <p>{row.poet ? <Link to={`/poets/${row.poet.id}`}>{row.poet.name}</Link> : "Nieznany poeta"}</p>
            <p>{row.actor ? <Link to={`/actors/${row.actor.id}`}>{row.actor.name}</Link> : "Nieznany aktor"}</p>
            <p>{row.year || "—"}</p>
            <p>{row.epoch}</p>
            <p>
              <Link to={`/playlists/${row.recording.id}`}>Otwórz</Link>
            </p>
          </article>
        ))}
      </div>
    </section>
  );
}
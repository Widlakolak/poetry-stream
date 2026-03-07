import { useState } from "react";
import type { FormEvent } from "react";

interface SearchModalProps {
  isOpen: boolean;
  onClose: () => void;
  onSubmit: (query: string) => void;
}

export default function SearchModal({ isOpen, onClose, onSubmit }: SearchModalProps) {
  const [query, setQuery] = useState("");

  if (!isOpen) {
    return null;
  }

  const handleSubmit = (event: FormEvent<HTMLFormElement>) => {
    event.preventDefault();
    if (!query.trim()) {
      return;
    }
    onSubmit(query.trim());
    setQuery("");
  };

  return (
    <div className="search-modal-backdrop" onClick={onClose}>
      <section className="search-modal" onClick={(event) => event.stopPropagation()}>
        <h2>Szukaj w PoetryStream</h2>
        <p>Wpisz tytuł wiersza, poetę albo aktora.</p>

        <form onSubmit={handleSubmit} className="search-form">
          <input value={query} onChange={(event) => setQuery(event.target.value)} placeholder="np. Mickiewicz" autoFocus />
          <button type="submit">Enter</button>
        </form>

        <button className="search-close" onClick={onClose}>
          Zamknij
        </button>
      </section>
    </div>
  );
}
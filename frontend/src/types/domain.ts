export interface Poet {
  id: string;
  name: string;
  bio: string;
  birthYear: number;
  deathYear?: number;
  photoUrl: string;
}

export interface Actor {
  id: string;
  name: string;
  bio: string;
  photoUrl: string;
}

export interface Poem {
  id: string;
  title: string;
  text: string;
  poetId: string;
}

export type RecordingStatus = "PUBLISHED";

export interface Recording {
  id: string;
  title: string;
  audioUrl: string;
  startTimeSec: number;
  status: RecordingStatus;
  poemId: string;
  actorId: string;
}

export interface RecordingKaraoke {
  id: string;
  audioUrl: string;
  startTimeSec: number;
  lines: string[];
  lineCount: number;
}

export interface SpringPage<T> {
  content: T[];
  totalElements: number;
  totalPages: number;
  size: number;
  number: number;
}
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

export interface Recording {
  id: string;
  title: string;
  audioUrl: string;
  startTimeSec: number;
  status: "PUBLISHED";
  poemId: string;
  actorId: string;
}

export interface RecordingLine {
  recordingId: string;
  lineOrder: number;
  lineText: string;
}
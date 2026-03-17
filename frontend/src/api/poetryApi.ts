import { apiGet } from "./client";
import type { Actor, Poet, Poem, Recording, RecordingKaraoke, SpringPage } from "../types/domain";

export const poetryApi = {
  getRecordings: () => apiGet<SpringPage<Recording>>("/recordings"),
  getRecording: (id: string) => apiGet<Recording>(`/recordings/${id}`),
  getRecordingKaraoke: (id: string) =>
    apiGet<RecordingKaraoke>(`/recordings/${id}/karaoke`),

  getPoets: () => apiGet<Poet[]>("/poets"),
  getPoet: (id: string) => apiGet<Poet>(`/poets/${id}`),

  getPoems: () => apiGet<Poem[]>("/poems"),
  getPoem: (id: string) => apiGet<Poem>(`/poems/${id}`),

  getActors: () => apiGet<Actor[]>("/actors"),
  getActor: (id: string) => apiGet<Actor>(`/actors/${id}`),
};
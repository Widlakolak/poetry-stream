import { apiGet } from "./client";
import type { Actor, Poet, Poem, Recording, RecordingKaraoke, SpringPage } from "../types/domain";

export const poetryApi = {
  getRecordings: () => apiGet<SpringPage<Recording>>("/api/recordings"),
  getRecording: (id: string) => apiGet<Recording>(`/api/recordings/${id}`),
  getRecordingKaraoke: (id: string) => apiGet<RecordingKaraoke>(`/api/recordings/${id}/karaoke`),
  getPoets: () => apiGet<Poet[]>("/api/poets"),
  getPoet: (id: string) => apiGet<Poet>(`/api/poets/${id}`),
  getPoems: () => apiGet<Poem[]>("/api/poems"),
  getPoem: (id: string) => apiGet<Poem>(`/api/poems/${id}`),
  getActors: () => apiGet<Actor[]>("/api/actors"),
  getActor: (id: string) => apiGet<Actor>(`/api/actors/${id}`),
};
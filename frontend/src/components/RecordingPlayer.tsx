import { useEffect, useMemo, useState } from "react";
import { poetryApi } from "../api/poetryApi";
import type { Recording, RecordingKaraoke } from "../types/domain";
import KaraokePlayer from "./KaraokePlayer";

interface RecordingPlayerProps {
  recording: Recording | null;
  embedded?: boolean;
  heading?: string;
  description?: string;
  showRecordingTitle?: boolean;
  titleOverride?: string;
}

function isYouTubeUrl(url: string): boolean {
  const lower = url.toLowerCase();
  return lower.includes("youtube.com/embed/") || lower.includes("youtube.com/watch") || lower.includes("youtu.be/");
}

function toYouTubeEmbedUrl(url: string): string {
  if (url.includes("youtube.com/embed/")) {
    return url;
  }

  if (url.includes("watch?v=")) {
    const videoId = url.split("watch?v=")[1]?.split("&")[0];
    return videoId ? `https://www.youtube.com/embed/${videoId}` : url;
  }

  if (url.includes("youtu.be/")) {
    const videoId = url.split("youtu.be/")[1]?.split("?")[0];
    return videoId ? `https://www.youtube.com/embed/${videoId}` : url;
  }

  return url;
}

export default function RecordingPlayer({
  recording,
  embedded = false,
  heading = "Odtwarzacz",
  description,
  showRecordingTitle = true,
  titleOverride,
}: RecordingPlayerProps) {
  const [karaoke, setKaraoke] = useState<RecordingKaraoke | null>(null);
  const [error, setError] = useState<string | null>(null);
  const [autoPlayToken, setAutoPlayToken] = useState(0);

  const isYouTube = Boolean(recording?.audioUrl && isYouTubeUrl(recording.audioUrl));

  useEffect(() => {
    if (!recording || isYouTube) {
      return;
    }

    let cancelled = false;

    poetryApi
      .getRecordingKaraoke(recording.id)
      .then((response) => {
        if (!cancelled) {
          setError(null);
          setKaraoke(response);
        }
      })
      .catch((err: Error) => {
        if (!cancelled) {
          setError(err.message);
        }
      });

    return () => {
      cancelled = true;
    };
  }, [recording, isYouTube]);

  const title = useMemo(() => {
    if (titleOverride) {
      return titleOverride;
    }
    return recording ? recording.title : "Wybierz nagranie";
  }, [recording, titleOverride]);

  const isLoading = Boolean(recording) && !isYouTube && !karaoke && !error;

  const content = (
    <div className="recording-player-content">
      <header className="panel-header">
        <h2>{heading}</h2>
        {description ? <p>{description}</p> : null}
        {showRecordingTitle ? <p>{title}</p> : null}
      </header>

      {!recording ? <p className="muted">Brak nagrań opublikowanych przez backend.</p> : null}

      {recording && !isYouTube ? (
        <button className="play-trigger" onClick={() => setAutoPlayToken((current) => current + 1)}>
          Odtwórz
        </button>
      ) : null}

      {isLoading ? <p className="muted">Ładowanie karaoke...</p> : null}
      {error ? <p className="error-text">{error}</p> : null}

      {recording && isYouTube ? (
        <div className="youtube-frame-wrap">
          <iframe
            className="youtube-frame"
            src={toYouTubeEmbedUrl(recording.audioUrl)}
            title={recording.title}
            allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share"
            referrerPolicy="strict-origin-when-cross-origin"
            allowFullScreen
          />
        </div>
      ) : null}

      {karaoke && !error && !isYouTube ? (
        <KaraokePlayer
          mediaUrl={karaoke.audioUrl}
          startTimeSec={karaoke.startTimeSec}
          lines={karaoke.lines}
          autoPlayToken={autoPlayToken}
        />
      ) : null}
    </div>
  );

  if (embedded) {
    return content;
  }

  return <section className="panel panel-player">{content}</section>;
}
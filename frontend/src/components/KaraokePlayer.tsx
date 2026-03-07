import { useEffect, useMemo, useRef, useState } from "react";

interface KaraokePlayerProps {
  mediaUrl: string;
  startTimeSec: number;
  lines: string[];
  autoPlayToken: number;
}

const VIDEO_EXTENSIONS = [".mp4", ".webm", ".ogg", ".mov", ".m4v", ".m3u8"];
const WINDOW_SIZE = 7;

function isVideoUrl(url: string): boolean {
  const lower = url.toLowerCase();
  return VIDEO_EXTENSIONS.some((extension) => lower.includes(extension));
}

export default function KaraokePlayer({ mediaUrl, startTimeSec, lines, autoPlayToken }: KaraokePlayerProps) {
  const audioRef = useRef<HTMLAudioElement>(null);
  const videoRef = useRef<HTMLVideoElement>(null);
  const [duration, setDuration] = useState(0);
  const [currentLineIndex, setCurrentLineIndex] = useState(0);
  const [fullTextOpen, setFullTextOpen] = useState(false);

  const videoMode = useMemo(() => isVideoUrl(mediaUrl), [mediaUrl]);

  useEffect(() => {
    const media = videoMode ? videoRef.current : audioRef.current;
    if (!media) {
      return;
    }

    const handleLoadedMetadata = () => {
      setDuration(media.duration || 0);
    };

    const handleTimeUpdate = () => {
      const elapsed = media.currentTime - startTimeSec;
      if (elapsed < 0) {
        setCurrentLineIndex(0);
        return;
      }

      const totalLines = Math.max(lines.length, 1);
      const effectiveDuration = Math.max(duration - startTimeSec, 0.01);
      const timePerLine = effectiveDuration / totalLines;
      const index = Math.min(Math.floor(elapsed / timePerLine), Math.max(lines.length - 1, 0));
      setCurrentLineIndex(index);
    };

    media.addEventListener("loadedmetadata", handleLoadedMetadata);
    media.addEventListener("timeupdate", handleTimeUpdate);

    return () => {
      media.removeEventListener("loadedmetadata", handleLoadedMetadata);
      media.removeEventListener("timeupdate", handleTimeUpdate);
    };
  }, [duration, lines.length, startTimeSec, videoMode]);

  useEffect(() => {
    const media = videoMode ? videoRef.current : audioRef.current;
    if (!media || autoPlayToken === 0) {
      return;
    }

    media.currentTime = startTimeSec;
    media.play().catch(() => undefined);
  }, [autoPlayToken, startTimeSec, videoMode]);

  const windowStart = Math.max(0, currentLineIndex - Math.floor(WINDOW_SIZE / 2));
  const windowEnd = Math.min(lines.length, windowStart + WINDOW_SIZE);
  const adjustedStart = Math.max(0, windowEnd - WINDOW_SIZE);
  const visibleLines = lines.slice(adjustedStart, windowEnd);

  return (
    <div className="karaoke-player">
      {videoMode ? (
        <video ref={videoRef} src={mediaUrl} controls className="karaoke-video" preload="metadata" />
      ) : (
        <audio ref={audioRef} src={mediaUrl} controls className="karaoke-audio" preload="metadata" />
      )}

      {!videoMode && (
        <>
          <button className="karaoke-window" onClick={() => setFullTextOpen(true)} title="Kliknij, aby zobaczyć cały tekst">
            {visibleLines.map((line, idx) => {
              const absoluteIndex = adjustedStart + idx;
              const distance = Math.abs(absoluteIndex - currentLineIndex);
              const alpha = distance <= 1 ? 1 : Math.max(1 - (distance - 1) * 0.32, 0.2);

              return (
                <p
                  className={`karaoke-line ${absoluteIndex === currentLineIndex ? "is-active" : ""}`}
                  key={`${line}-${absoluteIndex}`}
                  style={{ opacity: alpha }}
                >
                  {line}
                </p>
              );
            })}
          </button>

          {fullTextOpen && (
            <div className="search-modal-backdrop" onClick={() => setFullTextOpen(false)}>
              <section className="search-modal full-text-modal" onClick={(event) => event.stopPropagation()}>
                <h2>Pełny tekst</h2>
                <div className="full-text-content">
                  {lines.map((line, idx) => (
                    <p key={`${line}-${idx}`} className={idx === currentLineIndex ? "is-active" : ""}>
                      {line}
                    </p>
                  ))}
                </div>
                <button className="search-close" onClick={() => setFullTextOpen(false)}>
                  Zamknij
                </button>
              </section>
            </div>
          )}
        </>
      )}
    </div>
  );
}
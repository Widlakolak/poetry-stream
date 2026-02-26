import React, { useEffect, useRef, useState } from "react";

type KaraokePlayerProps = {
  audioUrl: string;
  startTimeSec: number;
  lines: string[];
  lineCount: number;
};

const KaraokePlayer: React.FC<KaraokePlayerProps> = ({ audioUrl, startTimeSec, lines, lineCount }) => {
  const audioRef = useRef<HTMLAudioElement>(null);
  const containerRef = useRef<HTMLDivElement>(null);
  const [currentLineIndex, setCurrentLineIndex] = useState(0);

  useEffect(() => {
    const audio = audioRef.current;
    if (!audio) return;

    const handleLoaded = () => {
      audio.currentTime = startTimeSec;
      audio.play().catch(err => console.log("Autoplay error:", err));
    };
    audio.addEventListener("loadedmetadata", handleLoaded);

    const interval = setInterval(() => {
      if (!audio || audio.duration === 0) return;
      const elapsed = audio.currentTime - startTimeSec;
      if (elapsed >= 0) {
        const timePerLine = (audio.duration - startTimeSec) / lineCount;
        const index = Math.min(Math.floor(elapsed / timePerLine), lines.length - 1);
        setCurrentLineIndex(index);

        if (containerRef.current) {
          const currentLineEl = containerRef.current.children[index] as HTMLElement;
          if (currentLineEl) {
            currentLineEl.scrollIntoView({ behavior: "smooth", block: "center" });
          }
        }
      }
    }, 100);

    return () => {
      clearInterval(interval);
      audio.removeEventListener("loadedmetadata", handleLoaded);
    };
  }, [startTimeSec, lines, lineCount]);

  return (
    <div>
      <audio ref={audioRef} src={audioUrl} controls style={{ width: "100%", marginBottom: "10px" }} />

      <div
        ref={containerRef}
        style={{
          maxHeight: "300px",
          overflowY: "auto",
          border: "1px solid #ddd",
          borderRadius: "8px",
          padding: "10px",
          background: "#f9f9f9",
        }}
      >
        {lines.map((line, idx) => (
          <p
            key={idx}
            style={{
              margin: "5px 0",
              fontWeight: idx === currentLineIndex ? "bold" : "normal",
              color: idx === currentLineIndex ? "#1DB954" : "#333",
              fontSize: idx === currentLineIndex ? "1.2em" : "1em",
              transition: "all 0.2s",
            }}
          >
            {line}
          </p>
        ))}
      </div>
    </div>
  );
};

export default KaraokePlayer;
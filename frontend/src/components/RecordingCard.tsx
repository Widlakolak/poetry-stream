import type { Recording } from "../types/domain";

interface RecordingCardProps {
  recording: Recording;
  onSelect: (recording: Recording) => void;
}

export default function RecordingCard({ recording, onSelect }: RecordingCardProps) {
  return (
    <button className="tile recording-tile" onClick={() => onSelect(recording)}>
      <div className="recording-thumbnail" aria-hidden="true">
        ♪
      </div>
      <h3>{recording.title}</h3>
      <p>{recording.status}</p>
    </button>
  );
}
const BASE_URL = import.meta.env.VITE_API_URL ?? "http://localhost:8080";

export async function apiGet<T>(path: string): Promise<T> {
  const response = await fetch(`${BASE_URL}${path}`, {
    headers: {
      Accept: "application/json",
    },
  });

  if (!response.ok) {
    const errorText = await response.text();
    throw new Error(`GET ${path} failed (${response.status}): ${errorText || response.statusText}`);
  }

  return (await response.json()) as T;
}
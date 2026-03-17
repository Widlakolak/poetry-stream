function apiUrl(path: string): string {
  const normalized = path.startsWith("/") ? path : `/${path}`;
  return normalized.startsWith("/api") ? normalized : `/api${normalized}`;
}

export async function apiGet<T>(path: string): Promise<T> {
  const url = apiUrl(path);

  const response = await fetch(url, {
    method: "GET",
    headers: {
      Accept: "application/json",
    },
  });

  if (!response.ok) {
    const errorText = await response.text();
    throw new Error(
      `GET ${url} failed (${response.status}): ${errorText || response.statusText}`
    );
  }

  const contentType = response.headers.get("content-type") ?? "";

  if (!contentType.includes("json")) {
    const preview = (await response.text()).slice(0, 200);
    throw new Error(
      `GET ${url} returned non-JSON response (${contentType}): ${preview}`
    );
  }

  return (await response.json()) as T;
}
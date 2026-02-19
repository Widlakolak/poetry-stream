# PoetryStream

PoetryStream to edukacyjna platforma cyfrowa popularyzująca poezję poprzez profesjonalne interpretacje aktorskie oraz interaktywne formy odbioru literatury.

Projekt łączy technologię Java + Spring Boot z frontem React, umożliwiając słuchanie wierszy, wyświetlanie zsynchronizowanego tekstu i poznawanie sylwetek autorów i aktorów.

---

## 🎯 Misja

- Promocja poezji i literatury  
- Wsparcie aktorów i twórców  
- Edukacja literacka w środowisku cyfrowym  
- Integracja środowiska kultury i edukacji  

Aktualny status: **MVP / Proof of Concept** (backend + podstawowy frontend)

---

## 🧱 Architektura MVP

**Backend:**

- Java 21  
- Spring Boot 4.0.2  
- REST API do nagrań, autorów i aktorów  
- Spring Data JPA + Hibernate  
- H2 Database (file-based)  
- Flyway (wersjonowane migracje)  
- Gradle (Groovy DSL)  

**Frontend:** (w trakcie)

- React 18 + TypeScript  
- Vite  
- Tailwind CSS (minimalistyczny styl Mudita-like)  
- Howler.js / native Audio API (odtwarzacz)  

---

## 🔊 Funkcje MVP (Etap 1)

- Lista nagrań (wiersze czytane przez aktorów)  
- Profil aktora i autora (bio, zdjęcia, nagrania)  
- Odtwarzacz audio  
- Synchronizowany tekst  
- Publiczny dostęp bez logowania  

---

## 🧱 Struktura repozytorium (MVP w Javie + React)

poetry-stream/  
├─ backend/                           # Spring Boot backend  
│  ├─ build.gradle                    # konfiguracja Gradle  
│  ├─ src/  
│  │  ├─ main/  
│  │  │  ├─ java/com/poetrystream/  
│  │  │  │  ├─ PoetryStreamApplication.java     # klasa startowa  
│  │  │  │  ├─ controller/  
│  │  │  │  │  └─ RecordingController.java  
│  │  │  │  ├─ domain/  
│  │  │  │  │  └─ Recording.java  
│  │  │  │  └─ service/  
│  │  │  │     └─ RecordingService.java  
│  │  │  └─ resources/  
│  │  │     ├─ application.properties     # konfiguracja (H2, Flyway)  
│  │  │     └─ db/migration/              # migracje Flyway (V1, V2...)  
│  └─ gradlew, gradlew.bat, settings.gradle  
│  
├─ frontend/                          # React + TypeScript  
│  ├─ src/  
│  │  ├─ App.tsx  
│  │  ├─ index.tsx  
│  │  └─ components/  
│  │     └─ RecordingPlayer.tsx  
│  ├─ package.json  
│  ├─ tsconfig.json  
│  └─ vite.config.ts  
│  
├─ .gitignore  
└─ README.md  

---

## Jak uruchomić lokalnie

### Backend

```bash
cd backend
./gradlew bootRun
```

- API dostępne na: http://localhost:8080  
- H2 Console: http://localhost:8080/h2-console  
(JDBC URL: jdbc:h2:file:./data/poetrydb, user: sa, pass: )  

### Frontend

```bash
cd frontend
npm install
npm run dev
```

- Otwórz: http://localhost:5173  

---

## 🚀 ROADMAPA ROZWOJU

### 📍 ETAP 1 – Prototyp

- 3–5 nagrań, podstawowe CRUD dla nagrań  
- Profile aktorów i autorów  
- Synchronizowany tekst  
- Estetyczny minimalistyczny interfejs: lista + odtwarzacz audio  
- Deployment online, DTO + walidacja + MapStruct  

---

### 📍 ETAP 2 – Rozszerzenie edukacyjne (3–9 miesięcy)

**Cel:** wzmocnienie komponentu edukacyjnego.  
**Nowe funkcje:**  

**👤 Profile autorów**  
- Biografia  
- Epoka literacka  
- Najważniejsze dzieła  
- Kontekst historyczny  
- Powiązane nagrania  

**🎭 Rozbudowane profile aktorów**  
- Portfolio nagrań  
- Informacje o współpracach  
- Linki do teatrów  

**🧠 Quizy literackie**  
- Quizy dotyczące: autorów, epok literackich, interpretacji utworów  
- Tryb edukacyjny dla szkół  
- Wyniki i statystyki użytkownika  

**🗂 Kategorie tematyczne**  
- Epoki literackie  
- Motywy (miłość, patriotyzm, natura)  
- Poezja dla dzieci  

---

### 📍 ETAP 3 – Komponent społecznościowy (9–18 miesięcy)

**Cel:** budowa społeczności wokół literatury.  

- 💬 Tablica społecznościowa  
  - Komentarze do nagrań  
  - Dyskusje interpretacyjne  
  - Pytania edukacyjne  

- ⭐ System ocen  
  - Ocena interpretacji  
  - Polecane nagrania  

- 📚 Playlisty tematyczne  
  - Poezja romantyczna  
  - Wiersze dla dzieci  
  - Interpretacje klasyczne / nowoczesne  

- 👤 Konta użytkowników  
  - Zapisywanie ulubionych nagrań  
  - Historia odsłuchań  
  - Postępy w quizach  

---

### 📍 ETAP 4 – Integracja z instytucjami kultury

**Cel:** realny wpływ społeczny.  

- 🎭 Informacje o spektaklach teatralnych  
  - Kalendarium wydarzeń  
  - Premiery  
  - Linki do teatrów  
  - Współpraca z instytucjami  

- 📍 Mapa wydarzeń literackich  
  - Spotkania autorskie  
  - Wieczory poezji  
  - Warsztaty  

---

### 📍 ETAP 5 – Transmisje na żywo (Live)

**Cel:** interaktywna kultura online.  

- 🔴 Transmisje live:  
  - Czytania w bibliotekach  
  - Odczyty w szkołach  
  - Wydarzenia w teatrach  
  - Poezja dla dzieci  

- Możliwości:  
  - Chat podczas transmisji  
  - Archiwizacja nagrań  
  - Specjalne cykle tematyczne  

---

### 📍 ETAP 6 – Wersja mobilna

- Aplikacja React Native  
- Tryb offline  
- Powiadomienia o wydarzeniach  
- Integracja z kalendarzem  

---

### 📍 ETAP 7 – Precyzyjna synchronizacja tekstu

- Timestampy dla wersów  
- Synchronizacja słowo-po-słowie  
- Edytor synchronizacji dla administratora  

---

## 💡 Docelowa wizja

PoetryStream jako:

- cyfrowa biblioteka poezji audio  
- platforma edukacyjna dla szkół  
- przestrzeń promocji aktorów  
- hub wydarzeń literackich  
- narzędzie do transmisji wydarzeń kulturalnych  

---

## 📈 Potencjalne modele rozwoju

- Współpraca z bibliotekami  
- Partnerstwa z teatrami  
- Patronaty instytucji kultury  
- Subskrypcja premium  
- Granty ministerialne i europejskie  

---

## 📚 Status projektu

- Aktualna faza: MVP (Proof of Concept)  
- Cel: Rozwój do pełnoprawnej platformy edukacyjno-kulturalnej
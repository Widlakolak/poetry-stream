# PoetryStream

PoetryStream to edukacyjna platforma cyfrowa popularyzująca poezję poprzez profesjonalne interpretacje aktorskie oraz interaktywne formy odbioru literatury.

Projekt łączy technologię Java + Spring Boot z frontem React, umożliwiając słuchanie wierszy, wyświetlanie zsynchronizowanego tekstu i poznawanie sylwetek autorów i aktorów.

---

## 📌 Project Overview

Educational audio-literature streaming platform built with Java 21 & Spring Boot 4.0.2.

PoetryStream is a modular backend-driven platform designed to deliver high-quality audio recordings of poetry and literary works.

---

## 🎯 Misja

- Popularyzacja poezji i literatury w środowisku cyfrowym  
- Wsparcie twórców i aktorów  
- Tworzenie nowoczesnego narzędzia edukacyjnego  
- Integracja środowiska kultury i edukacji  

Status projektu: **MVP / Proof of Concept**

---

## 🧱 Architektura MVP

### Backend
- Java 21  
- Spring Boot 4.0.2  
- REST API (nagrania, autorzy, aktorzy)  
- Spring Data JPA + Hibernate  
- H2 (środowisko developerskie)  
- Flyway (wersjonowanie migracji)  
- Gradle (Groovy DSL)  

Warstwowa architektura:
controller → service → repository → domain + DTO + mapper

### Frontend (w trakcie rozwoju)
- React 18 + TypeScript  
- Vite  
- Tailwind CSS  
- Audio API / Howler.js  

---

## 🔊 Funkcjonalności MVP

- Lista nagrań (wiersze czytane przez aktorów)  
- Profil autora i aktora  
- Odtwarzacz audio  
- Synchronizowany tekst  
- Publiczny dostęp bez logowania  

---

## 📡 API & Dokumentacja

Backend udostępnia REST API - pełny CRUD z MapStruct dla Actor, Poet, Poem i Recording.

Planowane rozszerzenia:

- Integracja z OpenAPI / Swagger UI  
- Automatyczna dokumentacja endpointów  
- Standaryzacja odpowiedzi (ResponseEntity + global handler)  
- Wersjonowanie API (np. /api/v1)

Docelowo API będzie gotowe do integracji z aplikacją mobilną oraz usługami zewnętrznymi.

---

## 🔐 Bezpieczeństwo (planowane)

Wersja MVP działa bez uwierzytelniania (publiczny dostęp do treści).

W kolejnych etapach planowane:

- JWT Authentication  
- Role użytkowników (ADMIN / EDUKATOR / USER)  
- Ochrona endpointów administracyjnych  
- Walidacja danych wejściowych  
- Globalny handler wyjątków  

---

## 🌍 Internacjonalizacja (i18n)

Platforma projektowana jest z myślą o obsłudze wielu języków.

Planowane rozwiązania:

- Backend: Spring MessageSource  
- Frontend: mechanizm i18n (React i18next)  
- Możliwość dynamicznego przełączania języka  
- Wsparcie dla środowisk polonijnych  

---

## 🧩 Skalowalność i kierunek architektoniczny

- Modularny monolit z podziałem domenowym  
- Migracja z H2 do PostgreSQL w środowisku produkcyjnym  
- Konteneryzacja (Docker)  
- Możliwość integracji z zewnętrznym storage dla plików audio  
- Przygotowanie pod przyszłe wydzielenie mikroserwisów

---

## ▶ Uruchomienie lokalne

### Backend
```bash
cd backend
./gradlew bootRun
```

- API dostępne na: http://localhost:8080  
- H2 Console: http://localhost:8080/h2-console  
(JDBC URL: jdbc:h2:file:./data/poetrydb, user: sa, pass: )  

---

### Frontend
```bash
cd frontend
npm install
npm run dev
```

- Frontend: http://localhost:5173  

---

## 🚀 Plan rozwoju

### Etap 1 – Stabilizacja MVP
- Ukończenie integracji frontend–backend  
- Deployment środowiska testowego&emsp;&emsp;&emsp;👈
- Walidacja danych, DTO, MapStruct  
- CORS Config
- Wyjątki - GlobalExceptionHandler, ResourceNotFoundException
- Przygotowanie pod PostgreSQL  

---

### Etap 2 – Rozszerzenie edukacyjne
- Rozbudowane profile autorów i aktorów  
- Kategorie tematyczne i epoki literackie  
- Quizy literackie dla szkół  
- Panel administracyjny  

---

### Etap 3 – Komponent społecznościowy
- Konta użytkowników  
- System ocen i komentarzy  
- Playlisty tematyczne  
- Historia odsłuchań i postępy  

---

### Etap 4 – Integracja instytucjonalna
- Informacje o wydarzeniach literackich  
- Współpraca z teatrami i bibliotekami  
- Kalendarz wydarzeń  

---

### Etap 5 – Transmisje na żywo
- Streaming wydarzeń literackich  
- Archiwizacja transmisji  
- Interakcja użytkowników (chat)  

---

### Etap 6 – Wersja mobilna
- Aplikacja React Native  
- Tryb offline  
- Powiadomienia o wydarzeniach  

---

## 🌍 Kierunek rozwoju

PoetryStream projektowany jest jako:

- cyfrowa biblioteka poezji audio  
- platforma edukacyjna dla szkół  
- narzędzie promocji aktorów i twórców  
- przestrzeń współpracy z instytucjami kultury  
- platforma możliwa do wdrożenia również dla środowisk polonijnych  

---

## 🧱 Struktura repozytorium (MVP w Javie + React)

poetry-stream/\
├─&nbsp;&nbsp;backend/&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;#&nbsp;Spring&nbsp;Boot&nbsp;backend\
│&nbsp;&nbsp;&nbsp;&nbsp;├─&nbsp;&nbsp;build.gradle&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;#&nbsp;konfiguracja&nbsp;Gradle\
│&nbsp;&nbsp;&nbsp;&nbsp;├─&nbsp;&nbsp;src/\
│&nbsp;&nbsp;&nbsp;&nbsp;│&nbsp;&nbsp;&nbsp;&nbsp;├─&nbsp;&nbsp;main/\
│&nbsp;&nbsp;&nbsp;&nbsp;│&nbsp;&nbsp;&nbsp;&nbsp;│&nbsp;&nbsp;&nbsp;&nbsp;├─&nbsp;&nbsp;java/com/poetrystream/backend/\
│&nbsp;&nbsp;&nbsp;&nbsp;│&nbsp;&nbsp;&nbsp;&nbsp;│&nbsp;&nbsp;&nbsp;&nbsp;│&nbsp;&nbsp;&nbsp;&nbsp;├─&nbsp;&nbsp;BackendApplication.java&emsp;&emsp;#&nbsp;&nbsp;klasa&nbsp;&nbsp;startowa\
│&nbsp;&nbsp;&nbsp;&nbsp;│&nbsp;&nbsp;&nbsp;&nbsp;│&nbsp;&nbsp;&nbsp;&nbsp;│&nbsp;&nbsp;&nbsp;&nbsp;├─&nbsp;&nbsp;controller/\
│&nbsp;&nbsp;&nbsp;&nbsp;│&nbsp;&nbsp;&nbsp;&nbsp;│&nbsp;&nbsp;&nbsp;&nbsp;│&nbsp;&nbsp;&nbsp;&nbsp;│&nbsp;&nbsp;&nbsp;&nbsp;├─&nbsp;&nbsp;ActorController.java\
│&nbsp;&nbsp;&nbsp;&nbsp;│&nbsp;&nbsp;&nbsp;&nbsp;│&nbsp;&nbsp;&nbsp;&nbsp;│&nbsp;&nbsp;&nbsp;&nbsp;│&nbsp;&nbsp;&nbsp;&nbsp;├─&nbsp;&nbsp;PoetController.java\
│&nbsp;&nbsp;&nbsp;&nbsp;│&nbsp;&nbsp;&nbsp;&nbsp;│&nbsp;&nbsp;&nbsp;&nbsp;│&nbsp;&nbsp;&nbsp;&nbsp;│&nbsp;&nbsp;&nbsp;&nbsp;├─&nbsp;&nbsp;PoemController.java\
│&nbsp;&nbsp;&nbsp;&nbsp;│&nbsp;&nbsp;&nbsp;&nbsp;│&nbsp;&nbsp;&nbsp;&nbsp;│&nbsp;&nbsp;&nbsp;&nbsp;│&nbsp;&nbsp;&nbsp;&nbsp;└─&nbsp;&nbsp;RecordingController.java\
│&nbsp;&nbsp;&nbsp;&nbsp;│&nbsp;&nbsp;&nbsp;&nbsp;│&nbsp;&nbsp;&nbsp;&nbsp;│&nbsp;&nbsp;&nbsp;&nbsp;├─&nbsp;&nbsp;domain/\
│&nbsp;&nbsp;&nbsp;&nbsp;│&nbsp;&nbsp;&nbsp;&nbsp;│&nbsp;&nbsp;&nbsp;&nbsp;│&nbsp;&nbsp;&nbsp;&nbsp;│&nbsp;&nbsp;&nbsp;&nbsp;├─&nbsp;&nbsp;Actor.java\
│&nbsp;&nbsp;&nbsp;&nbsp;│&nbsp;&nbsp;&nbsp;&nbsp;│&nbsp;&nbsp;&nbsp;&nbsp;│&nbsp;&nbsp;&nbsp;&nbsp;│&nbsp;&nbsp;&nbsp;&nbsp;├─&nbsp;&nbsp;Poet.java\
│&nbsp;&nbsp;&nbsp;&nbsp;│&nbsp;&nbsp;&nbsp;&nbsp;│&nbsp;&nbsp;&nbsp;&nbsp;│&nbsp;&nbsp;&nbsp;&nbsp;│&nbsp;&nbsp;&nbsp;&nbsp;├─&nbsp;&nbsp;Poem.java\
│&nbsp;&nbsp;&nbsp;&nbsp;│&nbsp;&nbsp;&nbsp;&nbsp;│&nbsp;&nbsp;&nbsp;&nbsp;│&nbsp;&nbsp;&nbsp;&nbsp;│&nbsp;&nbsp;&nbsp;&nbsp;├─&nbsp;&nbsp;Recording.java\
│&nbsp;&nbsp;&nbsp;&nbsp;│&nbsp;&nbsp;&nbsp;&nbsp;│&nbsp;&nbsp;&nbsp;&nbsp;│&nbsp;&nbsp;&nbsp;&nbsp;│&nbsp;&nbsp;&nbsp;&nbsp;└─&nbsp;&nbsp;RecordingStatus.java\
│&nbsp;&nbsp;&nbsp;&nbsp;│&nbsp;&nbsp;&nbsp;&nbsp;│&nbsp;&nbsp;&nbsp;&nbsp;│&nbsp;&nbsp;&nbsp;&nbsp;├─&nbsp;&nbsp;dto/\
│&nbsp;&nbsp;&nbsp;&nbsp;│&nbsp;&nbsp;&nbsp;&nbsp;│&nbsp;&nbsp;&nbsp;&nbsp;│&nbsp;&nbsp;&nbsp;&nbsp;│&nbsp;&nbsp;&nbsp;&nbsp;├─&nbsp;&nbsp;ActorDto.java\
│&nbsp;&nbsp;&nbsp;&nbsp;│&nbsp;&nbsp;&nbsp;&nbsp;│&nbsp;&nbsp;&nbsp;&nbsp;│&nbsp;&nbsp;&nbsp;&nbsp;│&nbsp;&nbsp;&nbsp;&nbsp;├─&nbsp;&nbsp;PoetDto.java\
│&nbsp;&nbsp;&nbsp;&nbsp;│&nbsp;&nbsp;&nbsp;&nbsp;│&nbsp;&nbsp;&nbsp;&nbsp;│&nbsp;&nbsp;&nbsp;&nbsp;│&nbsp;&nbsp;&nbsp;&nbsp;├─&nbsp;&nbsp;PoemDto.java\
│&nbsp;&nbsp;&nbsp;&nbsp;│&nbsp;&nbsp;&nbsp;&nbsp;│&nbsp;&nbsp;&nbsp;&nbsp;│&nbsp;&nbsp;&nbsp;&nbsp;│&nbsp;&nbsp;&nbsp;&nbsp;├─&nbsp;&nbsp;RecordingDto.java\
│&nbsp;&nbsp;&nbsp;&nbsp;│&nbsp;&nbsp;&nbsp;&nbsp;│&nbsp;&nbsp;&nbsp;&nbsp;│&nbsp;&nbsp;&nbsp;&nbsp;│&nbsp;&nbsp;&nbsp;&nbsp;└─&nbsp;&nbsp;RecordingKaraokeDto.java\
│&nbsp;&nbsp;&nbsp;&nbsp;│&nbsp;&nbsp;&nbsp;&nbsp;│&nbsp;&nbsp;&nbsp;&nbsp;│&nbsp;&nbsp;&nbsp;&nbsp;├─&nbsp;&nbsp;exception/\
│&nbsp;&nbsp;&nbsp;&nbsp;│&nbsp;&nbsp;&nbsp;&nbsp;│&nbsp;&nbsp;&nbsp;&nbsp;│&nbsp;&nbsp;&nbsp;&nbsp;│&nbsp;&nbsp;&nbsp;&nbsp;├─&nbsp;&nbsp;GlobalExceptionHandler.java\
│&nbsp;&nbsp;&nbsp;&nbsp;│&nbsp;&nbsp;&nbsp;&nbsp;│&nbsp;&nbsp;&nbsp;&nbsp;│&nbsp;&nbsp;&nbsp;&nbsp;│&nbsp;&nbsp;&nbsp;&nbsp;└─&nbsp;&nbsp;ResourceNotFoundException.java\
│&nbsp;&nbsp;&nbsp;&nbsp;│&nbsp;&nbsp;&nbsp;&nbsp;│&nbsp;&nbsp;&nbsp;&nbsp;│&nbsp;&nbsp;&nbsp;&nbsp;├─&nbsp;&nbsp;mapper/\
│&nbsp;&nbsp;&nbsp;&nbsp;│&nbsp;&nbsp;&nbsp;&nbsp;│&nbsp;&nbsp;&nbsp;&nbsp;│&nbsp;&nbsp;&nbsp;&nbsp;│&nbsp;&nbsp;&nbsp;&nbsp;├─&nbsp;&nbsp;ActorMapper.java\
│&nbsp;&nbsp;&nbsp;&nbsp;│&nbsp;&nbsp;&nbsp;&nbsp;│&nbsp;&nbsp;&nbsp;&nbsp;│&nbsp;&nbsp;&nbsp;&nbsp;│&nbsp;&nbsp;&nbsp;&nbsp;├─&nbsp;&nbsp;PoetMapper.java\
│&nbsp;&nbsp;&nbsp;&nbsp;│&nbsp;&nbsp;&nbsp;&nbsp;│&nbsp;&nbsp;&nbsp;&nbsp;│&nbsp;&nbsp;&nbsp;&nbsp;│&nbsp;&nbsp;&nbsp;&nbsp;├─&nbsp;&nbsp;PoemMapper.java\
│&nbsp;&nbsp;&nbsp;&nbsp;│&nbsp;&nbsp;&nbsp;&nbsp;│&nbsp;&nbsp;&nbsp;&nbsp;│&nbsp;&nbsp;&nbsp;&nbsp;│&nbsp;&nbsp;&nbsp;&nbsp;└─&nbsp;&nbsp;RecordingMapper.java\
│&nbsp;&nbsp;&nbsp;&nbsp;│&nbsp;&nbsp;&nbsp;&nbsp;│&nbsp;&nbsp;&nbsp;&nbsp;│&nbsp;&nbsp;&nbsp;&nbsp;├─&nbsp;&nbsp;repository/\
│&nbsp;&nbsp;&nbsp;&nbsp;│&nbsp;&nbsp;&nbsp;&nbsp;│&nbsp;&nbsp;&nbsp;&nbsp;│&nbsp;&nbsp;&nbsp;&nbsp;│&nbsp;&nbsp;&nbsp;&nbsp;├─&nbsp;&nbsp;ActorRepository.java\
│&nbsp;&nbsp;&nbsp;&nbsp;│&nbsp;&nbsp;&nbsp;&nbsp;│&nbsp;&nbsp;&nbsp;&nbsp;│&nbsp;&nbsp;&nbsp;&nbsp;│&nbsp;&nbsp;&nbsp;&nbsp;├─&nbsp;&nbsp;PoetRepository.java\
│&nbsp;&nbsp;&nbsp;&nbsp;│&nbsp;&nbsp;&nbsp;&nbsp;│&nbsp;&nbsp;&nbsp;&nbsp;│&nbsp;&nbsp;&nbsp;&nbsp;│&nbsp;&nbsp;&nbsp;&nbsp;├─&nbsp;&nbsp;PoemRepository.java\
│&nbsp;&nbsp;&nbsp;&nbsp;│&nbsp;&nbsp;&nbsp;&nbsp;│&nbsp;&nbsp;&nbsp;&nbsp;│&nbsp;&nbsp;&nbsp;&nbsp;│&nbsp;&nbsp;&nbsp;&nbsp;└─&nbsp;&nbsp;RecordingRepository.java\
│&nbsp;&nbsp;&nbsp;&nbsp;│&nbsp;&nbsp;&nbsp;&nbsp;│&nbsp;&nbsp;&nbsp;&nbsp;│&nbsp;&nbsp;&nbsp;&nbsp;└─&nbsp;&nbsp;service/\
│&nbsp;&nbsp;&nbsp;&nbsp;│&nbsp;&nbsp;&nbsp;&nbsp;│&nbsp;&nbsp;&nbsp;&nbsp;│&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;├─&nbsp;&nbsp;ActorService.java\
│&nbsp;&nbsp;&nbsp;&nbsp;│&nbsp;&nbsp;&nbsp;&nbsp;│&nbsp;&nbsp;&nbsp;&nbsp;│&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;├─&nbsp;&nbsp;PoetService.java\
│&nbsp;&nbsp;&nbsp;&nbsp;│&nbsp;&nbsp;&nbsp;&nbsp;│&nbsp;&nbsp;&nbsp;&nbsp;│&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;├─&nbsp;&nbsp;PoemService.java\
│&nbsp;&nbsp;&nbsp;&nbsp;│&nbsp;&nbsp;&nbsp;&nbsp;│&nbsp;&nbsp;&nbsp;&nbsp;│&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─&nbsp;&nbsp;RecordingService.java\
│&nbsp;&nbsp;&nbsp;&nbsp;│&nbsp;&nbsp;&nbsp;&nbsp;│&nbsp;&nbsp;&nbsp;&nbsp;└─&nbsp;&nbsp;resources/\
│&nbsp;&nbsp;&nbsp;&nbsp;│&nbsp;&nbsp;&nbsp;&nbsp;│&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;├─&nbsp;&nbsp;application.yaml&emsp;&emsp;&emsp;#&nbsp;&nbsp;konfiguracja&nbsp;&nbsp;(H2,&nbsp;&nbsp;Flyway)\
│&nbsp;&nbsp;&nbsp;&nbsp;│&nbsp;&nbsp;&nbsp;&nbsp;│&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─&nbsp;&nbsp;db/migration/&emsp;&emsp;&emsp;&emsp;#&nbsp;&nbsp;migracje&nbsp;&nbsp;Flyway&nbsp;&nbsp;(V1,&nbsp;&nbsp;V2...)\
│&nbsp;&nbsp;&nbsp;&nbsp;└─&nbsp;&nbsp;gradlew,&nbsp;&nbsp;gradlew.bat,&nbsp;&nbsp;settings.gradle\
│\
├─&nbsp;&nbsp;frontend/&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;#&nbsp;&nbsp;React&nbsp;&nbsp;+&nbsp;&nbsp;TypeScript\
│&nbsp;&nbsp;&nbsp;&nbsp;├─&nbsp;&nbsp;src/\
│&nbsp;&nbsp;&nbsp;&nbsp;│&nbsp;&nbsp;&nbsp;&nbsp;├─&nbsp;&nbsp;App.tsx\
│&nbsp;&nbsp;&nbsp;&nbsp;│&nbsp;&nbsp;&nbsp;&nbsp;├─&nbsp;&nbsp;index.tsx\
│&nbsp;&nbsp;&nbsp;&nbsp;│&nbsp;&nbsp;&nbsp;&nbsp;└─&nbsp;&nbsp;components/\
│&nbsp;&nbsp;&nbsp;&nbsp;│&nbsp;&nbsp;&nbsp;&nbsp;└─&nbsp;&nbsp;RecordingPlayer.tsx\
│&nbsp;&nbsp;&nbsp;&nbsp;├─&nbsp;&nbsp;package.json\
│&nbsp;&nbsp;&nbsp;&nbsp;├─&nbsp;&nbsp;tsconfig.json\
│&nbsp;&nbsp;&nbsp;&nbsp;└─&nbsp;&nbsp;vite.config.ts\
│\
├─&nbsp;&nbsp;.gitignore\
└─&nbsp;&nbsp;README.md\

---

## 📈 Potencjalne modele finansowania

- Współpraca z bibliotekami i teatrami  
- Patronaty instytucji kultury  
- Subskrypcja premium (funkcje rozszerzone)  
- Granty krajowe i europejskie  

---

## 📚 Status projektu

Aktualna faza: MVP (Proof of Concept)  
Cel: rozwój do pełnoprawnej platformy edukacyjno-kulturalnej.
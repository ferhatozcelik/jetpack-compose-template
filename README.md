# Jetpack Compose Template

[![License: Apache 2.0](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](LICENSE)
[![Kotlin](https://img.shields.io/badge/Kotlin-2.4.20-7F52FF.svg?logo=kotlin)](https://kotlinlang.org)

A modern, production-ready starter template for Android apps built with **Jetpack Compose**.
It wires together the libraries and architectural patterns most teams reach for on day one —
Compose + Material 3, Hilt, Coroutines/Flow, Room, Retrofit, and type-safe Navigation — so you
can delete the example screens and start building your app immediately.

## Features

- **Jetpack Compose** — 100% declarative UI, no XML layouts.
- **Material 3** — full light/dark color schemes with dynamic color (Android 12+) support.
- **Unidirectional data flow (MVI-style)** — each screen exposes a single sealed `UiState` via
  `StateFlow`, driven by sealed `Event`/`Intent` types: `Event -> ViewModel -> StateFlow<UiState> -> Composable`.
- **Hilt** — compile-time-safe dependency injection for ViewModels, repositories, and the
  database/network layer.
- **Coroutines & Flow** — structured concurrency throughout the data and domain layers.
- **Room** — local persistence with KSP-generated DAOs.
- **Retrofit + OkHttp** — typed REST networking with Gson conversion and HTTP logging.
- **Type-safe Navigation Compose** — destinations are `@Serializable` Kotlin types (no string
  routes, no manual argument parsing).
- **Domain layer (use cases)** — ViewModels depend on small, single-purpose use cases instead of
  repositories directly.
- **AGP 9 built-in Kotlin** — no separate `org.jetbrains.kotlin.android` plugin; Compose is
  compiled with the dedicated `org.jetbrains.kotlin.plugin.compose` Gradle plugin.
- **Gradle version catalog** (`libs.versions.toml`) as the single source of truth for every
  dependency and plugin version.
- **Gradle build/configuration cache** enabled for faster local builds.

## Architecture

The project follows a light, pragmatic **MVI-inspired** unidirectional data flow on top of a
conventional `presentation -> domain -> data` layering:

```
UI (Composable)
   │  dispatches Event/Intent
   ▼
ViewModel  ──────────────►  StateFlow<UiState>  ──────────────►  UI (Composable) re-renders
   │
   │ calls
   ▼
UseCase (domain layer)
   │
   ▼
Repository (data layer)
   │
   ├──► Retrofit (remote / AppApi)
   └──► Room (local / ExampleDao)
```

- **UI layer** (`ui/`): Composables are stateless functions of a single `UiState`. They never talk
  to the domain/data layers directly — they only dispatch `Event`s to the ViewModel.
- **ViewModel** (`ui/<feature>/<Feature>ViewModel.kt`): the only place mutable state lives
  (`MutableStateFlow`). It exposes an immutable `StateFlow<UiState>` and a single `onEvent(event)`
  entry point.
- **Domain layer** (`domain/usecase/`): small, single-purpose use cases (e.g. `GetExamplesUseCase`)
  that encapsulate business logic and decouple ViewModels from repositories.
- **Data layer** (`data/`): `ExampleRepository` is the single source of truth, combining the
  remote API (`AppApi` via Retrofit) and local storage (`ExampleDao` via Room), and exposes results
  wrapped in a `Resource<T>` (`Loading` / `Success` / `Error`) sealed class.
- **Navigation** (`navigation/`): destinations are `@Serializable` types (`Screen.Main`,
  `Screen.Detail(id)`) consumed through Navigation Compose's type-safe `composable<T>` API.
- **Dependency injection** (`di/`): Hilt modules provide the Retrofit/OkHttp client, the Room
  database, and application-scoped coroutine scope.

### Folder structure

```
app/src/main/java/com/ferhatozcelik/jetpackcomposetemplate/
├── App.kt                      # @HiltAndroidApp Application class
├── data/
│   ├── dao/                    # Room DAOs
│   ├── entity/                 # Room entities
│   ├── local/                  # RoomDatabase + TypeConverters
│   ├── model/                  # Network/domain models (ExampleModel, Resource<T>)
│   ├── remote/                 # Retrofit service interfaces (AppApi)
│   └── repository/             # Single source of truth (ExampleRepository)
├── domain/
│   └── usecase/                # Business logic, decoupled from data sources
├── di/                         # Hilt modules (network, database, app-scoped coroutine scope)
├── navigation/                 # Screen.kt (type-safe routes) + NavGraph.kt
├── ui/
│   ├── activitys/              # MainActivity (single-Activity host)
│   ├── detail/                 # DetailScreen + DetailViewModel + DetailUiState/Event
│   ├── home/                   # MainScreen + HomeViewModel + HomeUiState/Event
│   └── theme/                  # Material 3 Color/Theme/Type
└── util/                       # Small extension functions and helpers
```

## Getting started

### Prerequisites

- [Android Studio](https://developer.android.com/studio) (a recent stable release that supports
  AGP 9.x / Kotlin 2.4.x)
- JDK 17
- An Android SDK with **platform 36** and **build-tools 36.0.0** installed

### Setup

1. **Clone the repository**
   ```bash
   git clone https://github.com/ferhatozcelik/jetpack-compose-template.git
   cd jetpack-compose-template
   ```
2. **Open in Android Studio** and let it sync, or build from the command line:
   ```bash
   ./gradlew build
   ```
3. **Run the app**
   ```bash
   ./gradlew installDebug
   ```
   or press *Run* in Android Studio.
4. **Run tests**
   ```bash
   ./gradlew test
   ```

The package/namespace `com.ferhatozcelik.jetpackcomposetemplate` is left as-is intentionally so
you can `Find & Replace` it project-wide with your own application ID when starting a new project.

## Versions

| Component                     | Version    |
|--------------------------------|------------|
| Gradle                        | 9.3.1      |
| Android Gradle Plugin (AGP)    | 9.1.1      |
| Kotlin                        | 2.4.20     |
| KSP                            | 2.3.12     |
| `minSdk`                       | 24         |
| `compileSdk` / `targetSdk`     | 36         |
| Jetpack Compose BOM            | 2026.06.00 |
| Navigation Compose             | 2.9.8      |
| Hilt (`hilt-android`)           | 2.60.1     |
| `androidx.hilt:hilt-navigation-compose` | 1.3.0 |
| Room                           | 2.8.5      |
| Retrofit                       | 3.0.0      |
| OkHttp (`logging-interceptor`)  | 5.4.0      |
| Lifecycle (`lifecycle-runtime-ktx`, ViewModel/Runtime Compose) | 2.10.0 |
| `kotlinx-serialization-json`    | 1.11.0     |
| `kotlinx-coroutines`            | 1.11.0     |

> All versions are pinned in [`gradle/libs.versions.toml`](gradle/libs.versions.toml) — that file
> is the single source of truth; update it there rather than in individual `build.gradle` files.
> A few libraries are intentionally kept slightly behind their absolute latest release where a
> newer version raises the minimum required `compileSdk` above 36 (Compose BOM, Navigation
> Compose, Lifecycle Compose artifacts, `hilt-navigation-compose`, OkHttp); bump `compileSdk`
> together with those dependencies if you upgrade further.

## Contributing

Contributions are welcome! Please read [CONTRIBUTING.md](CONTRIBUTING.md) for the branch/PR
workflow, commit message format, and code style guidelines, and follow our
[Code of Conduct](CODE_OF_CONDUCT.md). See [CHANGELOG.md](CHANGELOG.md) for a history of notable
changes.

## License

This project is licensed under the **Apache License 2.0** — see the [LICENSE](LICENSE) file for
the full text.

---

If you find this template helpful, please consider giving it a ⭐️ on GitHub — it helps others
discover it too.

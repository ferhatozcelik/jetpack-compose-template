# Contributing to Jetpack Compose Template

First off, thank you for considering contributing! This project is a starter template for
Android apps built with Jetpack Compose, and contributions that keep it modern, clean, and
easy to learn from are very welcome.

## Code of Conduct

This project and everyone participating in it is governed by the
[Code of Conduct](CODE_OF_CONDUCT.md). By participating, you are expected to uphold this code.

## How to contribute

### 1. Fork & clone

```bash
git clone https://github.com/<your-username>/jetpack-compose-template.git
cd jetpack-compose-template
```

### 2. Create a branch

Branch off `main` using a short, descriptive name:

```bash
git checkout -b feature/short-description
# or
git checkout -b fix/short-description
```

### 3. Make your changes

- Keep changes focused: one feature/fix per pull request.
- Follow the code style described below.
- Update the `README.md` and `CHANGELOG.md` when your change affects usage, architecture, or
  dependency versions.
- If you touch build files (Gradle, version catalog), make sure `./gradlew build` still passes.

### 4. Test

```bash
./gradlew build
./gradlew test
```

### 5. Commit

This project uses a lightweight [Conventional Commits](https://www.conventionalcommits.org/)-style
format:

```
<type>(optional scope): short summary

Optional longer description.
```

Common types:

| Type       | Use for                                             |
|------------|------------------------------------------------------|
| `feat`     | A new feature                                         |
| `fix`      | A bug fix                                             |
| `docs`     | Documentation-only changes                            |
| `style`    | Formatting, missing semicolons, etc. (no code change) |
| `refactor` | Code change that neither fixes a bug nor adds a feature |
| `test`     | Adding or fixing tests                                |
| `chore`    | Build process, dependency, or tooling changes         |

Example:

```
feat(navigation): add type-safe routes with kotlinx.serialization
```

### 6. Open a Pull Request

Push your branch and open a PR against `main`. Fill in the PR template — describe **what**
changed and **why**, and reference any related issue.

## Code style

- **Kotlin**: follow the [official Kotlin style guide](https://kotlinlang.org/docs/coding-conventions.html)
  (`kotlin.code.style=official` is already set in `gradle.properties`).
- **Architecture**: keep the unidirectional data flow — `Event/Intent -> ViewModel -> StateFlow<UiState> -> Composable`.
  New screens should expose a single sealed `UiState` and a sealed `Event`/`Intent` type, mirroring
  `HomeUiState`/`HomeEvent` and `DetailUiState`/`DetailEvent`.
- **Layers**: put business logic in a `domain/usecase` class rather than calling repositories
  directly from a `ViewModel` when the logic is non-trivial or reused.
- **Navigation**: add new destinations as `@Serializable` types in `Screen.kt` (type-safe
  Navigation Compose), not raw string routes.
- Avoid unused imports and dead code; prefer clear, small, single-purpose functions.

## Reporting issues

- Search existing issues before opening a new one.
- Use the provided issue templates (bug report / feature request).
- Include reproduction steps, expected vs. actual behavior, and your environment
  (Gradle/AGP/Kotlin versions, OS, Android Studio version).

## Questions

If you have questions about the project, feel free to open a
[GitHub issue](https://github.com/ferhatozcelik/jetpack-compose-template/issues) using the
feature request template.

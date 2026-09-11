# Changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.1.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [Unreleased]

### Changed
- **Build tooling modernization**: upgraded Gradle to 9.3.1, Android Gradle Plugin (AGP) to
  9.1.1, and Kotlin to 2.4.20.
  - AGP 9's **built-in Kotlin support** is now used; the standalone
    `org.jetbrains.kotlin.android` Gradle plugin is no longer applied.
  - Jetpack Compose is now compiled with the dedicated
    `org.jetbrains.kotlin.plugin.compose` Gradle plugin instead of the removed
    `composeOptions.kotlinCompilerExtensionVersion`.
  - Added the `org.jetbrains.kotlin.plugin.serialization` plugin and
    `kotlinx-serialization-json` to support type-safe navigation routes.
  - Upgraded KSP to 2.3.12.
  - Raised `compileSdk`/`targetSdk` to 36 and `minSdk` to 24.
  - Enabled Gradle parallel execution, the build cache, and the configuration cache in
    `gradle.properties`.
  - Fixed a deprecated `getDefaultProguardFile('proguard-android.txt')` call (replaced with the
    `-optimize` variant) and a deprecated no-arg `fallbackToDestructiveMigration()` Room call.
  - Removed the unused legacy `androidx.multidex` / `androidx.legacy:legacy-support-v4`
    dependencies (not required for `minSdk 24`).
- **Dependency upgrades** (see `gradle/libs.versions.toml` and the README's version table for the
  full list): Compose BOM, Navigation Compose, Room, Hilt, `hilt-navigation-compose`, Retrofit,
  OkHttp, core-ktx, lifecycle, and other AndroidX libraries bumped to their latest versions
  compatible with `compileSdk 36`.
- **Type-safe navigation**: `Screen.kt` destinations are now `@Serializable` types consumed via
  `NavHost`'s reified `composable<T>` API instead of hand-built string routes with manual argument
  parsing.
- **Unidirectional data flow (MVI-style)**: `HomeScreenState`/`DetailScreenState` were reworked
  into sealed `HomeUiState`/`DetailUiState` types plus sealed `HomeEvent`/`DetailEvent` intents,
  exposed from the view models as a single `StateFlow`, and consumed in Compose via
  `collectAsStateWithLifecycle()`.
- **Domain layer**: introduced `domain/usecase/GetExamplesUseCase`, so `HomeViewModel` depends on
  a use case instead of talking to `ExampleRepository` directly.
- `ExampleRepository` now implements `getExamples()`, returning a `Flow<Resource<List<ExampleModel>>>`
  backed by `AppApi`.
- Fixed a generics bug in `Resource.Success` (`val data: Any` -> `val data: T`).
- Fixed a Room compile error: `ExampleDao.insert()` no longer accepts a nullable entity, and
  `AppDatabase` explicitly sets `exportSchema = false`.
- Cleaned up dead code (unreachable `return false` in `NetworkUtil`) and unused imports across the
  UI layer.
- Rewrote `README.md` for an open-source audience: features, architecture/folder structure,
  setup instructions, dependency version table, contribution/license sections.

### Added
- `CONTRIBUTING.md`, `CODE_OF_CONDUCT.md` (Contributor Covenant v2.1), and this `CHANGELOG.md`.
- GitHub issue templates (`bug_report.md`, `feature_request.md`) and a pull request template.
- `.github/workflows/ci.yml`: a GitHub Actions workflow that runs `./gradlew build` and
  `./gradlew test` on every push/PR (Ubuntu, JDK 17, Gradle caching).

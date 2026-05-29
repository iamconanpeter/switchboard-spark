I could not write to the workspace because every tool call failed with the same sandbox error:

`error applying legacy Linux sandbox restrictions: Sandbox(LandlockRestrict)`

Here is the Markdown content I prepared for `switchboard-spark-technical-plan.md`:

```md
# Switchboard Spark — Android Technical Plan

## 1) Objectives
- Build a reliable Android client for Switchboard Spark with fast startup, resilient networking, and testable modular architecture.
- Use modern Android stack: Kotlin, MVVM, Coroutines, AndroidX, Gradle, and Jetpack Compose (primary UI path).
- Enforce quality gates with CI, automated tests, and measurable performance targets.

## 2) Architecture Overview
- Pattern: `Clean-ish MVVM` with clear module boundaries.
- Layers:
  - `presentation`: Compose screens, ViewModels, UI state/events.
  - `domain`: use-cases, business rules, pure Kotlin models.
  - `data`: repositories, API clients, local persistence, mappers.
  - `core`: shared utilities (networking, logging, dispatchers, error handling).
- Data flow:
  - UI emits intents/events -> ViewModel -> UseCase -> Repository -> Remote/Local source.
  - ViewModel exposes immutable `StateFlow<UiState>` + one-off `SharedFlow<UiEvent>`.

## 3) Gradle & Module Structure
- Use Gradle Kotlin DSL and Version Catalog (`libs.versions.toml`).
- Suggested modules:
  - `:app`
  - `:core:common`
  - `:core:network`
  - `:core:database`
  - `:feature:home`
  - `:feature:auth`
  - `:feature:spark`
  - `:domain`
  - `:data`
  - `:benchmark` (Macrobenchmark baseline profiles/startup)
- Build variants:
  - `debug`, `release`
  - optional product flavors: `dev`, `staging`, `prod`.
- Required Gradle capabilities:
  - Configuration cache enabled.
  - Remote/local build cache enabled.
  - Static analysis tasks in verification phase (`detekt`, `ktlint`, Android Lint).

## 4) Kotlin Standards
- Kotlin-first codebase with strict null safety.
- Enable modern compiler/toolchain:
  - Kotlin JVM target aligned with project JDK.
  - Explicit API mode for libraries/modules where applicable.
- Conventions:
  - Immutable UI state data classes.
  - Extension functions for mapping and small utilities.
  - Sealed hierarchies for UI actions/results/errors.

## 5) MVVM Design
- ViewModel responsibilities:
  - Orchestrate use-cases, expose UI state, handle retries and user actions.
  - Avoid direct Android framework references outside allowed components.
- UI state model:
  - `Loading`, `Content`, `Empty`, `Error` states represented in typed state object.
- Dependency injection:
  - Hilt for app-wide DI.
  - Constructor injection in repositories/use-cases/ViewModels.

## 6) Coroutines & Concurrency
- Use Coroutines + Flow across data and domain layers.
- Rules:
  - Main-safe APIs from repositories/use-cases.
  - Structured concurrency in ViewModel scope.
  - Dispatcher injection (`IO`, `Default`, `Main`) for testability.
  - Timeouts/retry with backoff for network operations.
- Reactive patterns:
  - `StateFlow` for persistent screen state.
  - `SharedFlow`/`Channel` for one-time events.

## 7) UI Strategy (Compose-first, Views optional)
- Primary: Jetpack Compose + Material 3.
- Navigation: `androidx.navigation:navigation-compose`.
- Design system:
  - Centralized theme, typography, spacing tokens in `:core:common` or `:core:ui`.
- Interop:
  - If legacy screens exist, use Compose-in-View or View-in-Compose bridging.
- Accessibility:
  - Content descriptions, touch target minimums, contrast checks, dynamic type support.

## 8) AndroidX & Core Libraries
- Baseline stack:
  - AndroidX Core KTX, AppCompat (only if needed for View interop), Lifecycle, Activity/Fragment (if needed), Navigation.
  - Room (offline/cache), DataStore (key-value settings), WorkManager (deferrable background work).
  - Retrofit + OkHttp + Kotlinx Serialization/Moshi for API.
  - Coil for image loading in Compose.
  - Timber or structured logging abstraction.

## 9) Data, Offline, and Error Handling
- Repository contract:
  - Network-first with local cache fallback for key user paths.
- Persistence:
  - Room entities + DAO + migration strategy.
  - DataStore for feature flags/session prefs.
- Error model:
  - Map transport/domain/UI errors explicitly.
  - User-facing copy separated from internal exceptions.

## 10) Testing Strategy
- Test pyramid:
  - Unit tests (majority): ViewModels, use-cases, mappers, repository policies.
  - Integration tests: repository + local DB + mocked network.
  - UI tests: Compose UI tests for critical paths.
  - End-to-end smoke: instrumentation for login/startup/core spark flow.
- Tools:
  - JUnit5 (or JUnit4 where Android tooling requires), MockK/Mockito, Turbine for Flow, Robolectric (targeted), Espresso/Compose UI test.
  - JaCoCo/Kover coverage reporting in CI.
- Quality gates:
  - Unit test pass required on PR.
  - Minimum coverage target (start 65%, raise to 75%).
  - Lint + static analysis must pass.

## 11) CI/CD Pipeline
- CI provider: GitHub Actions (or equivalent).
- PR pipeline:
  - `./gradlew clean lint detekt ktlintCheck testDebugUnitTest`
  - Build debug APK.
  - Run targeted instrumentation tests on emulator matrix (API 26/30/34).
- Main branch pipeline:
  - Full test suite + release build validation.
  - Generate baseline profile and run macrobench smoke.
  - Publish artifacts (internal distribution channel).
- Release controls:
  - Semantic versioning, changelog generation, signed release artifacts.

## 12) Performance Targets (Initial SLOs)
- App startup:
  - Cold start P50 < 1.8s, P90 < 2.8s (mid-range device).
  - Warm start P50 < 900ms.
- UI smoothness:
  - >95% frames under 16ms on primary flows.
  - Jank frames < 3% in spark interaction screens.
- Network:
  - P95 API response handling-to-render < 1.2s on stable 4G/Wi-Fi.
- Resource limits:
  - ANR rate < 0.1%.
  - Crash-free sessions > 99.5%.
  - Idle memory budget per process (target band defined per feature rollout).
- Monitoring:
  - Firebase Performance + Crashlytics + custom trace markers.

## 13) Security & Compliance Baseline
- Use HTTPS/TLS with certificate pinning where appropriate.
- Secure local secrets with Android Keystore.
- Obfuscation/minification (`R8`) in release.
- Dependency vulnerability checks in CI.

## 14) Delivery Milestones
1. Foundation (Week 1-2): module setup, DI, network/data skeleton, CI basics.
2. Core Features (Week 3-5): auth/home/spark MVP flows with Compose UI.
3. Hardening (Week 6-7): offline/cache, error states, test expansion.
4. Performance & Release (Week 8): baseline profiles, macrobench, release candidate.

## 15) Definition of Done
- Feature implemented with MVVM + coroutine best practices.
- Unit and UI tests added; CI green.
- Performance regression checks passed against targets.
- Observability, crash reporting, and release notes completed.
``` 


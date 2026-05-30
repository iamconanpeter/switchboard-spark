# Switchboard Spark – Task List

## Epic 1 – Project Setup
- [ ] Create new Android module using Gradle + Kotlin.
- [ ] Add Jetpack Compose, Room, and lifecycle dependencies.
- [ ] Set minimum SDK 21, target SDK 34.
- [ ] Configure ProGuard for release.

## Epic 2 – Core Gameplay Engine
- [ ] Implement `DataModel` for board nodes and spark state.
- [ ] Generate board based on deterministic seed.
- [ ] Calculate spark trajectory on tap.
- [ ] Detect node collisions and activate chain reactions.
- [ ] Implement scoring system and undo token.

## Epic 3 – UI & Animation
- [ ] Design neon style board layout.
- [ ] Compose board grid with nodes.
- [ ] Animate spark movement and node lighting.
- [ ] Show combo multiplier UI overlay.

## Epic 4 – Audio & Feedback
- [ ] Add `SoundPool` wrapper.
- [ ] Load zap, chain, overload, and baseline hum sounds.
- [ ] Trigger sounds on relevant events.

## Epic 5 – Persistence & Daily Logic
- [ ] Store daily seed using `SharedPreferences`.
- [ ] Persist high‑score in Room DB.
- [ ] On app launch, check if today's seed exists; if not, generate new.

## Epic 6 – Testing & CI
- [ ] Write JUnit tests for engine logic.
- [ ] Create Compose UI tests for tap interaction.
- [ ] Integrate Gradle test tasks.

## Epic 7 – Release & Documentation
- [ ] Update README with build instructions.
- [ ] Generate README for GitHub release.
- [ ] Commit, push to GitHub under `iamconanpeter/switchboard-spark`.
- [ ] Tag latest commit as `v1.0.0`.

## Milestones
- **MVP Complete**: Ready for `./gradlew test assembleDebug`.
- **CI Pass**: All tests pass, no lint violations.
- **GitHub Push**: Repo updated with full history.

---
*Use Git issues to track feature/bug tickets if expanding scope.*
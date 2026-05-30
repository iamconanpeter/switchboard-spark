# Switchboard Spark – Technical Plan

## Architecture Overview
- **Presentation Layer**: Jetpack Compose UI with a single `MainScreen` composable displaying the circuit board.
- **Game Engine**: Kotlin class `SparkEngine` handling board generation, spark physics, collision detection, and scoring.
- **Data Layer**: Simple `Repository` storing daily seeds, high‑scores (Room DB), and user preferences.
- **Audio Layer**: `AudioManager` wrapping Android `SoundPool` for low‑latency effects.

## Core Modules
| Module | Responsibility |
|--------|----------------|
| `ui` | Compose screens, theming, animations |
| `engine` | Board generation, spark movement, chain reaction logic |
| `data` | Persistence (Room) for scores, seeds, settings |
| `audio` | Load/play sound assets |
| `network` (optional) | Sync daily seed with server (future) |

## Key Algorithms
1. **Board Generation**
   - Use deterministic RNG seeded by `YYYYMMDD` + user ID to create reproducible daily boards.
   - Place nodes on a grid; ensure at least one valid path for the spark.
2. **Spark Trajectory**
   - On tap, compute direction vector toward target node.
   - Perform ray‑marching stepwise to detect node collisions.
3. **Chain Reaction**
   - When spark hits a node, trigger `activateNeighbors(node)` which lights adjacent nodes and awards bonus points.
4. **Scoring**
   - Base points per hit + multiplier for chain length.
   - Daily high‑score stored locally and optionally uploaded.

## Performance Targets
- **Frame time**: ≤16 ms (60 fps) on low‑end devices.
- **Memory**: ≤40 MB heap usage.
- **Battery**: Limit background work; engine runs only while UI is visible.

## Development Milestones
1. **MVP Scaffold** (Day 1‑2)
   - Set up project with Compose, Room, and basic navigation.
   - Add placeholder UI for board.
2. **Engine Prototype** (Day 3‑4)
   - Implement board generation and spark movement.
   - Add simple collision detection.
3. **Chain Reaction & Scoring** (Day 5)
   - Implement chain logic, scoring, and UI feedback.
4. **Audio Integration** (Day 6)
   - Wire `AudioManager` for zap, chain, overload sounds.
5. **Polish & Testing** (Day 7‑8)
   - Add tutorial board, daily seed logic, undo token.
   - Write unit tests for `SparkEngine` core functions.
6. **Build & Release Prep** (Day 9)
   - Assemble debug APK, run `./gradlew test assembleDebug`.
   - Fix any CI failures, ensure lint passes.

## Testing Strategy
- **Unit Tests**: `SparkEngineTest` covering board generation, spark trajectory, chain activation.
- **UI Tests**: Compose UI tests for tap interaction and visual feedback.
- **Performance Tests**: Run on low‑end emulator, verify 60 fps.

## Risk Mitigation
- **Complex physics**: Keep spark path simple (straight line) to avoid heavy calculations.
- **Asset size**: Use vector drawables; keep audio files < 200 KB each.
- **Platform bugs**: Target stable versions of Compose and Room; avoid experimental APIs.

---
*All timelines are estimates; adjustments may be needed based on team velocity.*
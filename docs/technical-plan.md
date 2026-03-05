# Technical Plan

## Architecture
- **Client-first mobile app:** all core play runs locally and offline.
- **Deterministic puzzle domain:** board state, move application, undo, and win checks live in a pure logic layer.
- **Content-driven progression:** puzzle definitions, par values, and daily pack metadata load from static content bundles at MVP.

## Modules
- **Puzzle Engine:** board model, row-column flip operator, target matcher, undo handling, par tracking.
- **Puzzle Validation Tools:** solver/checker to verify solvability, exact-par thresholds, and content integrity before shipping.
- **Progression Layer:** unlock flow, medals, streak-safe rewards, local profile, daily puzzle state.
- **UI Layer:** board rendering, target preview, feedback states, onboarding prompts, results screens.
- **Content Module:** puzzle packs, difficulty tiers, daily rotation tables, tuning constants.
- **Telemetry Stub:** local event schema at MVP, remote analytics integration later if needed.

## Data Flow
1. App loads local player profile and current puzzle pack metadata.
2. Content module selects the next puzzle and sends board + target + par into the puzzle engine.
3. UI renders current board and subscribes to engine state.
4. Player tap triggers engine move resolution, then UI updates state, feedback, and remaining undo.
5. Engine emits `in_progress`, `cleared_under_par`, `cleared_over_par`, or `failed` result.
6. Progression layer persists medals, unlocks, and daily completion locally.

## Performance Notes
- Keep board updates O(n) per tap by flipping only affected row and column, not reprocessing the full grid unnecessarily.
- Target 60 FPS on low-mid mobile hardware with lightweight VFX and minimal layout churn.
- Keep puzzle payloads small and cacheable; all MVP content should fit comfortably in-app with no network dependency.
- Cap animation duration so retry loops stay fast; reset and undo should feel near-instant.

## Security Notes
- Treat puzzle content as trusted local data at MVP, but validate bundle shape/version to avoid corrupted saves or malformed packs.
- Avoid remote economy or competitive leaderboards in MVP to reduce tamper incentives.
- If post-MVP analytics or live ops are added, separate gameplay authority from telemetry and avoid sending sensitive identifiers.

## Scalability Notes
- Content pipeline should support hundreds of authored puzzles without code changes.
- Daily rotation tables should be data-driven so future live content can be swapped without touching core logic.
- Solver tooling should batch-validate new packs offline so content scale does not create manual QA bottlenecks.

## Test Strategy
- **Unit tests:** flip operator, overlap rule, undo behavior, win detection, par evaluation, puzzle serialization.
- **Property tests:** repeated move invariants, reset determinism, solver parity checks across generated boards.
- **Content validation tests:** every shipped puzzle must have at least one valid solve and no impossible par target.
- **UI/E2E tests:** first-time tutorial clear, exact-par clear, over-par clear, undo then solve, retry loop, daily puzzle completion.
- **Manual playtest passes:** readability on small screens, onboarding clarity, frustration around undo scarcity, difficulty ramp quality.


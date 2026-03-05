# Tasklist

## Milestones
- [x] M1 Plan Mode docs locked (`prd/specs/technical-plan/tasklist/gameplay-flow`)
- [x] M2 Android project scaffolding committed
- [x] M3 Core engine + unit tests green
- [x] M4 UI vertical slice playable
- [x] M5 Build + test verification (`test`, `assembleDebug`)

## Execution Steps
1. [x] Create Gradle Android skeleton in `projects/switchboard-spark`.
2. [x] Implement `SwitchboardEngine` with deterministic flip logic and undo.
3. [x] Add puzzle content set + local validator checks in tests.
4. [x] Implement `SparkProgressManager` for stars/streak/daily index.
5. [x] Build main board UI (target view, active board, status, controls).
6. [x] Wire reset, undo, next puzzle and medal output.
7. [x] Add unit tests: flip overlap, solve detection, undo, medal grading.
8. [x] Run full verification and fix issues.

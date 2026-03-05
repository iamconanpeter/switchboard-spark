# Specs

## Concept
- **Name:** Switchboard Spark
- **Slug:** `switchboard-spark`
- **Core loop:** tap node -> flip full row and column -> align with target pattern -> clear under par when possible.

## Gameplay Rules
- Board is an `N x N` binary grid (`off/on`).
- A tap at `(r, c)` flips all cells in row `r` and column `c`.
- Overlap cell `(r, c)` flips once only.
- Puzzle is cleared when board state exactly matches target state.
- One undo token is available per attempt.
- Move counter increases on each tap, decreases on undo.

## Result States
- `in_progress`: board not equal target.
- `cleared_under_par`: solved with `moves <= par`.
- `cleared_over_par`: solved with `moves > par`.

## Medals
- `Gold`: solved under or equal par and no undo used.
- `Silver`: solved over par by at most +2 moves.
- `Bronze`: solved but above silver threshold.

## Puzzle Schema
- `id: String`
- `name: String`
- `size: Int`
- `par: Int`
- `start: List<Int>` flattened binary board
- `target: List<Int>` flattened binary board

## MVP Content Scope
- 12 authored puzzles:
- 4 tutorial/easy (3x3)
- 4 medium (4x4 to 5x5)
- 4 hard (6x6)

## Retention and Replay
- Daily puzzle index rotates from local day-of-year.
- Best medal per puzzle persisted locally.
- Daily streak increments when first daily clear is recorded.

## Quality Bars
- Input-to-state update should feel instant.
- State readability (current/target/par/undo) visible at all times.
- Retry loop should take under 1 second.

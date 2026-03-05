# Gameplay Flow

## Session Entry
1. Load local profile (best medals, streak, last daily key).
2. Pick puzzle:
- Default campaign next unsolved puzzle.
- Daily mode uses `dayOfYear % puzzleCount`.
3. Render board and target preview.

## Puzzle Loop
1. Player taps a cell.
2. Engine flips row and column (center once).
3. UI updates board, move count, and optional feedback highlight.
4. Engine checks solved state against target.
5. If unsolved, continue loop.

## Solve/Fail Resolution
1. On solved state, compute medal from par + undo usage.
2. Persist best medal and daily streak updates.
3. Show result panel with:
- moves vs par
- medal earned
- retry and next puzzle actions

## Side Actions
- **Undo:** available once per attempt, restores previous board/moves.
- **Reset:** restores initial board and clears attempt-local state.
- **Next puzzle:** advances index and loads next definition.

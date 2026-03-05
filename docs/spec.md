# Spec

## Concept
- **Name:** Switchboard Spark
- **Slug:** `switchboard-spark`
- **Concept Rank:** 16
- **Genre:** deterministic micro puzzle
- **Core Loop:** tap a node, flip its full row and column, align the live board with a target electric pattern, finish at or under par, spend one undo token if needed, then move to the next puzzle.

## Q&A Discovery
- **Q: What is the 10-second hook?** A single tap rewires the whole grid, so every move feels large and readable.
- **Q: What fantasy is being sold?** You are restoring a broken power board with clean, clever reroutes.
- **Q: Why do players come back daily or weekly?** Short daily puzzle packs, medal chasing against par, and streak-safe rewards for near-miss returns.
- **Q: What session lengths should it support?** 30-second single clears, 2-minute daily runs, 5-minute retry-and-improve sessions.
- **Q: Is the game skill or luck driven?** Fully skill-driven; puzzle states are deterministic and solvable.
- **Q: How is failure kept fair?** Every board shows current parity, par target, and one undo; losses come from visible move inefficiency, not randomness.
- **Q: How does onboarding work?** Start with 3x3 boards, teach row flip, column flip, overlap center behavior, then introduce par and undo.
- **Q: What makes it distinct from low-quality Android clones?** Strong puzzle readability, authored target patterns, and strict deterministic feel instead of noisy effect spam.
- **Q: What art scope fits a small team?** Minimal neon switchboard theme, one board skin at MVP, high-contrast target overlays, restrained but polished VFX.
- **Q: What is the audio/feedback plan?** Crisp relay clicks, soft hum build-up, success chord on exact-par clears, warning sting when the undo is consumed.
- **Q: How is monetization kept safe?** Optional hints or cosmetic board themes later; no energy timers, no forced ads, no pay-to-solve.
- **Q: What technical constraints matter most?** Instant input response, deterministic solver validation, tiny battery footprint, and stable performance on low-mid mobile devices.

## Assumptions
- The board is a square grid of binary node states: on/off.
- A tap flips every node in the tapped row and tapped column, with the tapped node flipped once, not twice.
- Every shipped puzzle has a verified solution path at or under par.
- One undo token is granted per puzzle attempt, not stackable across attempts.
- MVP targets mobile portrait play first, with offline support and no account requirement.

## USP
- A one-tap row-and-column flip puzzle where every move rewires the whole board and every win is earned under a visible par target.

## Differentiators
- Deterministic board logic with zero RNG during play.
- Par-based mastery layer that turns correct solves into optimization play.
- Single-use undo that softens frustration without removing tension.

## Retention Hooks
- Daily circuit: one curated pack per day with bronze/silver/gold clear goals.
- Clean return value: failed or over-par runs still award progress toward unlock tracks.
- Variable challenge: rotating modifiers after MVP, such as blocked nodes or mirrored target shapes.

## Quality Bars
- Readability: players can parse current state, target state, and move delta in under 1 second.
- Feel: tap-to-flip response must feel immediate, with clear audiovisual confirmation.
- Smoothness: puzzle resets, undo, and win transitions must be fast enough to encourage instant retries.

## MVP vs Post-MVP
### MVP
- 3x3 to 6x6 authored puzzles.
- Target-pattern preview and live board comparison.
- Par move targets, one undo token, retry, and hint-free progression.
- Daily puzzle slot and local progress save.
- Basic medal system and lightweight stats.

### Post-MVP
- Endless generated puzzle mode with solver-backed quality filters.
- Puzzle modifiers, theme packs, and seasonal boards.
- Hint system, weekly challenge ladders, and ghost replay of best solutions.
- Accessibility upgrades such as alternate color palettes and haptics tuning.


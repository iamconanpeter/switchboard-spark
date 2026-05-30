# Switchboard Spark – Game Specification

## Core Fantasy & 10‑second Hook
- **Fantasy**: Players control a tiny “spark” of electricity that must jump across a circuit board, lighting up nodes while avoiding overloads.
- **Hook**: A single‑tap sends the spark in a straight line; the goal is to hit a moving target node within ~10 seconds for a burst of points.

## Retention Loop / Daily Challenge
- Daily seeded board layouts with a “high‑score” leaderboard.
- Streak of consecutive days with a successful board gives a multiplier.

## Session Length Targets
- **Micro‑session**: 30 seconds to 2 minutes per board.
- **Full‑run**: 5‑10 minutes for a series of 10 boards.

## Skill vs Luck Balance
- Skill: timing of tap, angle selection, and quick decision making.
- Luck: randomized board seed and target movement pattern (deterministic per‑day seed).

## Fail‑State Fairness & Frustration Controls
- One‑undo token per board to revert a mis‑tap.
- Clear visual feedback on overloads; no permanent loss of progress.

## Difficulty Ramp & On‑boarding
- Boards start with wide gaps and slow target movement.
- Gradual increase in node density and target speed.
- Tutorial board introduces mechanics with hand‑holding prompts.

## Distinctive Mechanic vs Android Clones
- Unique “spark‑chain” mechanic where hitting a node activates a chain reaction lighting adjacent nodes for bonus points.
- Minimalist neon‑style aesthetic distinct from typical “match‑3” or “runner” clones.

## Art / Animation Scope (Small Team)
- Simple vector‑based neon outlines for nodes and spark.
- Light particle effects for chain reactions (few sprite sheets).
- 2‑3 background themes (dark circuit, light circuit) – reusable assets.

## Audio / Feedback Plan
- Subtle “zap” sound on tap, layered with a soft ambient hum.
- Positive chime on chain reactions, gentle buzz on overload.
- All audio generated from royalty‑free synth packs.

## Monetization‑Safe Design (No Dark Patterns)
- Optional non‑intrusive rewarded video for an extra undo token.
- Small “remove‑ads” IAP (single purchase).
- No pay‑to‑win mechanics; all core gameplay is free.

## Technical Constraints & Performance Budgets
- Target Android 5.0+ (API 21). Minimum device RAM 512 MB.
- 60 fps target; limit draw calls to ≤150 per frame.
- Keep APK size < 30 MB (no heavy assets).
- Use Kotlin + Jetpack Compose for UI; minimal native libs.

---
*All assumptions are based on iOS‑hot references and are marked as assumptions where no direct user input was provided.*
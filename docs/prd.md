# Product Requirements Document

## Product
- **Name:** Switchboard Spark
- **Platform:** Android (portrait-first)
- **Genre:** deterministic micro puzzle
- **Version target:** MVP v0.1.0

## Problem
Players want a puzzle they can understand instantly and replay in short sessions without RNG or ad-heavy interruption.

## Goal
Ship a polished offline MVP where each tap flips a full row+column and players optimize under a visible par target.

## Success Metrics (MVP)
- First puzzle clear rate >= 80% in onboarding flow.
- Median session length 2-5 minutes.
- 60 FPS target on low-mid Android devices.
- Crash-free local sessions during manual QA pass.

## Target Audience
- Mobile players who enjoy short deterministic logic puzzles.
- Players who prefer "one more try" optimization loops over luck-based progression.

## Core Features (MVP)
- Authored puzzles from 3x3 to 6x6.
- Tap action flips row + column; center flips once.
- Visible target board + current board.
- Par move target + medal grading.
- One undo token per attempt.
- Retry/reset flow with fast loop.
- Daily puzzle slot based on local day.
- Local progress save (best medal, streak, clears).

## Non-Goals (MVP)
- Online accounts, cloud sync, multiplayer.
- Procedural endless generation.
- Real-money economy or forced ads.
- Heavy live-ops or leaderboard backend.

## Constraints
- Offline-first operation.
- Deterministic logic and repeatable outcomes.
- Lightweight UI with low battery/CPU impact.

## Risks and Mitigations
- **Risk:** puzzle set has invalid or unfair par values.  
  **Mitigation:** unit-tested validator + authored puzzle checks.
- **Risk:** UX confusion on flip behavior.  
  **Mitigation:** onboarding copy and highlighted row/column feedback.
- **Risk:** content too short.  
  **Mitigation:** include daily rotation + medal replay loop in MVP.

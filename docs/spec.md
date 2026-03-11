# Switchboard Spark - Product Spec

## Game Concept
Switchboard Spark is a deterministic micro puzzle where tapping a node flips its entire row and column to match a target electric pattern. Players must solve each board under a par move count with one undo token for fairness.

## Core Mechanics
- Grid size: 3x3 or 4x4 (configurable levels)
- Each tap toggles (on/off) all nodes in that row AND column
- Goal: Transform initial state to match target pattern
- Par moves: Precomputed minimum plus 1-2 buffer
- Undo: 1 token per level (consumable)
- Scoring: 3 stars if ≤ par, 2 if ≤ par+2, 1 otherwise

## Q&A Discovery

**Q: How do we guarantee deterministic solutions?**
A: Generate boards from known solvable configurations. For each target pattern, we compute the exact sequence of taps that solves it using reverse engineering. The initial board starts as all off, apply the solution sequence to get the initial state, then hide the solution.

**Q: How to calculate par moves?**
A: Compute the optimal solution length using BFS on the state space (2^(N*N) states). Use that as par. For 3x3: 2^9=512 states, trivial. For 4x4: 2^16=65536, still doable.

**Q: Undo system - what to restore?**
A: Restore board state to before the last tap. Undo token replenishes after level completion.

**Q: Daily challenge generation?**
A: Seed a deterministic RNG with YYYY-MM-DD to generate target pattern and compute solution. Store target + par in DB, generate initial state via solution.

**Q: Does the player have to achieve exactly the target pattern?**
A: Yes. The board is binary (on/off). Target is a binary pattern.

**Q: Are there any special nodes or modifiers?**
A: No - keep MVP minimal. All nodes same behavior.

**Q: Visual style?**
A: Electric theme: nodes as glowing circles, electrified lines when toggled, sparks effect on tap. Dark background with blue/cyan/orange accents.

**Q: Sound design?**
A: Snap sound on tap, success chime, undo click.

**Q: Input method?**
A: Tap on node. Display current pattern and target pattern side-by-side (or toggle view).

**Q: Win condition check?**
A: After each tap, check if board equals target. If yes, level complete.

**Q: Requirements:**
- Offline playable (no network needed for core)
- Ad integration placeholder (MVP omit)
- IAP placeholder (MVP omit)
- Leaderboards/Daily seeds MVP omit
- Local progress storage (SharedPreferences)

## MVP Scope
- One campaign of 30 handcrafted levels (presolved)
- 3x3 grid only for MVP
- Undo mechanics
- Star rating
- Level select screen
- No ads, no IAP, no network

## Success Metrics
- Solvable within par (verified during development)
- Playable sessions < 2 minutes
- < 1MB APK size
- 60fps on low-end Android

## Legal/IP
- Original mechanics, no external IP

## Implementation Estimate
2–4 days for MVP build.

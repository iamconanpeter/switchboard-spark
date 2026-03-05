# Switchboard Spark - Spec

Core concept: An Android lightweight puzzle game featuring a deterministic row/column flip mechanic on a 3x3 grid. Players aim to transform the starting grid into a target pattern under a fixed number of moves (par), with one undo token available per round.

Q&A Discovery (Plan-Mode)
- Core fantasy and 10-second hook:
  - A crisp neon-puzzle where every tap reshapes the board by flipping an entire row or column; solve within par moves for a satisfying sense of control.
  - 10-second hook: See a seed board and immediately start flipping rows/columns to reach the target in a few taps.
- Why users come back (daily/weekly loop):
  - Daily seeds with new target patterns, streak bonuses on solving under par, and a local leaderboard for best times.
- Session length targets: 30s quick rounds; 2m typical; 5m for advanced sessions.
- Skill vs luck balance: Pure logic with deterministic outcomes; no randomness in state transitions during a round.
- Fail-state fairness and frustration controls: If stuck, allow one undo; no penalty for exploration; reset after a fixed number of moves.
- Difficulty ramp and onboarding: Gradual introduction of row flips, then column flips, then mixed moves; hint economy added later.
- Distinctive mechanic vs common Android clones: Whole-row/column flips yield a readable, tactile feedback loop; parity-based progression enables predictable mastery.
- Art/animation scope feasible for small team: Neon-on-dark aesthetic with simple flip animations; 2-3 art assets for grid states.
- Audio/feedback plan: Crisp button clicks, small success chime on solve, subtle glow on flips.
- Monetization-safe design (optional): Cosmetic skins and hint pack, not required to play.
- Technical constraints and performance budgets: Target 60fps on mid-range devices; minimal allocations per move; efficient 3x3 grid updates.

Assumptions (if user answers unavailable):
- No licensed IP; original art and sound assets.
- Target device: Android 6.0+ compatible; low memory footprint.
- Leaderboard uses local storage with optional cloud sync later.

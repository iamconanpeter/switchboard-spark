I couldn’t read local workspace templates due a sandbox execution fault, so I mapped this to a standard game-planning spec format.

**Switchboard Spark — Game Planning Spec (Draft v0.1)**

**1. Concept**
- Working Title: `Switchboard Spark`
- Genre: Tactical lane-defense + chain-reaction puzzle roguelite
- Core Fantasy: “Route power, trigger perfect cascades, and save a collapsing neon grid.”
- Target Session: 12–20 minutes per run
- Platforms: PC first, Steam Deck-compatible, console-ready controls in scope

**2. Player / Market**
- Primary Audience: Players who like `Into the Breach`, `Slay the Spire`, `Mini Motorways`, `Luck be a Landlord`
- Secondary Audience: Streamers seeking “one more run” strategy games with readable clutch moments
- Rating Target: E10+/T (no graphic violence)

**3. Core Gameplay Loop**
- Plan phase: Place/re-route modules on a city switchboard.
- Sim phase: Sparks travel through routes in real-time ticks.
- Resolve phase: Chain reactions score, defend districts, and unlock upgrades.
- Meta phase: Spend run currency on permanent unlock trees and modifier cards.

**4. USP (Unique Selling Proposition)**
- “A roguelite where *positioning* and *timing* both matter every 10 seconds.”
- Hybrid loop: deterministic planning + high-drama spark execution.
- Spectacle with clarity: huge chain reactions that remain tactically legible.

**5. Differentiation**
- Versus pure deckbuilders: no card-hand RNG bottleneck; board topology is the strategic core.
- Versus tower defense: ultra-short runs, deterministic previews, and puzzle-like reroute actions.
- Versus autobattlers: constant micro-decisions during simulation windows (switch, overload, cut).

**6. Retention Hooks**
- Daily Grid Seed with global leaderboard.
- Weekly mutators (“Low Voltage,” “Overheat,” “Ghost Lines”).
- Unlockable operators with distinct passives and starter modules.
- Build-defining relics that alter spark physics.
- Milestone quests tied to playstyle (“Win with 0 overload events,” etc.).
- Post-run “near miss” recap highlighting lost value and improvement opportunities.

**7. Systems Overview**
- Board: Node-and-line grid with district objectives.
- Threats: Surge waves, sabotage drones, blackout storms.
- Player Tools: Modules (splitter, amplifier, capacitor, fuse), emergency actions, operator skills.
- Resource Model: Energy, Heat, Credits.
- Failure State: District stability hits zero in required zones.
- Win State: Survive N waves + stabilize final overload event.

**8. Progression**
- Run Progression: Biome map with branching risk/reward nodes.
- Meta Progression: Operator unlocks, module pool expansion, cosmetic board themes.
- Difficulty Ladder: Ascension-style tiers adding constraints before raw stat inflation.

**9. Content Plan (Launch)**
- 4 biomes
- 8 operators
- 45 modules
- 60 relics
- 30 mutators
- 3 final bosses/events
- Daily/weekly challenge pipeline live at launch

**10. Q&A (Design Decisions)**
- Q: Why real-time simulation instead of turn-only?
- A: Real-time spark travel creates tension and streamable clutch moments without long downtime.
- Q: How do we prevent chaos from feeling random?
- A: Deterministic sim previews, clear telegraphs, and rewind-once tutorial scaffolding.
- Q: What makes replayability durable?
- A: Board topology variance + operator identity + relic physics modifiers + mutators.
- Q: How is onboarding handled for non-hardcore players?
- A: 3-run guided arc, optional assist toggles, and “suggested route” hint overlays.
- Q: How do we avoid dominant strategies?
- A: Heat penalties, situational counters, and rotating weekly mutator incentives.
- Q: Why this theme?
- A: Neon-grid utility crisis supports readable systems language and high-contrast effects.

**11. Quality Bars**
- Readability: 95% of deaths attributable to visible/telegraphed causes in playtests.
- Input Feel: Action-to-response latency under 100ms on target hardware.
- Run Pace: First meaningful decision <20s from run start.
- Build Variety: At least 6 viable archetypes at highest shipped difficulty tier.
- Retention: D1 >40%, D7 >15% (PC premium target bands).
- Stability: Crash-free sessions >99.5% in release candidate telemetry.
- Accessibility: Full key remap, colorblind modes, motion reduction, scalable UI.

**12. MVP Slice (8–12 weeks)**
- 1 biome, 2 operators, 12 modules, 15 relics, 1 boss event.
- Full loop playable: tutorial → run → meta spend → re-run.
- Basic daily seed + local leaderboard stub.
- Internal telemetry for difficulty, run length, and failure causes.

**13. Top Risks + Mitigations**
- Cognitive overload in mid/late waves.
- Mitigation: layered UI reveal, optional pause-planning, preview lanes.
- Balance complexity explosion from relic interactions.
- Mitigation: simulation tests + banned pair lists pre-launch.
- Spectacle hurting readability.
- Mitigation: VFX budget caps, silhouette/contrast rules, replay scrubber.

If you want, I can convert this into your exact local “Game Planning Standard” file format once you paste that template (or once shell access is working).

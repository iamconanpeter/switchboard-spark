# Tasks

## Priorities
- **P0:** lock puzzle rules, validation tooling, MVP puzzle set, onboarding, save flow, and core tests.
- **P1:** daily puzzle rotation, medal progression, polish feedback, balancing passes.
- **P2:** modifiers, generated content, cosmetics, hints, and live ops hooks.

## Dependencies
- Finalized overlap rule and par definition before solver work.
- Solver/validator ready before content production scales.
- UI onboarding depends on stable engine events and state model.
- Daily puzzle feature depends on local profile persistence and content metadata format.

## Milestone Gates
- **Gate 1: Spec Locked** Core rules, assumptions, quality bars, and MVP scope approved.
- **Gate 2: Logic Proven** Engine and validator pass unit/property tests; sample puzzles clear correctly.
- **Gate 3: Vertical Slice** Tutorial, 10-15 puzzles, results flow, save/load, and base feedback working on device.
- **Gate 4: Content Complete** Full MVP puzzle pack validated, difficulty ramp reviewed, daily puzzle pipeline working.
- **Gate 5: Release Candidate** E2E passes, performance target met, no blocker UX issues in final playtest.

## MVP Work Breakdown
1. **Rule Finalization**
- Define exact flip semantics, fail states, par grading, and undo consumption rules.
- Write puzzle content schema and result-state contract.

2. **Core Logic**
- Build deterministic board engine, undo stack, win check, and par tracker.
- Build solver/validator for authored puzzle verification.

3. **Content**
- Author tutorial sequence and first puzzle ladder.
- Tune difficulty from 3x3 introduction to 6x6 mastery.
- Validate every puzzle against solver outputs.

4. **Frontend**
- Implement board screen, target preview, move counter, undo button, retry, and results screen.
- Add onboarding prompts and basic feedback/audio hooks.

5. **Progression**
- Add local save, medal assignment, unlock progression, and daily puzzle selection.
- Track simple completion stats for tuning.

6. **Quality**
- Run unit, property, content-validation, and E2E coverage for critical flows.
- Perform device performance pass and frustration-focused playtest review.

## Post-MVP Work Breakdown
1. Add generated puzzle mode with quality filters.
2. Add weekly challenge ladders and replay ghosts.
3. Add accessibility options, themes, and optional hint system.
4. Add analytics/live-content hooks only after core retention is proven.


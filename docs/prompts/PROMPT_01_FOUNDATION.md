# Prompt 01 – Audit and establish the new project foundation

You are working inside an existing Android/Wear OS repository.

First inspect the repository thoroughly. Do not rewrite working code blindly.

Goal:
Refactor the project direction from a swim-only app into an Android-first personal fitness coach, while preserving existing swim functionality.

Required architecture:
- `mobile-app/`
- `wear-app/`
- `shared/`
- `analysis/`
- `docs/`

Product rules:
- Main goal: reduce waist circumference and reveal abdominal definition while preserving swimming performance.
- Weekly plan supports:
  - 2 required strength sessions
  - 2 required swim sessions
  - 1 optional swim session
- The phone is the primary hub.
- Room/SQLite is the local source of truth.
- Deterministic rules must work without AI.
- AI must be optional, local-first and replaceable behind an interface.
- No paid cloud API dependency.

Tasks:
1. Inspect the current repository and summarize the current modules, build setup and existing functionality.
2. Identify what already matches the target architecture.
3. Propose the smallest safe migration plan.
4. Create or update:
   - `docs/PROJECT_CONTEXT.md`
   - `docs/ARCHITECTURE.md`
   - `docs/ROADMAP.md`
5. Add no speculative UI and do not delete existing swim code.
6. If folders differ from the target names, document the mapping before changing anything.
7. Run relevant tests/build checks.
8. Report:
   - files changed
   - architecture decisions
   - risks
   - exact next prompt to run

Important:
- Keep changes incremental.
- Prefer interfaces and pure domain models.
- Do not add an LLM dependency yet.
- Do not fabricate repository state; cite file paths and relevant line numbers in the final report.

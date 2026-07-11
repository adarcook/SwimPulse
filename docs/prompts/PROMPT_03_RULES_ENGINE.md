# Prompt 03 – Implement the local Rules Engine

Use the shared domain model from Prompt 02.

Goal:
Create a deterministic Rules Engine that chooses one primary daily action.

Initial rules, in priority order:
1. If today's required workout is not completed, recommend it.
2. If a required workout from earlier in the week was missed, recommend the most relevant recoverable action without rewriting history.
3. If all required workouts are complete and an optional swim is planned today, recommend it only when recovery status allows.
4. If body measurements are overdue, recommend measuring weight or waist.
5. Otherwise recommend recovery or no action.

Add recovery inputs:
- perceived fatigue: LOW, MODERATE, HIGH
- last workout date
- consecutive training days
- optional sleep duration input

Outputs must include:
- action type
- short title
- explanation code
- priority
- whether the action is optional

Requirements:
- pure Kotlin where possible
- no LLM
- deterministic, testable rules
- unit tests for conflicts and priority ordering
- no hard-coded user-facing long prose inside the engine; use explanation codes/resources

Report exact files changed and tests run.

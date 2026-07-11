# Prompt 02 – Implement the shared domain model and weekly plan

Use the repository state produced by Prompt 01.

Goal:
Create the shared domain foundation for a weekly coaching plan.

Implement domain types for:
- WorkoutType: STRENGTH, SWIM
- WorkoutRequirement: REQUIRED, OPTIONAL
- PlannedWorkout
- CompletedWorkout
- WeeklyPlan
- WeeklyProgress
- BodyMeasurement
- DailyAction

Current default weekly template:
- Sunday: strength, required
- Wednesday: strength, required
- Thursday: swim, required
- Friday: swim, required
- Saturday: swim, optional

Requirements:
1. Put pure domain models in the shared/domain layer.
2. Avoid Android framework dependencies in pure domain code.
3. Add functions to calculate:
   - required strength completed / planned
   - required swims completed / planned
   - optional swims completed
   - next planned workout
   - whether the weekly minimum has been met
4. Handle missed workouts without silently moving them.
5. Use local dates and the device timezone correctly.
6. Add unit tests for:
   - empty week
   - partially completed week
   - all required workouts completed
   - optional swim completed
   - missed workout
   - week boundary
7. Do not add AI.
8. Do not build the full UI yet.
9. Run tests and report changed files with line references.

Definition of done:
The domain layer can represent the current weekly plan and compute progress with no Android UI and no server dependency.

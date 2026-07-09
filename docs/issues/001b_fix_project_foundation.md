You are working on SwimPulse, a Wear OS swimming companion app.

Before starting, inspect:
- AGENTS.md
- docs/issues/001_project_setup.md
- docs/issues/002_heart_rate.md
- app/src/main/java/com/example/swimpulse/MainActivity.kt
- Gradle files

Goal:
Complete the missing architecture from Issue #001 before starting heart-rate integration.

Current problem:
The UI works and the app runs on the Galaxy Watch Ultra, but the workout state and timer logic are still inside MainActivity / composables.
Issue #002 expects a WorkoutViewModel layer, so we need to add that first.

Task:
Refactor the existing implementation into a simple MVVM structure.

Requirements:
1. Create a WorkoutViewModel.
2. Move workout state out of composables:
   - isSwimming
   - elapsed time
   - start workout
   - stop workout
3. Timer logic must live in the ViewModel, not in a composable.
4. MainActivity should only set content and connect the UI to the ViewModel.
5. Composables should be stateless where practical:
   - receive state
   - call callbacks
6. Keep the existing UI visually the same:
   - Start screen
   - Stop screen
   - elapsed timer
   - "-- bpm" placeholder
   - "תקין" status
7. Do not add Health Services yet.
8. Do not add permissions yet.
9. Do not add vibration yet.
10. Do not add accelerometer, turn detection, pool counting, phone sync, Room, FastAPI, or AI.
11. Keep the implementation small and readable.
12. The project must compile with:
   ./gradlew assembleDebug

Expected structure:
- MainActivity.kt
- WorkoutViewModel.kt
Optional:
- WorkoutUiState.kt if it makes the code cleaner

After implementation:
Explain:
- which files changed
- what logic moved out of MainActivity
- how this prepares the app for docs/issues/002_heart_rate.md
- how to test manually on the Galaxy Watch Ultra
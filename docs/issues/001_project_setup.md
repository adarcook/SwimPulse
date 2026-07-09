# Issue #001 - Project Foundation

You are a senior Android Wear OS engineer.

Goal:
Create the initial architecture for SwimPulse, a Wear OS application designed specifically for swimming.

Requirements:

- Kotlin
- Jetpack Compose for Wear OS
- Material 3
- MVVM architecture
- Keep the project clean and scalable.

Current functionality:

- Start Workout
- Stop Workout
- Workout timer
- Heart rate placeholder
- Status placeholder

Rules:

- UI must be composed of small composables.
- Separate UI from business logic.
- Timer logic must not be inside composables.
- Create a ViewModel even if it currently only controls the timer.
- Keep everything simple.
- No sensor code.
- No Health Services.
- No permissions.
- No experimental features.

Project structure should already support future modules:

- Workout Session
- Heart Rate
- Sensor Processing
- Analytics

After finishing:

1. Explain every new file.
2. Explain why the architecture was chosen.
3. Suggest improvements if you see technical debt.
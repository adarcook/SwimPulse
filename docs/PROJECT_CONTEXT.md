# Fitness Coach – Project Context

## Product goal
Build an Android-first personal fitness coach whose main goal is helping the user reduce waist circumference and reveal abdominal definition while preserving or improving swimming performance.

The product is no longer only a swim-analysis app. Swimming is the first rich workout module inside a broader coaching system.

## Current user plan
- Strength: 2 planned sessions per week
- Swimming: 2 required sessions per week
- Swimming: 1 optional third session per week
- Current baseline: 25 m pool, 60 lengths = 1500 m, usually around 34–35 minutes
- Current body data: 70 kg, height 176–179 cm
- Main progress measures:
  - Waist circumference
  - Weight trend
  - Strength consistency
  - Swim performance and fatigue

## Architecture principles
1. Android-first, native Kotlin.
2. Phone is the primary always-on hub.
3. Galaxy Watch Ultra / Wear OS handles workout-time interaction and recording support.
4. Room/SQLite is the local source of truth.
5. Rules-first: deterministic logic handles most recommendations.
6. Local-AI-first: no paid API dependency.
7. AI is optional and replaceable behind a stable provider interface.
8. FastAPI is used only for heavier analysis and local-model orchestration.
9. Features must lead to a concrete user decision or action.

## Repository structure
- `mobile-app/` – Android phone app, Jetpack Compose
- `wear-app/` – Wear OS app
- `shared/` – shared domain models and rules
- `analysis/` – Python/FastAPI analysis service and local AI adapter
- `docs/` – product, architecture, prompts and decisions

## Core product layers
### Facts
Weight, waist, workouts, swim sessions, nutrition logs, sleep, steps, resting heart rate and progress photos.

### Rules Engine
Deterministic recommendations such as:
- missing strength session
- required swim not completed
- optional swim eligibility
- excessive fatigue
- measurement reminder
- weight-loss trend too fast or too slow

### AI Coach
Used only when:
- the user asks for an explanation
- several signals conflict
- a weekly or monthly narrative summary is needed
- free-text nutrition or journal input must be interpreted

### Memory
Store useful long-term patterns, such as foods that improve satiety, recurring late-night eating, or performance changes around different nutrition strategies.

## Local AI design
The Android app must never depend directly on a specific model.

Use a stable API contract:
- `POST /v1/coach/analyze`
- `POST /v1/coach/chat`
- `GET /v1/health`

Define a provider interface in Python:
- `LocalModelProvider`
- future implementations may use Ollama, llama.cpp, MLX, Transformers or another OpenAI-compatible local server

The first implementation may be a mock provider. No cloud API key should be required.

## MVP sequence
1. Domain model and weekly plan
2. Rules Engine
3. Home screen with one primary daily action
4. Workout completion flow
5. Swim module integration
6. Nutrition logging
7. Local AI adapter
8. Weekly coaching summary

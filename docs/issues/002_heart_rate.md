# Issue #002 - Heart Rate Integration

You are a senior Wear OS engineer.

Goal:

Integrate real-time heart rate using Wear OS Health Services.

Requirements:

Create a HeartRateManager.

The manager should:

- Connect to Health Services
- Subscribe to heart rate updates
- Expose heart rate as StateFlow
- Handle unavailable heart rate gracefully
- Handle permission failures gracefully

Architecture:

UI
↓

WorkoutViewModel
↓

HeartRateManager
↓

Health Services API

Rules:

Do NOT place Health Services code inside the Activity.

Do NOT place business logic inside composables.

Keep all sensor logic isolated.

If heart rate is unavailable:

Display:

--
instead of a number.

If permission is denied:

Display a meaningful message.

Do not implement vibration.

Do not implement swimming logic.

Do not implement pool counting.

Only heart rate.

After implementation:

Explain:

- which APIs were used
- why they were chosen
- how battery consumption is affected
- how the implementation could be tested on a real Galaxy Watch Ultra
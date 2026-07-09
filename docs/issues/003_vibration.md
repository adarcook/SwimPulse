Add heart-rate threshold alerts.

Requirements:
1. Add configurable threshold constant, default 160 bpm.
2. If heart rate >= threshold, show status "האט".
3. Trigger a short vibration when crossing from below threshold to above threshold.
4. Do not vibrate repeatedly every update while already above threshold.
# SwimPulse

SwimPulse is a Wear OS swimming companion app for the Galaxy Watch Ultra.

Current goal: build the app incrementally, test every step on the watch, and only then push verified code to GitHub.

## Project status

- Wear OS app
- Kotlin
- Jetpack Compose for Wear OS
- Gradle wrapper included
- GitHub Actions CI runs `./gradlew assembleDebug` on pushes to `main`

## Recommended workflow

Before moving to the next development step:

1. Pull the latest code from GitHub.
2. Run the app locally on the Galaxy Watch Ultra.
3. Test manually on the watch.
4. Run a local Gradle build.
5. Push only after the watch test passes.
6. Confirm GitHub Actions passes.
7. Ask for a gate review before starting the next issue.

## Prerequisites

Install on the development machine:

- Android Studio
- Android SDK / Gradle support from Android Studio
- JDK 17, or use the JDK bundled with Android Studio
- Git

Use a real Galaxy Watch Ultra for manual testing. The emulator is useful for UI checks, but it cannot prove that watch sensors and Health Services behave correctly on the real device.

## Open the project

1. Clone the repository:

```bash
git clone https://github.com/adarcook/SwimPulse.git
cd SwimPulse
```

2. Open the `SwimPulse` folder in Android Studio.

3. Wait for Gradle sync to finish.

4. Confirm Android Studio recognizes the `:app` module.

## Enable Developer Options on the Galaxy Watch Ultra

On the watch:

1. Open **Settings**.
2. Go to **About watch**.
3. Open **Software information**.
4. Tap **Software version** several times until Developer Options are enabled.
5. Go back to **Settings**.
6. Open **Developer options**.
7. Enable **ADB debugging**.
8. Enable **Wireless debugging**.

Keep the watch and the computer on the same Wi‑Fi network.

## Pair the watch with ADB over Wi‑Fi

On the watch:

1. Open **Settings → Developer options → Wireless debugging**.
2. Choose **Pair new device**.
3. The watch will show:
   - IP address
   - pairing port
   - pairing code

On the computer, from the project folder:

```bash
adb pair WATCH_IP:PAIRING_PORT
```

Example:

```bash
adb pair 192.168.1.45:37123
```

Enter the pairing code shown on the watch.

Then connect to the normal wireless debugging port shown on the watch:

```bash
adb connect WATCH_IP:DEBUG_PORT
```

Example:

```bash
adb connect 192.168.1.45:45678
```

Verify that the watch is connected:

```bash
adb devices
```

You should see the watch listed as a connected device.

## Run from Android Studio

1. In Android Studio, choose the `app` run configuration.
2. Select the Galaxy Watch Ultra from the device list.
3. Click **Run**.
4. Wait for the APK to install and open on the watch.

If the watch does not appear:

```bash
adb devices
```

If it is missing, repeat the `adb connect WATCH_IP:DEBUG_PORT` step.

## Build locally before testing or pushing

From the project root:

```bash
./gradlew assembleDebug
```

On Windows PowerShell:

```powershell
.\gradlew.bat assembleDebug
```

A successful build means the APK was created locally, but it does not replace manual testing on the real watch.

## Install manually with ADB

Usually Android Studio is easier. If manual install is needed:

```bash
./gradlew assembleDebug
adb install -r app/build/outputs/apk/debug/app-debug.apk
```

On Windows PowerShell:

```powershell
.\gradlew.bat assembleDebug
adb install -r app\build\outputs\apk\debug\app-debug.apk
```

## Manual watch test checklist

For the current foundation stage, verify:

1. The app opens on the Galaxy Watch Ultra.
2. The `SwimPulse` start screen is visible.
3. Pressing `Start` moves to the workout screen.
4. The elapsed timer increases once per second.
5. The heart-rate placeholder still shows `-- bpm` until the heart-rate issue is implemented.
6. The Hebrew status text is readable.
7. Pressing `Stop` returns to the start state.
8. Text is large enough to read during swimming.
9. The app does not crash when the screen turns off and back on.

For future heart-rate work, also verify:

1. The app requests the required body sensor permission if needed.
2. Heart rate changes from `-- bpm` to a real value.
3. If permission is denied, the app shows a meaningful fallback.
4. Stopping the workout stops heart-rate tracking cleanly.

## Push only after manual testing

After a successful local build and watch test:

```bash
git status
git add .
git commit -m "Describe the completed issue"
git push
```

Then open the repository's **Actions** tab and confirm that Android CI passes.

## Current issue order

Issue prompts live under:

```text
docs/issues/
```

Recommended order:

1. `001_project_setup.md` — project foundation
2. `001b_fix_project_foundation.md` — if needed, complete MVVM/timer separation before heart rate
3. `002_heart_rate.md` — real heart-rate integration
4. `003_vibration.md` — vibration alerts
5. `004_turn_detection.md` — turn/push-off detection
6. `005_pool_count.md` — pool length counting

## Gate rule

Do not start the next issue just because the code compiles.

A step is considered ready only when:

1. The code builds locally.
2. The app was tested manually on the Galaxy Watch Ultra.
3. The code was pushed to GitHub.
4. GitHub Actions passed.
5. A gate review confirms it is safe to continue.

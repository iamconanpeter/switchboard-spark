# Switchboard Spark

Deterministic Android puzzle MVP where each tap flips the selected row and column. Match the board to the target pattern under par.

## MVP Features
- 12 authored puzzles (3x3 to 6x6)
- Deterministic row+column flip logic
- One undo charge per attempt
- Par-based medal grading
- Daily puzzle slot and local streak save

## Build

```bash
export JAVA_HOME=/home/openclaw/.openclaw/workspace/.local/jdk-17
export ANDROID_SDK_ROOT=/home/openclaw/.openclaw/workspace/.local/android-sdk
export PATH=$JAVA_HOME/bin:$PATH
./gradlew test assembleDebug
```

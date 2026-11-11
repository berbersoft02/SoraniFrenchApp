# Sorani-French Language Learning App

Native Android educational application designed for Sorani Kurdish speakers learning French.

## Features

* Structured Curriculum: Step-by-step lessons covering beginner to intermediate French vocabulary and grammar.
* Interactive Exercises: Multiple-choice quizzes, word matching, and sentence completion.
* Pronunciation Support: Text-to-Speech integration for French audio pronunciation.
* Offline Learning: Local storage for vocabulary bookmarks and lesson progress.
* Localized UI: Interface designed specifically for Sorani Kurdish readers with bidirectional text support.

## Project Structure

```
app/src/main/java/com/example/soranfrenchapp/
├── MainActivity.kt               # Entry point
├── data/
│   ├── models/                   # Phrase, category, and lesson data models
│   ├── Lesson.kt                 # Curriculum structures
│   ├── Exercise.kt               # Exercise validation logic
│   └── UserPreferences.kt       # Stored progress and settings
├── navigation/
│   ├── AppNavigation.kt          # Compose navigation graph
│   └── Screen.kt                 # Destination routes
└── ui/
    ├── screens/                  # Lesson, practice, dictionary, and profile screens
    ├── components/               # Custom UI cards, navigation bars, buttons
    └── theme/                    # Material 3 typography and colors
```

## Setup and Installation

Prerequisites: Android Studio, JDK 17, Android SDK (minSdk 24, targetSdk 34).

```bash
# Clone the repository
git clone https://github.com/berbersoft02/SoraniFrenchApp.git
cd SoraniFrenchApp

# Build project
./gradlew assembleDebug

# Run unit tests
./gradlew testDebugUnitTest
```

## License

MIT

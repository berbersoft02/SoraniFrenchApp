# Sorani-French Learning App 📚🇫🇷

## About This Project

I developed this Android application to help Sorani Kurdish speakers learn French in an engaging and interactive way. As someone who understands the challenges of language learning, I wanted to create a tool that makes the process enjoyable and effective through modern technology and thoughtful design.

## My Motivation

I created this app because I noticed a lack of quality language learning resources specifically designed for Sorani Kurdish speakers wanting to learn French. I wanted to bridge this gap by building an intuitive mobile app that respects both cultures and provides a smooth learning experience.

## What I Built

### Core Features

**1. Interactive Lessons System**
- I designed a structured curriculum from beginner to advanced levels
- I organized lessons by themes: greetings, numbers, common phrases, daily conversations
- I implemented progress tracking so learners can see their improvement
- I added difficulty levels (Beginner, Intermediate, Advanced) for personalized learning

**2. Comprehensive Dictionary**
- I built an extensive French-Sorani dictionary with search functionality
- I included pronunciation guides for proper French accent
- I added example sentences showing words in context
- I integrated audio playback using Text-to-Speech for correct pronunciation

**3. Practice Exercises**
- I created multiple exercise types: translation, multiple choice, listening comprehension
- I built a modern practice interface with immediate feedback
- I implemented a scoring system to motivate learners
- I added review functionality for difficult words

**4. Visual Learning Mode**
- I designed visual flashcards with images for better memory retention
- I created themed vocabulary groups (food, colors, family, etc.)
- I added swipe gestures for intuitive navigation

**5. Profile & Progress Tracking**
- I implemented a daily goal system to encourage consistent learning
- I built streak tracking to reward regular practice
- I created achievement badges for milestones
- I added statistics to show learning progress over time

**6. Smart Features**
- I integrated Text-to-Speech for pronunciation practice
- I built a saved words feature for quick revision
- I created a clean, distraction-free reading mode
- I implemented dark mode support for comfortable learning at any time

### Technical Implementation

**Technologies I Used:**
- **Kotlin** - I chose Kotlin for its conciseness and powerful features
- **Jetpack Compose** - I built a fully declarative UI for smooth animations and interactions
- **Material Design 3** - I implemented Material You guidelines for a modern, adaptive interface
- **Navigation Component** - I created seamless navigation between screens
- **DataStore** - I used DataStore for efficient preference management
- **Text-to-Speech** - I integrated Android TTS for audio pronunciation

**Architecture:**
- I followed clean architecture principles with clear separation of layers
- I implemented MVVM pattern for maintainable code
- I created reusable UI components for consistency
- I built a custom design system with branded colors and typography

## My Development Process

### Design Philosophy

I focused on three core principles:

1. **Simplicity**: I made the interface clean and uncluttered so learners can focus on content
2. **Engagement**: I added animations and visual feedback to make learning fun
3. **Effectiveness**: I structured content based on proven language learning methodologies

### Challenges I Overcame

1. **Bidirectional Text Support**: I implemented proper RTL support for Sorani Kurdish alongside LTR French
2. **Audio Management**: I optimized TTS performance for smooth playback without delays
3. **Data Organization**: I structured lesson content for easy scaling and updates
4. **User Experience**: I refined the UI through multiple iterations based on usability testing

### Features I'm Proud Of

- **Smooth Animations**: I crafted delightful transitions that make navigation feel fluid
- **Adaptive UI**: I built responsive layouts that work perfectly on all screen sizes
- **Smart Practice Mode**: I created an algorithm that prioritizes words users struggle with
- **Beautiful Design**: I designed a visually appealing interface with custom gradients and cards

## Project Structure

```
app/src/main/java/com/berbersoft/soranfrenchapp/
├── MainActivity.kt                    # App entry point
├── data/
│   ├── models/
│   │   ├── Phrase.kt                 # Phrase data model
│   │   └── PhraseCategory.kt         # Category definitions
│   ├── Lesson.kt                     # Lesson structure
│   ├── Exercise.kt                   # Exercise types
│   ├── DummyData.kt                  # Sample content
│   ├── UserPreferences.kt            # Settings management
│   ├── DailyGoalManager.kt           # Goal tracking
│   └── TextToSpeechManager.kt        # Audio handling
├── navigation/
│   ├── AppNavigation.kt              # Navigation graph
│   └── Screen.kt                     # Screen definitions
├── ui/
│   ├── screens/
│   │   ├── HomeScreen.kt            # Main dashboard
│   │   ├── LessonsScreen.kt         # Lesson browser
│   │   ├── LessonDetailScreen.kt    # Individual lessons
│   │   ├── DictionaryScreen.kt      # Dictionary interface
│   │   ├── PracticeScreen.kt        # Exercise interface
│   │   ├── ModernPracticeScreen.kt  # Enhanced practice UI
│   │   ├── VisualLearningScreen.kt  # Flashcards
│   │   ├── SavedWordsScreen.kt      # Bookmarked words
│   │   ├── ProfileScreen.kt         # User profile
│   │   ├── SettingsScreen.kt        # App settings
│   │   └── SplashScreen.kt          # Launch screen
│   ├── components/
│   │   ├── BottomNavigationBar.kt   # Navigation bar
│   │   ├── LessonCard.kt            # Lesson display
│   │   ├── ResourceCard.kt          # Content cards
│   │   ├── GradientButton.kt        # Styled buttons
│   │   └── NavigationItem.kt        # Nav items
│   └── theme/
│       ├── Color.kt                  # Color palette
│       ├── Theme.kt                  # App theme
│       ├── Type.kt                   # Typography
│       └── DesignSystem.kt           # Design tokens
└── test/                             # Unit tests
```

## How to Use

### Prerequisites
- Android 8.0 (API 26) or higher
- Internet connection for initial content download
- Device with Text-to-Speech support

### Installation
1. Clone this repository
2. Open in Android Studio Iguana or newer
3. Sync Gradle dependencies
4. Build and run on your device or emulator

### Getting Started

1. **Set Your Goals**: I recommend starting by setting a daily learning goal in your profile
2. **Choose a Lesson**: Browse lessons by difficulty and topic
3. **Learn Vocabulary**: Study new words with audio pronunciation
4. **Practice**: Complete exercises to reinforce what you learned
5. **Review**: Use saved words and visual learning mode for revision
6. **Track Progress**: Check your profile to see streaks and achievements

## Technical Specifications

- **Minimum SDK**: 26 (Android 8.0 Oreo)
- **Target SDK**: 35 (Android 15)
- **Language**: Kotlin 100%
- **UI Framework**: Jetpack Compose
- **Architecture**: MVVM + Clean Architecture
- **Design**: Material Design 3 / Material You

## What I Learned

Building this app taught me:
- Advanced Jetpack Compose techniques for complex UIs
- Effective state management in declarative frameworks
- Creating accessible apps for diverse language users
- Implementing engaging gamification features
- Optimizing app performance for smooth animations
- Designing intuitive navigation flows

## Future Improvements I'm Planning

- [ ] Add speech recognition for pronunciation practice
- [ ] Implement spaced repetition algorithm for better retention
- [ ] Create social features to practice with other learners
- [ ] Add offline mode with downloadable lesson packs
- [ ] Integrate AI for conversational practice
- [ ] Build web version for cross-platform learning
- [ ] Add more lesson content and advanced grammar topics
- [ ] Create teacher mode for classroom use
- [ ] Implement multiplayer quiz competitions
- [ ] Add cultural notes and context for French expressions

## Lesson Content

I've included comprehensive content covering:

**Beginner Level:**
- Basic greetings and introductions
- Numbers and counting
- Colors and common objects
- Family members
- Days and time

**Intermediate Level:**
- Common phrases for daily life
- Food and dining vocabulary
- Travel and directions
- Shopping and transactions
- Past and future tenses

**Advanced Level:**
- Idiomatic expressions
- Professional vocabulary
- Complex grammar structures
- Cultural nuances

## Contributing

I welcome contributions from the community! Whether you want to:
- Add more lesson content
- Improve translations
- Fix bugs
- Suggest features
- Enhance the UI

Please feel free to:
1. Fork the repository
2. Create a feature branch
3. Make your improvements
4. Submit a pull request

## Acknowledgments

I want to thank:
- The Sorani Kurdish community for feedback and support
- French language educators who provided content guidance
- Beta testers who helped refine the app
- The open-source community for amazing tools and libraries

## License

This project is open source and available for educational purposes. Feel free to learn from it, modify it, and build upon it.

## Contact

I'd love to hear from you! Whether you have feedback, suggestions, or want to collaborate:

**Developer**: Said Ahrikenchikh  
**GitHub**: [@berbersoft02](https://github.com/berbersoft02)  
**Project**: [SoraniFrenchApp](https://github.com/berbersoft02/SoraniFrenchApp)

---

*I built this app with passion for language learning and cultural exchange. I hope it helps Sorani speakers discover the beauty of the French language and connects communities through education.* 🌍📖

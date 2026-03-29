# CuliNote

A recipe and meal-tracking Android app built as a capstone project at Bunker Hill Community College. CuliNote allows users to search for recipes, save meals, and track expiration dates — all from a clean, responsive mobile interface.

---

## Screenshots

_Coming soon_

---

## Features

- Search recipes using the Spoonacular API
- Save meals to a local Room database
- Track meal expiration dates
- Clean RecyclerView-based UI with card layouts
- Full error handling for network and caching failures

---

## Tech Stack

| Layer | Technology |
|---|---|
| Language | Kotlin |
| Architecture | Android MVC |
| Networking | Retrofit + OkHttp3 |
| Local Storage | Room Database |
| API | Spoonacular Food API |
| IDE | Android Studio |

---

## Getting Started

### Prerequisites

- Android Studio (latest stable)
- Android SDK 26+
- A free Spoonacular API key — get one at [spoonacular.com/food-api](https://spoonacular.com/food-api)

### Setup

1. Clone the repository
```bash
   git clone https://github.com/Deydrik/CuliNote.git
```

2. Open the project in Android Studio

3. Add your Spoonacular API key:
   - Open `local.properties` in the project root
   - Add the following line:
```
   SPOONACULAR_API_KEY=your_api_key_here
```

4. Build and run on an emulator or physical device

---

## Project Structure
```
app/
├── data/
│   ├── local/        # Room database, DAOs, entities
│   └── remote/       # Retrofit API service and models
├── ui/
│   ├── recipes/      # Recipe search and display screens
│   └── meals/        # Saved meals and expiration tracking
└── utils/            # Helper classes and extensions
```

---

## Status

Complete — built and submitted as a capstone project in June 2025.

---

## Author

**Mario Smith-Pignone**
[GitHub](https://github.com/Deydrik) • [Email](mailto:pignone.mario@yahoo.com)

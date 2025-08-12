# Weather App (Room + API Version)

A modern Android application that fetches real-time weather data from a public API, stores it locally using Room Database, and displays it in a clean, user-friendly interface.

## 📌 Features
- **Live Weather Data** — Fetches current weather details from an external API.
- **Room Database Integration** — Stores recent weather data for offline access.
- **MVVM Architecture** — Separates UI, business logic, and data handling for maintainability.
- **API Service** — Uses Retrofit for network calls.
- **Dependency Injection** — Simplifies object creation and testing.
- **Jetpack Components** — ViewModel, LiveData, and more for reactive UI updates.

## 🛠 Tech Stack
- **Language:** Kotlin
- **Architecture:** MVVM
- **Network:** Retrofit2 + Gson
- **Local Storage:** Room Database
- **UI:** Jetpack Compose / XML (depending on project setup)
- **Build System:** Gradle (Kotlin DSL)

## 📂 Project Structure
```
app/
 ├── data/         # Room entities, DAO interfaces, repositories
 ├── network/      # Retrofit API service interfaces
 ├── ui/           # Activities, Fragments, and ViewModels
 ├── utils/        # Helper classes and constants
```

## 🚀 Setup Instructions
1. **Clone the repository**
   ```bash
   git clone <your-repo-link>
   cd Weather-App-version-room-api-
   ```
2. **Open in Android Studio** (latest stable version recommended).
3. **Add your API key**:
   - Open `ApiService.kt` or `Constants.kt` (depending on where it's stored).
   - Replace the placeholder with your weather API key.
4. **Build & Run** on an emulator or physical device.

## 📦 Dependencies
Main dependencies used:
- `androidx.room:room-runtime`
- `androidx.lifecycle:lifecycle-viewmodel-ktx`
- `com.squareup.retrofit2:retrofit`
- `com.squareup.retrofit2:converter-gson`
- `org.jetbrains.kotlinx:kotlinx-coroutines-android`

For full dependency list, check `app/build.gradle.kts`.

## 📝 Notes
- Internet permission is required (`<uses-permission android:name="android.permission.INTERNET"/>` in `AndroidManifest.xml`).
- The app caches the latest fetched weather data for offline use.

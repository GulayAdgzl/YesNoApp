# YesNo App 

## Overview
YesNo is a simple Android application that fetches random Yes/No responses from an external API. The app displays a video background while showing the response along with a corresponding image.




https://github.com/user-attachments/assets/18f4dd3a-e1e1-46ae-a2b3-d970fd08279d






## Architecture
The application follows the MVVM (Model-View-ViewModel) architecture pattern with dependency injection using Hilt. The main components are:

### UI Layer
- `MainActivity`: The entry point of the application that hosts the main composable screen
- `YesNoScreen`: A composable function that displays the video background and the Yes/No response

### ViewModel Layer
- `YesNoViewModel`: Manages UI-related data and exposes StateFlow and LiveData objects for the UI to observe

### Repository Layer
- `YesNoRepository`: Handles data operations and provides clean APIs to the ViewModel

### Network Layer
- `YesNoAPI`: Retrofit interface for API communication
- `RandomYesNoModel`: Provides dependencies for network operations using Hilt

### Data Models
- `YesNoResponse`: Data class representing the API response containing the yes/no answer, a forced flag, and an image URL

## Features
- Fetches random Yes/No responses from an external API
- Displays responses with corresponding images
- Edge-to-edge display with video background
- Supports both StateFlow and LiveData implementations

## Dependencies
- Jetpack Compose for UI
- Hilt for dependency injection
- Retrofit for network calls
- OkHttp for HTTP client
- Kotlin Coroutines for asynchronous operations
- StateFlow and LiveData for observable data holders

## Setup Instructions
1. Clone the repository
2. Open the project in Android Studio
3. Sync Gradle files
4. Run the application on an emulator or physical device

## API Information
The application uses the public API at [yesno.wtf](https://yesno.wtf/api) which returns random Yes/No answers with corresponding GIF images.

## Project Structure
```
app/
├── src/
│   ├── main/
│   │   ├── java/com/yourpackage/yesno/
│   │   │   ├── di/
│   │   │   │   └── RandomYesNoModel.kt
│   │   │   ├── network/
│   │   │   │   └── YesNoAPI.kt
│   │   │   ├── repository/
│   │   │   │   └── YesNoRepository.kt
│   │   │   ├── ui/
│   │   │   │   ├── MainActivity.kt
│   │   │   │   └── YesNoScreen.kt
│   │   │   ├── viewmodel/
│   │   │   │   └── YesNoViewModel.kt
│   │   │   └── model/
│   │   │       └── YesNoResponse.kt
│   │   └── res/
│   │       └── raw/
│   │           └── clouds.mp4
```


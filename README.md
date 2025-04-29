# JobSearch App

<div align="center">
  <h1>JobSearch - Modern Android Job Hunting Application</h1>
  <p>
    JobSearch is a modern Android application that demonstrates current Android development practices with Jetpack Compose, Hilt, Coroutines, Flow, Jetpack (Room, ViewModel), and Material Design 3 based on MVVM architecture.
  </p>
</div>

![Newsletter CTA section](https://github.com/user-attachments/assets/21af3594-6d83-4924-93b7-e06b50cec795)

## Features

- **Seamless Job Discovery**: Browse through available jobs with smooth infinite scrolling
- **Job Details**: View comprehensive information about each job opportunity
- **Bookmarking System**: Save your favorite jobs for later reference
- **Offline Support**: Access your bookmarked jobs even without an internet connection
- **Clean UI/UX**: Modern Material Design 3 interface with dark mode support
- **State Management**: Proper handling of loading, error, and empty states

## Tech Stack & Open-source Libraries

- **Kotlin**: 100% [Kotlin](https://kotlinlang.org/) based with [Coroutines](https://github.com/Kotlin/kotlinx.coroutines) + [Flow](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.flow/) for asynchronous operations
- **Jetpack Libraries**:
  - **Jetpack Compose**: Modern declarative UI toolkit
  - **ViewModel**: Lifecycle-aware data management for UI components
  - **Navigation**: Compose Navigation for seamless screen transitions
  - **Room**: SQLite abstraction layer for fluid local database access
  - **Paging 3**: Library for efficient data loading from network and local storage
- **Dependency Injection**:
  - **Hilt**: Simplified dependency injection built on Dagger
- **Network**:
  - **Retrofit2**: Type-safe HTTP client for API communication
  - **OkHttp3**: Efficient HTTP client with interceptors, logging, and caching
  - **Gson**: JSON serialization/deserialization
- **UI Components**:
  - **Material Design 3**: Latest Material Design components and theming
- **Architecture**:
  - **MVVM**: Clean separation of concerns (Model-View-ViewModel)
  - **Repository Pattern**: Single source of truth for data operations

## Architecture

JobSearch adheres to the MVVM architecture and implements the Repository pattern, following [Google's official architecture guidance](https://developer.android.com/topic/architecture).

### Architecture Overview

The application is divided into three distinct layers:

1. **UI Layer**: Manages UI components and user interactions
2. **Domain Layer**: Contains business logic and use cases
3. **Data Layer**: Handles data operations from various sources

This clean architecture ensures:
- Loose coupling between components
- High scalability and maintainability
- Easy testing of individual components

```
project_root
├── data
│   ├── local
│   │   ├── dao
│   │   ├── entity
│   │   ├── mapper
│   │   └── AppDatabase
│   ├── model
│   ├── network
│   │   ├── api
│   │   ├── paging
│   │   └── ApiService
│   └── repository
│       └── JobRepositoryImplementation
├── di
│   └── AppModule
├── domain
│   ├── repository
│       └── JobRepository     
├── ui
│   ├── bookmark
│   │   ├── BookmarksScreen.kt
│   │   └── BookmarkViewModel.kt
│   ├── common
│   │   ├── BottomNav.kt
│   │   └── JobCard.kt
│   ├── jobs
│   │   ├── components
│   │   ├── JobsScreen.kt
│   │   └── JobsViewModel.kt
│   ├── job_detail
│   │   ├── JobDetailScreen.kt
│   │   └── JobDetailViewModel.kt
│   ├── theme
│   │   ├── Color.kt
│   │   ├── Theme.kt
│   │   └── Type.kt
│   └── MainScreen.kt
├── util
│   └── Constants.kt
└── MainActivity.kt
```

### UI Layer

The UI layer is built entirely with Jetpack Compose and follows these principles:

- **State Hoisting**: UI states are managed in ViewModels and passed down to composables
- **Single Source of Truth**: UI observes data flows from ViewModel
- **Unidirectional Data Flow**: Events flow up, state flows down

Key Components:
- **JobsScreen**: Displays job listings with infinite scroll capability
- **BookmarksScreen**: Shows saved jobs stored locally
- **Common Components**: Reusable UI elements shared across screens

### Domain Layer

The domain layer contains the business logic of the application, independent of other layers:

- **Repository Interfaces**: Define contracts for data operations
- **Use Cases**: Encapsulate specific business logic operations:
  - `GetJobs`: Retrieves paginated job listings
  - `BookmarkJob`: Saves or removes a job from bookmarks
  - `GetBookmarkedJobs`: Retrieves locally stored bookmarked jobs

### Data Layer

The data layer manages data operations and serves as the single source of truth:

- **Local Storage**:
  - **Room Database**: Stores bookmarked jobs for offline access
  - **DAOs**: Data Access Objects for database operations
  - **Entities**: Database table schemas
- **Remote Data**:
  - **Retrofit API**: Handles network requests
  - **Paging Source**: Manages pagination for infinite scrolling
  - **DTOs**: Data Transfer Objects for network responses
- **Repository Implementation**: Coordinates between local and remote data sources

## How to Use the App

1. **Browsing Jobs**:
   - Open the app to see the Jobs tab
   - Scroll through available jobs with infinite loading
   - Jobs display title, location, salary and contact information

   ![assignment](https://github.com/user-attachments/assets/aa5d8d9e-51ee-455c-b6c8-be8274808957)


2. **Bookmarking Jobs**:
   - Tap the bookmark icon on any job card or detail screen
   - Bookmarked jobs are saved for offline viewing

   ![assignment_1](https://github.com/user-attachments/assets/ebd62627-cc34-4fc1-ace3-5fa806af4e47)


## State Management

The app handles various states throughout the user experience:

- **Loading State**: Displays progress indicators during data fetching
- **Empty State**: Shows appropriate messages when no data is available
- **Error State**: Presents error messages with retry options
- **Success State**: Renders content when data is successfully loaded

## Dark Mode Support

JobSearch features full dark mode support that follows system settings:

**Light Mode**:

![image](https://github.com/user-attachments/assets/9c7e87c8-4752-4c84-9c7a-1ce21bff982c)


**Dark Mode**:

![image](https://github.com/user-attachments/assets/5401b633-5f47-431b-8378-38e106d4ffcb)


## Getting Started

Follow these steps to build and run the project:

1. Clone the repository:
   ```
   git clone https://github.com/yourusername/JobSearch.git
   ```

2. Open the project in Android Studio Arctic Fox or later

3. Sync Gradle and build the project

4. Run on an emulator or physical device

## Requirements

- Android Studio Arctic Fox or later
- Minimum SDK 21
- Target SDK 34
- Kotlin 1.9.0+
- Java 11

## License

```
Copyright 2025 Utkarsh Singh

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```

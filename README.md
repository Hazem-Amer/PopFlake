# Popflake 🎬

**Popflake** is a feature-rich Android application developed as part of my summer training in Android development. The app showcases upcoming and currently running movies, providing users with detailed information such as movie posters, descriptions, ratings, and more. Built with clean architecture principles, Popflake ensures a smooth and engaging user experience through efficient data management and modern UI elements.

## Table of Contents

- [Features](#features)
- [Architecture](#architecture)
- [Technologies and Libraries](#technologies-and-libraries)
- [Screenshots](#screenshots)
- [Getting Started](#getting-started)
- [Contributing](#contributing)
- [License](#license)

## Features

- **Movies Showcase**: Discover upcoming and currently running movies with rich details including posters, descriptions, and ratings.
- **Movie Search**: Search for movies by title, genre, or actors with fast and accurate results.
- **Dark Mode and Light Mode**: Seamless support for both dark and light themes, adapting to system settings or user preference.
- **Loading Indicators with Shimmer**: Smooth animations during data loading to enhance user experience.
- **Manual Data Refresh**: Ensure access to the latest movie information with a simple swipe to refresh.
- **User Feedback**: Dedicated section for users to submit their thoughts and suggestions to help improve the app.

## Architecture

Popflake is built using the **MVVM (Model-View-ViewModel)** architecture pattern, following clean architecture principles. This ensures a scalable, maintainable, and testable codebase.

### Key Components:

- **ViewModel**: Manages UI-related data in a lifecycle-conscious way, allowing data to survive configuration changes.
- **Use Cases**: Encapsulate business logic, making it easy to test and maintain.
- **Repository**: Acts as a mediator between different data sources, ensuring a single source of truth.

## Technologies and Libraries

- **[Retrofit](https://square.github.io/retrofit/)**: For seamless API calls and integrating external services.
- **[Shimmer Layout](https://facebook.github.io/shimmer-android/)**: Provides smooth loading indicators, enhancing user experience.
- **[Room Database](https://developer.android.com/jetpack/androidx/releases/room)**: Manages local data persistence, ensuring offline access.
- **[Hilt Dagger](https://developer.android.com/training/dependency-injection/hilt-android)**: Simplifies dependency injection and improves code organization.
- **[Picasso](https://square.github.io/picasso/)**: Efficiently loads and displays movie posters.

## Screenshots

![Picture12](https://github.com/user-attachments/assets/b85ebc59-0e1d-4442-8983-8ba8013555d4)  ![Picture6](https://github.com/user-attachments/assets/9595bab5-5e89-43ee-ab17-19f72184e461)  ![Picture7](https://github.com/user-attachments/assets/d9690fbf-7622-4356-882d-2a056a55a56c)  ![Picture8](https://github.com/user-attachments/assets/b7e4ee5f-0818-4751-9e2b-41fc848c515f)
![Picture9](https://github.com/user-attachments/assets/9ed67c81-fb17-46e9-963c-7e856bf84489)  ![Picture10](https://github.com/user-attachments/assets/98855716-7ba2-4dde-8b23-c35feda892f3)  ![Picture14 (Custom)](https://github.com/user-attachments/assets/b37c741e-2ddb-4814-9ef5-1cde818e5a3f)
![Picture13](https://github.com/user-attachments/assets/106a439a-1397-4522-84a3-3c7fcb8ed879)

## Getting Started

To get a local copy of the project up and running, follow these simple steps:

### Prerequisites

- Android Studio installed on your development machine.
- A device or emulator running Android API level 21 or higher.

### Installation

1. Clone the repository:
    ```bash
    git clone https://github.com/yourusername/popflake.git
    ```
2. Open the project in Android Studio.

3. Build and run the app on your device or emulator.

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for more details.

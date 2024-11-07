# Unplashy

Photo and wallpaper application. 📸

## Description

Search, discover and download hundreds of photos and wallpapers thanks to the [Unsplash](https://unsplash.com/developers) API.
The app is written in Kotlin and Compose, it also uses some third-party libraries like Retrofit and Mockk. It also uses several Unsplash APIs.

### Features

- Search.
- Download images.
- Manual light/dark mode switching and dynamic color support for Android 12+.

> [!IMPORTANT]
In order to access the content of the app, you will need an API_KEY which you can obtain by creating an account on Unsplash.
>
> Once you get the key, you will need to place it in the LOCAL.PROPERTIES file under the name "clientId".
> Then you will rebuild the project and you should be ready to go.

## TechStack

- Kotlin
- Serialization
- Coroutines
- Kps
- Compose
- Hilt
- ViewModel
- Navigation
- Paging 3
- Data Store
- Splash Screen
- Retrofit
- OkHttp
- Coil
- Lottie
- MockK

## Architecture

The pattern used for this project is MVVM (Model-View-ViewModel) which is a widely used architectural pattern in Android development that promotes separation of concerns, testability, and maintainability. It divides an application into three interconnected parts:

1. **Model**: Represents the data and business logic of the application. It's responsible for fetching, storing, and manipulating data. This might include data classes, repositories, and data sources like databases or network APIs.
2. **View**: In Compose, the View is represented by composable functions. These functions describe the UI declaratively, defining how it should look based on the current state. Instead of directly manipulating UI elements, composable functions recompose when the underlying data changes.
3. **ViewModel**: Acts as an intermediary between the Model and the View. It exposes data from the Model in a way that's easily consumable by the View. It also handles user interactions from the View and updates the Model accordingly. The ViewModel is lifecycle-aware, meaning it survives configuration changes like screen rotations.

![Mvvm arch](https://github.com/user-attachments/assets/011add8b-cd32-4ae7-b78e-60a2ca578a59)

## Navigation

![NavigationGraph](https://github.com/user-attachments/assets/1443d904-0300-4a52-a862-0c2e1622c2c4)

## Screenshots

TODO

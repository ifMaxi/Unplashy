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

The architecture used is the one recommended by Google, usually called MVVM (Model - View - ViewModel)

This is divided into:

- Model: Which represents the data and business logic
- View: Which represents the UI
- ViewModel: Which represents the bridge between the View and the Model

![Mvvm arch](https://github.com/user-attachments/assets/011add8b-cd32-4ae7-b78e-60a2ca578a59)

## Navigation

TODO

## Screenshots

TODO

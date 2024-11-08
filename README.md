# Unplashy

Photo and wallpaper application. 📸

## Description

Search, discover and download hundreds of photos and wallpapers thanks to the [Unsplash](https://unsplash.com/developers) API.
The app is written in Kotlin and Compose, it also uses some third-party libraries like Retrofit and Mockk. It also uses several Unsplash APIs.

It has temporary offline support through OkHttp caching, it decides not to place a database for better offline support due to the large amount of data being handled. 
It also allows downloading the image to the cell phone's internal storage, as well as the use of Intents to share the image or view it directly in the browser.
It also contains a basic search engine, which can obtain the desired images through a query.

### Features

- Search.
- Download images.
- Manual light/dark mode switching and dynamic color support for Android 12+.

> [!IMPORTANT]
> In order to access the content of the app, you will need an API_KEY which you can obtain by creating an account on Unsplash.
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

Navigation graph describing the flow of the app. 

![NavigationGraph](https://github.com/user-attachments/assets/1443d904-0300-4a52-a862-0c2e1622c2c4)

## Screenshots

### Light Mode
| Home - Topbar | Home - Bottombar | Detail |
| ------------- | ------------ | ------------ |
| <img src="https://github.com/user-attachments/assets/8d206cc9-6754-4636-b9d9-eefd9fdeef22" width="190" height="400"> | <img src="https://github.com/user-attachments/assets/005297cb-d96c-4583-9731-fbe8d4ee3234" width="190" height="400"> | <img src="https://github.com/user-attachments/assets/bd1437f8-6c83-47a8-a546-4619d857b9d5" width="190" height="400"> |

| Topics | Topics - Content | Search - Results |
| ------------- | ------------ | ------------ |
| <img src="https://github.com/user-attachments/assets/dff88c2b-9b1a-4afb-aae8-2d5dbb710e62" width="190" height="400"> | <img src="https://github.com/user-attachments/assets/b3063ce2-3b69-4e6e-92bd-e99310ea9307" width="190" height="400"> | <img src="https://github.com/user-attachments/assets/67aaf1df-f4af-4807-9a82-829501e2417e" width="190" height="400"> |

### Dark Mode

| Home - Topbar | Home - Bottombar | Detail |
| ------------- | ------------ | ------------ |
| <img src="https://github.com/user-attachments/assets/60492a50-a32b-43e6-a837-eab8e23daf13" width="190" height="400"> | <img src="https://github.com/user-attachments/assets/eadfcc18-03b5-4c72-a5b8-c1bbb2e252fa" width="190" height="400"> | <img src="https://github.com/user-attachments/assets/70a62596-7d4b-4a53-8812-3b175accbc9c" width="190" height="400"> |

| Topics | Topics - Content | Search - Results |
| ------------- | ------------ | ------------ |
| <img src="https://github.com/user-attachments/assets/b1801047-4064-4fd7-9e18-a80136b23efa" width="190" height="400"> | <img src="https://github.com/user-attachments/assets/407f2565-2af1-44b8-81c6-84f9e6459c7b" width="190" height="400"> | <img src="https://github.com/user-attachments/assets/2f34cace-a1ac-4834-a182-d74d34e72385" width="190" height="400"> |

### Pinch/Zoom image

| Pinch - Zoom |
| ------------- |
| <img src="https://github.com/user-attachments/assets/21635008-7b5d-49a3-b7ba-13dc88a842a5" width="190" height="400"> |

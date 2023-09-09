<div align="center">
<h1 align="center">
<img src="https://raw.githubusercontent.com/PKief/vscode-material-icon-theme/ec559a9f6bfd399b82bb44393651661b08aaf7ba/icons/folder-markdown-open.svg" width="100" />
<br>HackerUAndroidProject
</h1>
<h3>◦ Unleash your coding powers</h3>
<h3>◦ Developed with the software and tools listed below.</h3>

<p align="center">
<img src="https://img.shields.io/badge/Kotlin-7F52FF.svg?style&logo=Kotlin&logoColor=white" alt="Kotlin" />
<img src="https://img.shields.io/badge/Gradle-02303A.svg?style&logo=Gradle&logoColor=white" alt="Gradle" />
<img src="https://img.shields.io/badge/Markdown-000000.svg?style&logo=Markdown&logoColor=white" alt="Markdown" />
</p>
</div>

---

## 📒 Table of Contents

- [📒 Table of Contents](#-table-of-contents)
- [📍 Overview](#-overview)
- [⚙️ Features](#-features)
- [📂 Project Structure](#-project-structure)
- [🧩 Modules](#-modules)
- [🚀 Getting Started](#-getting-started)
- [🗺 Roadmap](#-roadmap)
- [🤝 Contributing](#-contributing)
- [📄 License](#-license)
- [👏 Acknowledgments](#-acknowledgments)

---

## 📍 Overview

The project is an Android application for a trivia game. It allows users to select categories, answer questions of different difficulties, and view their scores on a scoreboard. The application uses an API to fetch trivia questions and stores them locally in a Room database. It also includes features like data binding, dependency injection with Dagger Hilt, and navigation components for a seamless user experience. Overall, the project provides an engaging trivia game experience for users on the Android platform.

---

## ⚙️ Features

| Feature                | Description                           |
| ---------------------- | ------------------------------------- |
| **⚙️ Architecture**     | The codebase follows the Single Activity and ViewModel architecture pattern, separating UI logic from underlying data and business logic. The use of Kotlin, Dagger Hilt, and Room database provides a clear separation of concerns, promoting maintainability and reusability.|
| **📖 Documentation**   | |
| **🔗 Dependencies**    | The codebase relies on several widely-used libraries and technologies such as AndroidX, Kotlin coroutines, Dagger Hilt, Retrofit, Room database, Glide, Picasso, and Navigation. These dependencies enhance development speed, modularity, and performance, while also providing powerful functionalities out-of-the-box.|
| **🧩 Modularity**      | The codebase is well-structured, employing many modular components like adapters, DAOs, models, fragments, and view models. Each component serves specific purposes, making the codebase easier to understand, maintain, and test. The use of Dagger Hilt for dependency injection further enhances modularity by decoupling dependencies and improving testability.|
| **⚡️ Performance**      | The system's performance relies on efficient technologies like Kotlin coroutines, Room database, and Retrofit. The codebase appears well-optimized, using asynchronous operations where necessary to avoid blocking the UI thread. However, proper profiling and performance testing should be conducted to ensure optimal performance under various conditions and devices.|

---

## 📂 Project Structure

```Kotlin
HackerUAndroidProject
├─ app
│  └─ src
│     ├─ main
│     │  ├─ AndroidManifest.xml
│     │  ├─ java
│     │  │  └─ com
│     │  │     └─ example
│     │  │        └─ finalprojectapi
│     │  │           ├─ adapters
│     │  │           │  ├─ CategoryAdapter.kt
│     │  │           │  └─ ScoreboardAdapter.kt
│     │  │           ├─ converter
│     │  │           │  └─ Converters.kt
│     │  │           ├─ dao
│     │  │           │  ├─ CategoryDao.kt
│     │  │           │  ├─ QuestionDao.kt
│     │  │           │  └─ ScoreBoardDao.kt
│     │  │           ├─ data
│     │  │           │  ├─ CategoryResponse.kt
│     │  │           │  └─ TriviaResponse.kt
│     │  │           ├─ di
│     │  │           │  ├─ AppModule.kt
│     │  │           │  └─ DatabaseModule.kt
│     │  │           ├─ entities
│     │  │           │  └─ AppDatabase.kt
│     │  │           ├─ MainActivity.kt
│     │  │           ├─ model
│     │  │           │  ├─ Category.kt
│     │  │           │  ├─ Question.kt
│     │  │           │  └─ ScoreBoard.kt
│     │  │           ├─ MyApplication.kt
│     │  │           ├─ network
│     │  │           │  └─ ApiService.kt
│     │  │           ├─ repository
│     │  │           │  └─ TriviaRepository.kt
│     │  │           └─ ui
│     │  │              ├─ category
│     │  │              │  └─ CategoryFragment.kt
│     │  │              ├─ difficulty
│     │  │              │  └─ DifficultyFragment.kt
│     │  │              ├─ question
│     │  │              │  └─ QuestionFragment.kt
│     │  │              ├─ result
│     │  │              │  └─ ResultFragment.kt
│     │  │              ├─ start
│     │  │              │  └─ WelcomeFragment.kt
│     │  │              └─ viewmodels
│     │  │                 └─ SharedViewModel.kt
│     │  └─ res
│     │     ├─ drawable
│     │     │  ├─ boardgame.png
│     │     │  ├─ books.png
│     │     │  ├─ comicbook.png
│     │     │  ├─ easy.png
│     │     │  ├─ filmreel.png
│     │     │  ├─ gadgets.png
│     │     │  ├─ globe.png
│     │     │  ├─ hard.png
│     │     │  ├─ ic_dashboard_black_24dp.xml
│     │     │  ├─ ic_home_black_24dp.xml
│     │     │  ├─ ic_launcher_background.xml
│     │     │  ├─ ic_launcher_foreground.xml
│     │     │  ├─ ic_notifications_black_24dp.xml
│     │     │  ├─ joystick.png
│     │     │  ├─ laptop.png
│     │     │  ├─ math.png
│     │     │  ├─ medium.png
│     │     │  ├─ mixed.png
│     │     │  ├─ modernart.png
│     │     │  ├─ musicalnotes.png
│     │     │  ├─ questionmark.png
│     │     │  ├─ retrotv.png
│     │     │  ├─ ricksanchez.png
│     │     │  ├─ sportsmode.png
│     │     │  ├─ sportutilityvehicle.png
│     │     │  ├─ star.png
│     │     │  ├─ testtube.png
│     │     │  ├─ theatremask.png
│     │     │  ├─ trojanhorse.png
│     │     │  └─ unknown.png
│     │     ├─ layout
│     │     │  ├─ activity_main.xml
│     │     │  ├─ custom_toast_layout.xml
│     │     │  ├─ fragment_category.xml
│     │     │  ├─ fragment_difficulty.xml
│     │     │  ├─ fragment_question.xml
│     │     │  ├─ fragment_result.xml
│     │     │  ├─ fragment_welcome.xml
│     │     │  ├─ item_category.xml
│     │     │  └─ item_scoreboard.xml
│     │     ├─ menu
│     │     │  └─ overflow_menu.xml
│     │     ├─ mipmap-anydpi
│     │     │  ├─ ic_launcher.xml
│     │     │  └─ ic_launcher_round.xml
│     │     ├─ mipmap-anydpi-v26
│     │     │  ├─ ic_launcher.xml
│     │     │  └─ ic_launcher_round.xml
│     │     ├─ mipmap-hdpi
│     │     │  ├─ ic_launcher.webp
│     │     │  └─ ic_launcher_round.webp
│     │     ├─ mipmap-mdpi
│     │     │  ├─ ic_launcher.webp
│     │     │  └─ ic_launcher_round.webp
│     │     ├─ mipmap-xhdpi
│     │     │  ├─ ic_launcher.webp
│     │     │  └─ ic_launcher_round.webp
│     │     ├─ mipmap-xxhdpi
│     │     │  ├─ ic_launcher.webp
│     │     │  └─ ic_launcher_round.webp
│     │     ├─ mipmap-xxxhdpi
│     │     │  ├─ ic_launcher.webp
│     │     │  └─ ic_launcher_round.webp
│     │     ├─ navigation
│     │     │  └─ mobile_navigation.xml
│     │     ├─ values
│     │     │  ├─ colors.xml
│     │     │  ├─ dimens.xml
│     │     │  ├─ strings.xml
│     │     │  ├─ styles.xml
│     │     │  └─ themes.xml
│     │     ├─ values-night
│     │     │  ├─ strings.xml
│     │     │  └─ themes.xml
│     │     └─ xml
│     │        ├─ backup_rules.xml
│     │        └─ data_extraction_rules.xml
├─ gradle
│  └─ wrapper
│     ├─ gradle-wrapper.jar
│     └─ gradle-wrapper.properties
├─ gradle.properties
├─ gradlew
├─ gradlew.bat
└─ README.md
```

---

## 🧩 Modules

<details closed><summary>Root</summary>

| File                                                                                                                                                                    | Summary                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            |
| ----------------------------------------------------------------------------------------------------------------------------------------------------------------------- | -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| [build.gradle](https://github.com/ErezD1/HackerUAndroidProject/blob/main/build.gradle)                                                                                  | This build file sets up the necessary configurations for Android application and library projects. It includes plugins for Android, Kotlin, and Dagger Hilt, which are used for building and dependency injection in Android projects.                                                                                                                                                                                                                                                                                                                                                                                                                                                             |
| [settings.gradle](https://github.com/ErezD1/HackerUAndroidProject/blob/main/settings.gradle)                                                                            | This code sets up the plugin management and dependency resolution for the Gradle build. It adds repositories for accessing dependencies and defines the project name as "FinalProjectApi" with a module named "app".                                                                                                                                                                                                                                                                                                                                                                                                                                                                               |
| [build.gradle](https://github.com/ErezD1/HackerUAndroidProject/blob/main/app\build.gradle)                                                                              | This code sets up an Android application using Kotlin and includes various dependencies such as `Dagger Hilt`, `Kotlin coroutines`, `Room database`, `Retrofit`, `Picasso`, `Glide`, and `Navigation`. It also includes configuration for view binding and data binding.                                                                                                                                                                                                                                                                                                                                                                                                                                         |
| [MainActivity.kt](https://github.com/ErezD1/HackerUAndroidProject/blob/main/app\src\main\java\com\example\finalprojectapi\MainActivity.kt)                              | The code initializes an `MainActivity` and sets up the navigation host fragment, navigation controller, and app bar configuration to navigate between different fragments in the app. It also sets the action bar for the activity.                                                                                                                                                                                                                                                                                                                                                                                                                                                                      |
| [MyApplication.kt](https://github.com/ErezD1/HackerUAndroidProject/blob/main/app\src\main\java\com\example\finalprojectapi\MyApplication.kt)                            | This code defines the `MyApplication` class for the Final Project API app. It is annotated with `HiltAndroidApp` to enable dependency injection using Hilt.                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              |
| [CategoryAdapter.kt](https://github.com/ErezD1/HackerUAndroidProject/blob/main/app\src\main\java\com\example\finalprojectapi\adapters\CategoryAdapter.kt)               | This code is an adapter for a `RecyclerView` in an Android app. It binds a list of `Category` objects to the `RecyclerView` items and handles item clicks. It also maps each category name to a corresponding icon resource name or URL and displays the icon using `Glide` library. The `CategoryAdapter` class extends `ListAdapter` and uses a `DiffUtil.ItemCallback` for efficient list updates. Overall, this adapter simplifies the implementation of the `RecyclerView` for displaying and interacting with categories.                                                                                                                                                                                    |
| [ScoreboardAdapter.kt](https://github.com/ErezD1/HackerUAndroidProject/blob/main/app\src\main\java\com\example\finalprojectapi\adapters\ScoreboardAdapter.kt)           | The `ScoreboardAdapter` is responsible for creating and binding views for each item in the scoreboard list. It extends `RecyclerView.Adapter` and uses a `ViewHolder` to optimize performance. The adapter retrieves the values for each item from the `ScoreBoard` model and binds them to the corresponding views, such as the username, date, category, difficulty, and score. It also includes a method to update the list of scores and notify any attached `RecyclerView` of the changes.                                                                                                                                                                                                              |
| [Converters.kt](https://github.com/ErezD1/HackerUAndroidProject/blob/main/app\src\main\java\com\example\finalprojectapi\converter\Converters.kt)                        | The `Converters` class is responsible for converting between `List<String>` and `String` objects. The `fromStringList` function converts a list of strings to a single string representation, while the `toStringList` function converts a string to a list of strings by splitting it based on commas. These functions are used to handle type conversion in a database when working with `Room` persistence library.                                                                                                                                                                                                                                                                             |
| [CategoryDao.kt](https://github.com/ErezD1/HackerUAndroidProject/blob/main/app\src\main\java\com\example\finalprojectapi\dao\CategoryDao.kt)                            | This code defines a data access object (`DAO`) for managing categories in a `Room` database. It includes functions for retrieving all categories, inserting multiple categories, deleting all categories, and deleting a specific category by ID. The functions are annotated with `Room`-specific annotations for database operations.                                                                                                                                                                                                                                                                                                                                                                  |
| [QuestionDao.kt](https://github.com/ErezD1/HackerUAndroidProject/blob/main/app\src\main\java\com\example\finalprojectapi\dao\QuestionDao.kt)                            | The code is for a `QuestionDao` interface that handles the database operations related to questions. It provides functions to retrieve questions based on category and difficulty, get all questions, insert multiple questions, and delete questions based on category and difficulty.                                                                                                                                                                                                                                                                                                                                                                                                              |
| [ScoreBoardDao.kt](https://github.com/ErezD1/HackerUAndroidProject/blob/main/app\src\main\java\com\example\finalprojectapi\dao\ScoreBoardDao.kt)                        | The code defines a data access object (`DAO`) interface for accessing a local database. It includes functions for inserting a score, retrieving all scores sorted by date, and deleting all scores. The `DAO` utilizes `LiveData` to provide reactive data updates.                                                                                                                                                                                                                                                                                                                                                                                                                                      |
| [AppModule.kt](https://github.com/ErezD1/HackerUAndroidProject/blob/main/app\src\main\java\com\example\finalprojectapi\di\AppModule.kt)                                 | This code provides dependency injection for the application using `Dagger Hilt`. It provides a `Retrofit` instance for making API requests, an `ApiService` to interact with the API, and a `SharedPreferences` object for storing data persistently. All instances are marked as singletons for efficient memory management.                                                                                                                                                                                                                                                                                                                                                                              |
| [DatabaseModule.kt](https://github.com/ErezD1/HackerUAndroidProject/blob/main/app\src\main\java\com\example\finalprojectapi\di\DatabaseModule.kt)                       | This code defines a `Dagger` module for dependency injection in the Final Project API. It provides singleton instances of the `AppDatabase`, `CategoryDao`, `QuestionDao`, and `ScoreBoardDao`, allowing access to database functionality throughout the application.                                                                                                                                                                                                                                                                                                                                                                                                                                        |
| [AppDatabase.kt](https://github.com/ErezD1/HackerUAndroidProject/blob/main/app\src\main\java\com\example\finalprojectapi\entities\AppDatabase.kt)                       | This code defines an `AppDatabase` class that extends `RoomDatabase`. It includes entities, DAOs, and a method to get an instance of the database. The entities include `Category`, `Question`, and `ScoreBoard`. The DAOs include `CategoryDao`, `ScoreBoardDao`, and `QuestionDao`. The database is built using `Room` and uses a version number of 2.                                                                                                                                                                                                                                                                                                                                                             |
| [Category.kt](https://github.com/ErezD1/HackerUAndroidProject/blob/main/app\src\main\java\com\example\finalprojectapi\model\Category.kt)                                | This code defines a `Category` data model with annotations for `Room` database and `Gson` serialization. It includes an id and name field and can be used to store and retrieve category data in an app.                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 |
| [Question.kt](https://github.com/ErezD1/HackerUAndroidProject/blob/main/app\src\main\java\com\example\finalprojectapi\model\Question.kt)                                | The code defines a data model class called `Question` that represents a question from a quiz. It includes various attributes such as category, question text, type, difficulty, correct answer, and a list of incorrect answers. The class is annotated with `Room`, allowing it to be stored in a local database. It also has `Gson` annotations to parse JSON data. Additionally, `TypeConverters` are used to convert the list of incorrect answers to a JSON string before storing it in the database.                                                                                                                                                                                               |
| [ScoreBoard.kt](https://github.com/ErezD1/HackerUAndroidProject/blob/main/app\src\main\java\com\example\finalprojectapi\model\ScoreBoard.kt)                            | This code defines a model class `ScoreBoard` with `Room` annotations. It represents a scoreboard entry with attributes such as username, date, category, difficulty, and score. The class is annotated with `@Entity` to create a table named "scoreboard" in a `Room` database. The `@PrimaryKey` annotation specifies the primary key as "id".                                                                                                                                                                                                                                                                                                                                                       |
| [ApiService.kt](https://github.com/ErezD1/HackerUAndroidProject/blob/main/app\src\main\java\com\example\finalprojectapi\network\ApiService.kt)                          | The code defines an interface for making API requests related to trivia questions. It includes functions for getting categories and fetching questions with parameters like amount, category, difficulty, and type of questions. The responses are wrapped in `retrofit` `Response` objects.                                                                                                                                                                                                                                                                                                                                                                                                           |
| [TriviaRepository.kt](https://github.com/ErezD1/HackerUAndroidProject/blob/main/app\src\main\java\com\example\finalprojectapi\repository\TriviaRepository.kt)           | The `TriviaRepository` code provides the core functionalities for interacting with categories, questions, and scoreboards in the API. It handles database operations for categories, questions, and scores, syncs categories with the API, fetches questions from either the API or database, clears scores, and fetches categories from the database.                                                                                                                                                                                                                                                                                                                                               |
| [CategoryFragment.kt](https://github.com/ErezD1/HackerUAndroidProject/blob/main/app\src\main\java\com\example\finalprojectapi\ui\category\CategoryFragment.kt)          | The code defines a `Fragment` that displays categories using a `RecyclerView`. It uses a shared `ViewModel` to handle category selection and navigates to a `DifficultyFragment` when a category is clicked. The `RecyclerView` is set up with a `GridLayoutManager` and an adapter to display the categories. When the categories change, the list is updated and submitted to the adapter.                                                                                                                                                                                                                                                                                                                   |
| [DifficultyFragment.kt](https://github.com/ErezD1/HackerUAndroidProject/blob/main/app\src\main\java\com\example\finalprojectapi\ui\difficulty\DifficultyFragment.kt)    | This code represents a fragment in an Android app that handles the selection of game difficulty. It uses data binding to inflate the layout and sets click listeners for difficulty buttons. When a button is clicked, it navigates to the question fragment and passes the selected difficulty. It utilizes the shared `ViewModel` to share data between fragments. The code follows the Android best practices by utilizing the Single Activity and `ViewModel` architecture.                                                                                                                                                                                                                         |
| [QuestionFragment.kt](https://github.com/ErezD1/HackerUAndroidProject/blob/main/app\src\main\java\com\example\finalprojectapi\ui\question\QuestionFragment.kt)          | The code implements a `QuestionFragment` in an Android app. It fetches and displays questions, handles user answer submission, shows toast messages for correct or incorrect answers, updates the UI based on the current question, and handles navigation to the result fragment when the last question is answered.                                                                                                                                                                                                                                                                                                                                                                                |
| [ResultFragment.kt](https://github.com/ErezD1/HackerUAndroidProject/blob/main/app\src\main\java\com\example\finalprojectapi\ui\result\ResultFragment.kt)                | The `ResultFragment` is part of a larger Android application and is responsible for displaying the result of a quiz taken by the user. It receives data from a `ViewModel`, initializes and updates a `RecyclerView` display of scores, handles menu options, and provides functionality to start a new quiz or clear the scoreboard.                                                                                                                                                                                                                                                                                                                                                                  |
| [WelcomeFragment.kt](https://github.com/ErezD1/HackerUAndroidProject/blob/main/app\src\main\java\com\example\finalprojectapi\ui\start\WelcomeFragment.kt)               | This code defines a `WelcomeFragment` that allows users to enter their name and saves it in `SharedPreferences`. It also updates the name in a `SharedViewModel` and navigates to the `CategoryFragment`. The code includes functionalities to handle button and keyboard actions as well.                                                                                                                                                                                                                                                                                                                                                                                                                 |
| [SharedViewModel.kt](https://github.com/ErezD1/HackerUAndroidProject/blob/main/app\src\main\java\com\example\finalprojectapi\ui\viewmodels\SharedViewModel.kt)          | The code defines a `ViewModel` class that acts as a bridge between the UI and the data sources. It contains various `LiveData` objects to store the state of the app, such as categories, difficulties, selected category, selected difficulty, questions list, question index, score, and user name. The `ViewModel` also interacts with `sharedPreferences` and a repository to fetch and update data. It provides methods to handle user actions like updating the user name, selecting a category and difficulty, starting a new quiz, checking answers, saving the score, and clearing the scoreboard. Additionally, it has error message handling and ensures the score is not saved multiple times. |

</details>

---

## 🗺 Roadmap

> - [X] `ℹ️  Make the Application work and not crash`
> - [ ] `ℹ️  Maybe upload to the app store`
> - [ ] `ℹ️ ...`

---

## 📄 License

This project is licensed under the `ℹ️  INSERT-LICENSE-TYPE` License. See the [LICENSE](https://docs.github.com/en/communities/setting-up-your-project-for-healthy-contributions/adding-a-license-to-a-repository) file for additional info.

---

## 👏 Acknowledgments

> - `ℹ️  Thanks  https://opentdb.com/ for the API usage.`
> - `ℹ️  Thanks  https://icons8.com/ for the Icons used .`

---

# KAIZEN GAMING - ANDROID INTERVIEW PROJECT

## Introduction

This project is an Android application developed primarily in Kotlin, with some parts in Java. 
It is designed to fetch and display a list of sports events from an API endpoint. 
The events are grouped by sport type and displayed in a scrollable list. Each event entry shows the competitors, 
a countdown timer indicating when the event is scheduled to start, and a favorite button.

Users can filter the events per sport based on whether they are marked as favorites. 
This filter is accessible via a toggle button at the header of each sport. The countdown timer updates 
in real-time, decrementing as time passes to accurately reflect the time remaining until the event starts.

## Architecture

## Architecture

This project is being modularized and aims to implement the Clean Architecture approach. The Clean Architecture separates the software into layers, each with its own distinct responsibility, making the system easier to understand, develop, test, and become more resilient to future changes.

The project is divided into the following layers:

- `Domain`: This layer contains all the business logic, encapsulated in entities and use cases. It is independent of any specific application framework and does not depend on the layers above. In this project, the `Event` and `Sport` data classes in the `domain/model` directory represent the entities.

- `Data`: This layer serves as a data source and contains classes that implement the repositories defined in the domain layer. It is responsible for fetching data from the API endpoint.

- `Presentation`: This layer is responsible for presenting the data to the user and handling user interactions. It follows the Model-View-ViewModel (MVVM) pattern. The `EventAdapter` and `SportAdapter` classes in the `presentation/adapter` directory act as the ViewModels, fetching the data from the repositories and populating the views with this data.

The project also makes use of Android's SharedPreferences for persisting the favorite status of each event across app launches. This can be seen in the `updateFavorites` method in the `EventAdapter` class.The project also makes use of Android's SharedPreferences for persisting the favorite status of each event across app launches. This can be seen in the `updateFavorites` method in the `EventAdapter` class.

## Setup

1. Ensure you have the latest version of Android Studio installed. You can download it from [here](https://developer.android.com/studio).

2. Clone the project from GitHub. Open your terminal and run the following command:
git clone https://github.com/velosobr/KaizenGamingChallenge.git

3. Open the project. Launch Android Studio, click on `Open an existing Android Studio project` and select the project directory.

4. Sync the Gradle files. Android Studio should do this automatically, but you can also manually trigger a sync by clicking on `File -> Sync Project with Gradle Files`.

5. Run the project. Click on `Run -> Run 'app'`. Choose either a connected device or a simulator to run the app on.

Please note that you need to have the Android SDK and Android SDK Tools installed. These can be installed through the SDK Manager in Android Studio.

## Requirements : 📄
`OK` The app should fetch the list of events from an API endpoint.<br>
`OK` The app should display the list of events in a scrollable list that groups the sport events
by type, displays the competitors, the countdown timer that indicates when the event is
scheduled to start and a favorite button.<br>
`NOT WORKING` The app should allow users to filter the events per sport based on if they are added as
favorites or not. The filter should be accessible via a toggle button at the header of each
sport.<br>
`OK` The app should update the countdown timer in real-time, i.e., the timer should decrement
as time passes and should accurately reflect the time remaining until the event starts.<br>
● The app should display an appropriate message if there are no events to display or if
there is an error fetching the events from the API endpoint.<br>
● The app should allow user to be able to collapse and expand events per sport.<br>
`OK` Expected deliverable is an Android project that can be built and run both in an emulator
as well as a physical device and should work on SDK21 and above.<br>

## Nice to have 👍
`OK`  The app could store the favorite events in session or a local database.<br>
● The app could include unit tests to ensure that the filtering and countdown timer
functionality work correctly.<br>
● The app could include UI tests to ensure that the app's UI looks and behaves correctly
on different devices and screen sizes.<br>
`OK` The project could contain a README file with some insights and information about the
project structure, technologies used and anything else you think that is worth
mentioning.<br>

## Features

[x] Fetch and display a list of sports events from an API endpoint.<br>
[x] Group events by sport type and display them in a scrollable list.<br>
[x] Display each event entry with the competitors, a countdown timer indicating when the event is scheduled to start, and a favorite button.<br>
[ ] Allow users to filter the events per sport based on whether they are marked as favorites. This filter is accessible via a toggle button at the header of each sport.<br>
[x] Real-time countdown timer updates, decrementing as time passes to accurately reflect the time remaining until the event starts.<br>
[x] Persist the favorite status of each event across app launches using Android's SharedPreferences.<br>
## Future Improvements

- Implement a more robust error handling mechanism to handle network errors, timeouts, and other exceptions.
- Add a search feature to allow users to search for specific events.


## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

## Contact Information

For any queries or suggestions, you can reach out to me:

- Name: Lino Veloso
- Email: linoc.veloso@mgial.com
- GitHub: [velosobr](https://github.com/velosobr)
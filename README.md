# SuperHeroes And Villains
SuperHeroes And Villains is an android application that allows collect superheroes and villains from the the the application, and battle them with other characters. It fetches data from the `SuperHero API.`

## Tech Stack Used 
Java ([View](https://www.oracle.com/java/technologies/javase-downloads.html))

### Prerequisites
Be sure to run `git clone https://github.com/rutherfn/Superheroes-And-Villains.git`, on the given repo. From there, you will be able to check out the master branch to get the up to date version of features!

## Getting Started / Running The Project 

This project uses the latest Gradle version. Gradle will update if a new version gets released.  

To run this app, you will need to download [Android Studio](https://developer.android.com/studio). 

To run the project open the folder `code`

You will need to create a new emulator otherwise can use your own android phone [Use Your Android Phone](https://javatutorial.net/connect-android-device-android-studio). 

From there the gradle will build the project and after completed, hit the `run` green button on top of the screen. 

The app will build and run starting from the splash screen. 

## Core Project 

`code` contains folder stucture and classes to get you started. Classes are defined into folders, to keep the code organized and concise.

# Classes

Down below are all of the classes defined in `code` and what they are used for. 

## Activitys

An activity provides the window in which the app draws its UI. The window typically fills the screen, but may be smaller then the screen and float on top of other windows. 

Used to control different `windows/screens` of a mobile applications, but can also be used to load in other screens inside of a activity. 

- `SplashActivity`: Responsible for loading in a splash screen towards the app from stock image.

- `MainActivity`: Loads in a `ViewPager Adapter`, to handle all the sub views or Fragments state. Also responsible for handling the Bottom Navigation View functionality.

- `OnBoardingActivity`: Handles functionality of onboarding the user, by taking in array list of any size. Array list comes with a title, description fields, and a image that uses the `OnBoard` adapter. 

- `WebView`: Responsible for handling a browser response to the user, if they want to view the `Super Hero API`. 

## Adapters 

Adapters are used to load in layout for fragments, as well as loads in data from UI. 

Every class has a primary sub class of `ViewHolder`. Which handles the functionality of current layout that gets `init`

`ViewHolders` handles all of the UI functionality for recycler view adapter classes. 

Here in this project there are `Adapters` that handles loading recycler view holders into the desired Fragments. 

- `All Adapters`:Are responsible for loading there associated `ViewHolder` classes to control functionality of view, and using override methods `onCreateViewHolder, onBindViewHolder, getItemCount`.

- `ViewPagerAdapter`: Handles loading in all the Fragments inside a adapter use to handle state. 

## Fragments

A `Fragment` represents a behavior of a portion of user interface in a `FragmentActivity`. You can combine multiple fragments in a single activity to build a multi-pane UI and reuse a fragment in multiple activitys.

A fragment can really be summed up as a modular section of an activity, which has its own life cycles. 

Fragments declared in this project are the `Battle, Home, PrivatePolicy, PurchaseCharacters, Searc, Stats, TacticalVs, Vs` and `YourCharacter` Fragments. 

## Helpers 

A `Helper` class is any override class used in a parent class, to ease functionality of a current class. 

It creates less code for overall functionality of your parent class, and used mainly for passing paramters from `parent` class, and using it in `sub` class. 

- `NonSwipePagerableViewPager`: Is a helper class that extends View Pager, and gets used in replaced of ViewPager to not allow swiping. 

## Models

All model `classes` are used for Model types to get a return type from the Open Weather API. 

## Network

`Network` represents all of the ovveride methods used for return of SuperHero API calls. 

- `GetDataService`: Declares all of the requests making, and what will the return data type be for said requests. 

- `RetroFitInstance`: Responsible for declaring a base url for API data type call. 
 
## Libraries Being Used 

- Materials ([View](https://github.com/material-components/material-components-android))
- RecyclerView ([View](https://developer.android.com/reference/android/support/v7/widget/RecyclerView))
- Picasso ([View](https://github.com/square/picasso))
- RetroFit ([View](https://square.github.io/retrofit/))
- Gson ([View](https://github.com/google/gson/))
- CircleImageView ([View](https://github.com/hdodenhof/CircleImageView/))

## APIS Used

- Open Weather API ([View](https://superheroapi.com/))

## Live Google Play Store Link 

- Google Play Store Page ([View](https://play.google.com/store/apps/details?id=rutheford.com.superheroesandvillainscentral/))

## Project Status

:white_check_mark: Completed
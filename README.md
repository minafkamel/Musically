# Musically

| Author |  |
|--|--|
| Mina Kamel | Android Engineer  |

## Table of Contents
- [Musically](#musically)
	- [Description](#description)
	- [Architecture](#architecture)
		- [The Data Layer](#the-data-layer)
		- [The Domain Layer](#the-domain-layer)
		- [The UI Layer](#the-ui-layer)
		- [Mappers and Objects](#mappers-and-objects)
		- [Utils and Extensions](#utils-and-extensions)
		- [Unit Tests](#unit-tests)
		- [Dependencies](#dependencies)

## Description

**Musically** is a simple app that displays a list of the top 5 artists from [HeartThisAt API](https://hearthis.at/api-v2/). When an artist item is clicked, the app shows their top 5 songs. When a song is selected, the app streams the song. The song continues to play even if the user goes to the artists list.
The flow:

<p align="center">
<img src="https://github.com/minafkamel/Musically/blob/master/media/musically_flow.gif" alt="musically flow" width="400" height="700">

Click [here](https://drive.google.com/file/d/1qWVyOjIacvfmT98_qUdbwJGvI7bVisiL/view?usp=sharing) for a video demo to hear a selected song.


## Architecture

The app uses the **Android Architecture Components** to deliver its functionality. Each Activity or Fragment has a View Model to control it. A set of Live Data objects act as a bridge from the View Model to the Activity or Fragment.

The architecture is highly reactive. It uses **RxJava** for domain and data layer and **LiveData** for the UI Layer. **Koin** is used for dependency injection due to its minimalistic set up.

### The Data Layer
The Data Layer contains raw network objects (`PopularRaw`, `SingleArtistRaw`, `SongRaw`),  `FeedRepository`, `SelectionRepository` `FeedApi`. The `FeedRepository` calls the `FeedApi` to retrieve data from the API.

- The `FeedRepository` has 3 methods:

| Method | Returns | Uri | Purpose
|--|--| -- | --|
| `getPopular()` | List of `PopularRaw` | `feed/?type=popular&page=1&count=5` | Gets popular 5 artists
| `getSingleArtist()` | `SingleArtistRaw` | `{permalink}` | Gets data of a single artist. We are interested in the `trackCount` and the `description`.
| `getSongs` | List of `SongRaw` | `{permalink}/?type=tracks&page=1&count=5` | Gets the top 5 songs of an artist

The suffix `-Raw` in the objects is to indicated that this is an *unprocessed* data object as opposed to a Domain Layer or a UI Layer object

For now, the `FeedRepository` looks like a proxy class to `FeedApi`. However, the `FeedRepository` can possibly handle other sources of data like cached or database.

-  The`SelectionRepository` has one object and a method:

|Item  | Description |
|--|--|
| `selectionPublisher` | A `PublishSubject` for anyone interested in listening to the song selection done in the `SongsFragment`/`SongsViewModel`. This is used by the `StreamingFragment`/`StreamingViewModel` to start streaming a song. <br /> Passing the data vertically via the domain and data layer as opposed to horizontally between activities and fragments is far better as it reduces the clutter of passing parameters around and keeps the data flow from data to domain to UI consistent.  |
  | `setSongSelection` | Sets the title and stream url for a selected song and provides the observer with a new item. Used by `StreamingFragment`/`StreamingViewModel` via `SelectSong` use case.  |



### The Domain Layer
The Domain Layer contains a group of use cases that do pieces of business logic. They are consumed by View Models in the UI Layer. There are two types of use cases:<br/>
	- `UseCase` : Emits one value therefore it's of a `Single` return type.<br/>
	- `ObservableUseCase`: Emits a series of values therefore it's of an `Observable` return type.

Input and output: Both use cases are generic in nature. This means they can receive custom types for `Input` and return a custom type for `Output`. In case no input is expected, `NoParams` is used. And in case no output is expected, `NoResult` is used. `Params` is used to wrap input parameters.

| Use Case | Description | Type | Input | Output |
|--|--|--|--|--|
| `GetArtists` | Calls `getPopular()` and `getSingleArtist()` APIs via `FeedRepository` and combines the result then returns a list of `Artist`  |`UseCase`| `NoParams`| List of `Artist` |
| `GetSongs` | Retrieves the songs of a `permalink` via `FeedRepository`|`UseCase`| `Params(permalink)`| List of `SongRaw` |
`SelectSong`  | Sets the song selection in the `SelectionRepository` |`UseCase`| `Params(title, streamUrl)`| `NoResult` |
| `GetStreamingInfo` | Observes the `selectionPublisher` in `SelectionRepository` and returns the emitted title and stream url pair|`ObservableUseCase`| `NoParams`| `Pair(title, streamUrl)` |

### The UI Layer

A View Model is a `LifecycleObserver` while an Activity or a Fragment is a `LifecycleOwner`. This helps the View Model to know when either of them is created to start the flow, without the need of each child of the `BaseActivity` or `BaseFragment` to do so.

Both base classes have a similar structure. The layout is provided via the constructor and they have two abstract methods.

- `passViewModel()`: for each child to pass its injected view model. This is used to make the view model a `LifecycleObserver`.
- `observeLiveData`: A unified place for all LiveData observation.

`BaseViewModel` : Upon the creation of the Activity or Fragment, calls `onViewCreate()` which is implemented by the child activities or fragments to start binding to use cases or any other action needed. When cleared, it makes sure to clears the `CompositeDisposable`.

Activities and Fragments:
|  | Activity/Fragment | ViewModel | Layout |
|--|--|--|--|
| Main <br/>`MainActivity` <br/>`MainViewModel`<br/>`a_main`| Loads the three fragments: `ArtistsFragment`, `SongsFragment` and `StreamingFragment`.| N/A | Contains two `FrameLayout`s, one loads `ArtistsFragment` and `SongsFragment` and the bottom one loads `StreamingFragment`
| Artists <br/>`ArtistsFragment`<br/>`ArtistsViewModel`<br/>`f_recyclerview`<br/>| Observes `artistsLiveData` and `progressLiveData` from `ArtistsViewModel`. Loads the artists in the `ArtistsAdapter` and show progress while waiting for artists. </br> Also provides a click handler to the adapter and passes the clicked `artistId` to `MainActivity`| When the view is created, calls `GetArtists` use case, uses `ArtistsMapper` to map the list of domain models `Artist` to a list of `ArtistViewEntity`. | A recycler view and a custom progress load view that shows loading while waiting for artists.
| Songs <br/>`SongsFragment`<br/>`SongsViewModel`<br/>`f_recyclerview`<br/> | Observes `songsLiveData` and `progressLiveData` from `SongsViewModel`. Loads the list of `SongViewEntity` in the `SongsAdapter` and shows progress while waiting for songs. <br/> Also provides a click handler to the adapter and passes the clicked song `Pair(title, streamUrl)` | Calls `GetSongs` use case then `SongsMapper` to map when the view is created. <br/> Calls `SelectSong` when a user clicks on the a song to notify the `StreamingFragment`. |A recycler view and a custom progress load view that shows loading while waiting for songs.
| Streaming <br/>`StreamingFragment`<br/>`StreamingViewModel`<br/>`f_streaming`<br/> | Observes `streamingLiveData` to stream the selected song using `MediaPlayer` and sets a song title.  It also uses `Glide` to do play a gif while the song is playing.| Calls `getStreamingInfo` when the view is created to listen to any song clicks.  | A simple layout with an `ImageView` for the gif and a `TextView` for the streamed song title.

### Mappers and objects
Each layer has a set of objects suited to its needs. The Domain Layer does the mapping in the use cases while the UI Layer uses mappers.
| Data | Domain | UI
|--|--|--|
| No mapping. Objects have a `-Raw` suffix to indicate they are unprocessed (e.g: `SongRaw`) | Mapped in use cases. Have no suffix (e.g: `Artist`). Optional as sometimes there's no need. | Uses mappers to create models more suited to the UI displayed like string formatting. These entities have a `-ViewEntitiy` suffix to indicate they're view related.|

### Utils & extensions
- `StringProvider`: A utility class that provides strings values given a key.
- `Rx`: `subscribeToDisposeLater`, `withDefaultSchedulers`  and `addTo` to avoid boilerplate code while using RxJava.

### Unit Tests
- All of the testable code (Use Cases, View Models and mappers) are unit tested.
	- The `whenever` statements are organised according to the `ArrangeBuilder` pattern. I've written about it [here](https://proandroiddev.com/tips-for-neater-android-development-part-1-34d3250b7943). This helps reusing statements and also turns them into english statements.
	- Tests are organised into Arrange, Act, and Assert(verify) blocks with a line break in between.

### Dependencies
All dependencies are organised in the `Dependencies.kt` files.
- Most important ones:
	- RxJava
	- Retrofit
	- Koin
	- Glide

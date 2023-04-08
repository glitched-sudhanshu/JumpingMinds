package com.example.tmm.ui.viewmodels

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tmm.domain.use_cases.*
import com.example.tmm.ui.viewmodels.ListState.*
import com.example.tmm.utils.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MarvelListViewModel @Inject constructor(
    private val charactersUseCase: CharactersUseCase,
    private val searchCharacterUseCase: SearchCharacterUseCase,
    private val creatorsUseCase: CreatorUseCase,
    private val searchCreatorUseCase: SearchCreatorUseCase,
    private val comicUseCase: ComicUseCase,
    private val searchComicUseCase: SearchComicUseCase,
    private val eventUseCase: EventUseCase,
    private val searchEventUseCase: SearchEventUseCase,
    private val seriesUseCase: SeriesUseCase,
    private val searchSeriesUseCase: SearchSeriesUseCase,
    ) : ViewModel() {
    private val _marvelCharactersValue = MutableStateFlow(CharacterListState())
    var marvelCharactersValue: StateFlow<CharacterListState> = _marvelCharactersValue

    private val _marvelCreatorsValue = MutableStateFlow(CreatorsListState())
    var marvelCreatorsValue: StateFlow<CreatorsListState> = _marvelCreatorsValue

    private val _marvelComicValue = MutableStateFlow(ComicListState())
    var marvelComicValue: StateFlow<ComicListState> = _marvelComicValue

    private val _marvelEventValue = MutableStateFlow(EventListState())
    var marvelEventValue: StateFlow<EventListState> = _marvelEventValue

    private val _marvelSeriesValue = MutableStateFlow(SeriesListState())
    var marvelSeriesValue: StateFlow<SeriesListState> = _marvelSeriesValue

    fun getAllCharactersData(offset: Int) = viewModelScope.launch(Dispatchers.IO) {
        charactersUseCase(offset = offset).collect {
            when (it) {
                is Response.Success -> {
                    _marvelCharactersValue.value =
                        CharacterListState(characterList = it.data ?: emptyList())
                }
                is Response.Loading -> {
                    _marvelCharactersValue.value = CharacterListState(isLoading = true)
                }
                is Response.Error -> {
                    _marvelCharactersValue.value =
                        CharacterListState(error = it.message ?: "An unexpected error")
                }
            }
        }
    }

    fun getAllSearchedCharacters(search: String) = viewModelScope.launch(Dispatchers.IO) {
        searchCharacterUseCase.invoke(search = search).collect {
            when (it) {
                is Response.Success -> {
                    _marvelCharactersValue.value =
                        CharacterListState(characterList = it.data ?: emptyList())
                }
                is Response.Loading -> {
                    _marvelCharactersValue.value = CharacterListState(isLoading = true)
                }
                is Response.Error -> {
                    _marvelCharactersValue.value =
                        CharacterListState(error = it.message ?: "An unexpected error")
                }
            }
        }
    }

    fun getAllCreatorsData(offset: Int) = viewModelScope.launch(Dispatchers.IO) {
        creatorsUseCase(offset = offset).collect {
            when (it) {
                is Response.Success -> {
                    _marvelCreatorsValue.value =
                        CreatorsListState(creatorList = it.data ?: emptyList())
                }
                is Response.Loading -> {
                    _marvelCreatorsValue.value = CreatorsListState(isLoading = true)
                }
                is Response.Error -> {
                    _marvelCreatorsValue.value =
                        CreatorsListState(error = it.message ?: "An unexpected error")
                }
            }
        }
    }

    fun getAllSearchedCreatorsData(search: String) = viewModelScope.launch(Dispatchers.IO) {
        searchCreatorUseCase.invoke(search = search).collect {
            when (it) {
                is Response.Success -> {
                    _marvelCreatorsValue.value =
                        CreatorsListState(creatorList = it.data ?: emptyList())
                }
                is Response.Loading -> {
                    _marvelCreatorsValue.value = CreatorsListState(isLoading = true)
                }
                is Response.Error -> {
                    _marvelCreatorsValue.value =
                        CreatorsListState(error = it.message ?: "An unexpected error")
                }
            }
        }
    }

    fun getAllComicsData(offset: Int) = viewModelScope.launch(Dispatchers.IO) {
        comicUseCase(offset = offset).collect {
            when (it) {
                is Response.Success -> {
                    _marvelComicValue.value = ComicListState(comicList = it.data ?: emptyList())
                }
                is Response.Loading -> {
                    _marvelComicValue.value = ComicListState(isLoading = true)
                }
                is Response.Error -> {
                    _marvelComicValue.value =
                        ComicListState(error = it.message ?: "An unexpected error")
                }
            }
        }
    }

    fun getAllSearchedComics(search: String) = viewModelScope.launch(Dispatchers.IO) {
        searchComicUseCase.invoke(search = search).collect {
            when (it) {
                is Response.Success -> {
                    _marvelComicValue.value = ComicListState(comicList = it.data ?: emptyList())
                }
                is Response.Loading -> {
                    _marvelComicValue.value = ComicListState(isLoading = true)
                }
                is Response.Error -> {
                    _marvelComicValue.value =
                        ComicListState(error = it.message ?: "An unexpected error")
                }
            }
        }
    }

    fun getAllEventsData(offset: Int) = viewModelScope.launch(Dispatchers.IO) {
        eventUseCase(offset = offset).collect {
            when (it) {
                is Response.Success -> {
                    _marvelEventValue.value = EventListState(eventList = it.data ?: emptyList())
                }
                is Response.Loading -> {
                    _marvelEventValue.value = EventListState(isLoading = true)
                }
                is Response.Error -> {
                    _marvelEventValue.value =
                        EventListState(error = it.message ?: "An unexpected error")
                }
            }
        }
    }

    fun getAllSearchedEvents(search: String) = viewModelScope.launch(Dispatchers.IO) {
        searchEventUseCase.invoke(search = search).collect {
            when (it) {
                is Response.Success -> {
                    _marvelEventValue.value = EventListState(eventList = it.data ?: emptyList())
                }
                is Response.Loading -> {
                    _marvelEventValue.value = EventListState(isLoading = true)
                }
                is Response.Error -> {
                    _marvelEventValue.value =
                        EventListState(error = it.message ?: "An unexpected error")
                }
            }
        }
    }

    fun getAllSeriesData(offset: Int) = viewModelScope.launch(Dispatchers.IO) {
        seriesUseCase(offset = offset).collect {
            when (it) {
                is Response.Success -> {
                    _marvelSeriesValue.value = SeriesListState(seriesList = it.data ?: emptyList())
                    for (item in it.data!!) {
                        Log.d(TAG,
                            "getAllData: ${item.title}->${item.noOfCharacters} & ${item.description}")
                    }
                }
                is Response.Loading -> {
                    _marvelSeriesValue.value = SeriesListState(isLoading = true)
                }
                is Response.Error -> {
                    _marvelSeriesValue.value =
                        SeriesListState(error = it.message ?: "An unexpected error")
                }
            }
        }
    }


    fun getAllSearchedSeries(search: String) = viewModelScope.launch(Dispatchers.IO) {
        searchSeriesUseCase.invoke(search = search).collect {
            when (it) {
                is Response.Success -> {
                    _marvelSeriesValue.value = SeriesListState(seriesList = it.data ?: emptyList())
                    for (item in it.data!!) {
                        Log.d(TAG,
                            "getAllData: ${item.title}->${item.noOfCharacters} & ${item.description}")
                    }
                }
                is Response.Loading -> {
                    _marvelSeriesValue.value = SeriesListState(isLoading = true)
                }
                is Response.Error -> {
                    _marvelSeriesValue.value =
                        SeriesListState(error = it.message ?: "An unexpected error")
                }
            }
        }
    }
}
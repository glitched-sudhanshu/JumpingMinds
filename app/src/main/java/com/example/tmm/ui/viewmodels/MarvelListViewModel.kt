package com.example.tmm.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tmm.domain.use_cases.CharactersUseCase
import com.example.tmm.domain.use_cases.CreatorUseCase
import com.example.tmm.ui.viewmodels.CharactersList.CharacterListState
import com.example.tmm.ui.viewmodels.CreatorsList.CreatorsListState
import com.example.tmm.utils.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MarvelListViewModel @Inject constructor(
    private val charactersUseCase: CharactersUseCase,
    private val creatorsUseCase: CreatorUseCase
) : ViewModel(){
    private val _marvelCharactersValue = MutableStateFlow(CharacterListState())
    var marvelCharactersValue : StateFlow<CharacterListState> = _marvelCharactersValue

    private val _marvelCreatorsValue = MutableStateFlow(CreatorsListState())
    var marvelCreatorsValue : StateFlow<CreatorsListState> = _marvelCreatorsValue

    fun getAllCharactersData(offset: Int) = viewModelScope.launch {
        charactersUseCase(offset = offset).collect {
            when (it) {
                is Response.Success -> {
                    _marvelCharactersValue.value = CharacterListState(characterList = it.data?: emptyList())
                }
                is Response.Loading -> {
                    _marvelCharactersValue.value = CharacterListState(isLoading = true)
                }
                is Response.Error -> {
                    _marvelCharactersValue.value = CharacterListState(error = it.message?:"An unexpected error")
                }
            }
        }
    }

    fun getAllCreatorsData(offset : Int) = viewModelScope.launch(Dispatchers.IO) {
        creatorsUseCase(offset = offset).collect{
            when(it){
                is Response.Success -> {
                    _marvelCreatorsValue.value = CreatorsListState(creatorList = it.data?: emptyList())
                }
                is Response.Loading -> {
                    _marvelCreatorsValue.value = CreatorsListState(isLoading = true)
                }
                is Response.Error -> {
                    _marvelCreatorsValue.value = CreatorsListState(error = it.message?:"An unexpected error")
                }
            }
        }
    }
}
package com.example.tmm.ui.viewmodels.ListState

import com.example.tmm.domain.model.Character

data class CharacterListState(
    val isLoading: Boolean = false,
    val characterList: List<Character> = emptyList(),
    val error: String = "",
)
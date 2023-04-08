package com.example.tmm.ui.viewmodels.ListState

import com.example.tmm.domain.model.Character
import com.example.tmm.domain.model.MarvelComic

class ComicListState(
    val isLoading: Boolean = false,
    val list: List<MarvelComic> = emptyList(),
    val error: String = "",
)
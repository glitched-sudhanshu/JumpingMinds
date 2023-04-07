package com.example.tmm.ui.viewmodels.ListState

import com.example.tmm.domain.model.Character
import com.example.tmm.domain.model.MarvelComic

class ComicListState(
    val isLoading: Boolean = false,
    val comicList: List<MarvelComic> = emptyList(),
    val error: String = "",
)
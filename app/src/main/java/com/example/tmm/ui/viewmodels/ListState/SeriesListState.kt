package com.example.tmm.ui.viewmodels.ListState

import com.example.tmm.domain.model.Character
import com.example.tmm.domain.model.MarvelSeries

data class SeriesListState(
    val isLoading: Boolean = false,
    val list: List<MarvelSeries> = emptyList(),
    val error: String = "",
)
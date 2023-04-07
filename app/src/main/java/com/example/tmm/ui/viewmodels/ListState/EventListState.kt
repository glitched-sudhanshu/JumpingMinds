package com.example.tmm.ui.viewmodels.ListState

import com.example.tmm.domain.model.Character
import com.example.tmm.domain.model.MarvelEvent

class EventListState(
    val isLoading: Boolean = false,
    val eventList: List<MarvelEvent> = emptyList(),
    val error: String = "",
)
package com.example.tmm.ui.viewmodels.ListState

import com.example.tmm.domain.model.Creator

data class CreatorsListState(
    val isLoading: Boolean = false,
    val list: List<Creator> = emptyList(),
    val error: String = "",
)
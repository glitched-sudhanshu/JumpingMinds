package com.example.tmm.data.data_source.dto

import com.example.tmm.domain.model.MarvelEvent
import com.example.tmm.domain.model.MarvelSeries

data class ResultXXXX(
    val characters: CharactersXX,
    val comics: ComicsXXX,
    val creators: CreatorsXX,
    val description: String,
    val end: String,
    val id: Int,
    val modified: String,
    val next: Next,
    val previous: Previous,
    val resourceURI: String,
    val series: SeriesXXX,
    val start: String,
    val stories: StoriesXXXX,
    val thumbnail: ThumbnailXXXX,
    val title: String,
    val urls: List<UrlXXXX>
){
    fun toMarvelEvent(): MarvelEvent {
        return MarvelEvent(
            id = id,
            title = title,
            description = description,
            thumbnail = thumbnail.path,
            thumbnailExt = thumbnail.extension
        )
    }
}
package com.example.tmm.data.data_source.dto

import com.example.tmm.domain.model.MarvelSeries

data class ResultXXX(
    val characters: CharactersX,
    val comics: ComicsXX,
    val creators: CreatorsX,
    val description: String,
    val endYear: Int,
    val events: EventsXXX,
    val id: Int,
    val modified: String,
    val next: Any,
    val previous: Any,
    val rating: String,
    val resourceURI: String,
    val startYear: Int,
    val stories: StoriesXXX,
    val thumbnail: ThumbnailXXX,
    val title: String,
    val type: String,
    val urls: List<UrlXXX>,
) {
    fun toMarvelSeries(): MarvelSeries {
        var d = description
        if(d.isNullOrEmpty()) d = "Description Not Found!!"
        return MarvelSeries(
            id = id,
            title = title,
            description = d,
            noOfCharacters = characters.items.size,
            noOfComics = comics.items.size,
            thumbnail = thumbnail.path,
            thumbnailExt = thumbnail.extension
        )
    }
}
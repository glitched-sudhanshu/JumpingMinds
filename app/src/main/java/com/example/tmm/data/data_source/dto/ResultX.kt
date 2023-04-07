package com.example.tmm.data.data_source.dto

import com.example.tmm.domain.model.Creator

data class ResultX(
    val comics: ComicsX,
    val events: EventsX,
    val firstName: String,
    val fullName: String,
    val id: Int,
    val lastName: String,
    val middleName: String,
    val modified: String,
    val resourceURI: String,
    val series: SeriesX,
    val stories: StoriesX,
    val suffix: String,
    val thumbnail: ThumbnailX,
    val urls: List<UrlX>
){
    fun toCreator(): Creator {
        return Creator(
            id = id,
            firstName = firstName,
            middleName = middleName,
            lastName = lastName,
            thumbnail = thumbnail.path,
            thumbnailExt =  thumbnail.extension,
            comics =  comics.items.map{
                it.name
            },
            series = series.items.map {
                it.name
            }
        )
    }
}
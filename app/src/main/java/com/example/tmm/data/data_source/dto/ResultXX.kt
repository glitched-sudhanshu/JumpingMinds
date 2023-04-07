package com.example.tmm.data.data_source.dto

import com.example.tmm.domain.model.MarvelComic

data class ResultXX(
    val characters: Characters,
    val collectedIssues: List<Any>,
    val collections: List<Any>,
    val creators: Creators,
    val dates: List<Date>,
    val description: String?,
    val diamondCode: String,
    val digitalId: Int,
    val ean: String,
    val events: EventsXX,
    val format: String,
    val id: Int,
    val images: List<Any>,
    val isbn: String,
    val issn: String,
    val issueNumber: Int,
    val modified: String,
    val pageCount: Int,
    val prices: List<Price>,
    val resourceURI: String,
    val series: SeriesXX,
    val stories: StoriesXX,
    val textObjects: List<Any>,
    val thumbnail: ThumbnailXX,
    val title: String,
    val upc: String,
    val urls: List<UrlXX>,
    val variantDescription: String,
    val variants: List<Variant>
){
    fun toComic(): MarvelComic {
        var p = prices[0].getPrice()
        if(p==0)p=10
        var d = description
        if(d.isNullOrEmpty()) d = "Description Not Found!!"
        return MarvelComic(
            id = id,
            title = title,
            price = p,
            description = d,
            thumbnail = thumbnail.path,
            thumbnailExt = thumbnail.extension
        )
    }
}
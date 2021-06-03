package com.ajayasija.appentus.model

import com.squareup.moshi.Json

data class ApiResponseItem(
    @Json(name = "id")
    val id:String,
    @Json(name = "download_url")
    val download_url:String
)
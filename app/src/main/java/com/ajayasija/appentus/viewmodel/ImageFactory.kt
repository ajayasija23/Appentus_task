package com.ajayasija.appentus.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ajayasija.appentus.web.ApiService

class ImageFactory(private val api:ApiService):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ImageViewModel (api) as T
    }
}
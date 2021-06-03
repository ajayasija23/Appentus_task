package com.ajayasija.appentus.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.ajayasija.appentus.model.ApiResponseItem
import com.ajayasija.appentus.paging.PagingDataSource
import com.ajayasija.appentus.web.ApiService
import kotlinx.coroutines.flow.Flow

class ImageViewModel(private val api:ApiService):ViewModel() {

    val data = Pager(PagingConfig(pageSize = 10)) {
        PagingDataSource(api)
    }.flow.cachedIn(viewModelScope)
}
package com.ajayasija.appentus.paging

import androidx.paging.PagingState
import com.ajayasija.appentus.model.ApiResponseItem
import com.ajayasija.appentus.web.ApiService
import androidx.paging.PagingSource as PagingSource

class PagingDataSource(private val api:ApiService): PagingSource<Int,ApiResponseItem>() {
    override fun getRefreshKey(state: PagingState<Int, ApiResponseItem>): Int? {
        return 1
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ApiResponseItem> {
        // retern fetched Page
        return try {
            val nextPage=params.key?:1
            val respone=api.fetchImages(nextPage,10)
            LoadResult.Page(
                data = respone,
                prevKey = null,//only forward page numbers
                nextKey = nextPage+1
            )
        }catch (e:Exception){
            LoadResult.Error(e)
        }
    }
}
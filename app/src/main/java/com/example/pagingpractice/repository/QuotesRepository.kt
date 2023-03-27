package com.example.pagingpractice.repository

import android.graphics.pdf.PdfDocument.Page
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.example.pagingpractice.api.ApiService
import com.example.pagingpractice.paging.QuotePagingSource
import javax.inject.Inject

class QuotesRepository @Inject constructor(private val apiService: ApiService) {
    fun getQuotes() = Pager(
        config = PagingConfig(pageSize = 20, maxSize = 100),
        pagingSourceFactory = {
            QuotePagingSource(apiService)
        }
    ).liveData
}
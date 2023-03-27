package com.example.pagingpractice.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.pagingpractice.models.Results
import com.example.pagingpractice.repository.QuotesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class QuotesViewModel @Inject constructor(private val repository: QuotesRepository) : ViewModel(){

   val list = repository.getQuotes().cachedIn(viewModelScope)

    /* var list = MutableLiveData<PagingData<Results>>()
  init {

//      list.value = repository.getQuotes().cachedIn(viewModelScope).value
  }
*/
}
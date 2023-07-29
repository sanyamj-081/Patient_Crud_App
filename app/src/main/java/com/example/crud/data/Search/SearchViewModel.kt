package com.example.crud.data.Search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class SearchViewModel : ViewModel() {

    val searchResultLiveData: MutableLiveData<SearchViewModel?> = MutableLiveData()

    private val _searchUiState = MutableStateFlow(SearchUIState())
    val searchUiEvent = _searchUiState.asStateFlow()




}
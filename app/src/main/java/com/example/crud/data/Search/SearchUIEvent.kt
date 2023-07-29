package com.example.crud.data.Search

sealed class SearchUIEvent {
    data class PidChanged(val pidChanged: String): SearchUIEvent()

    object SearchButtonClicked: SearchUIEvent()

}
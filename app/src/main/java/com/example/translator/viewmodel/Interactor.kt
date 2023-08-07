package com.example.translator.viewmodel

import com.example.translator.model.data.AppState
import io.reactivex.rxjava3.core.Observable


interface Interactor<T> {
    fun getData(word: String, fromRemoteSource: Boolean): Observable<AppState>

}
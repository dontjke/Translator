package com.example.translator.model.repository

import com.example.translator.model.data.DataModel
import io.reactivex.rxjava3.core.Observable


interface Repository<T> {
    fun getData(word: String): Observable<List<DataModel>>

}
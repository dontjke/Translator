package com.example.translator.model.datasource

import com.example.translator.model.data.DataModel
import io.reactivex.rxjava3.core.Observable


interface DataSource<T> {
    fun getData(word: String): Observable<List<DataModel>>

}
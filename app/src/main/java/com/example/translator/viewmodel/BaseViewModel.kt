package com.example.translator.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.translator.model.data.AppState
import com.example.translator.rx.SchedulerProvider
import com.example.translator.rx.SchedulerProviderImpl

import io.reactivex.rxjava3.disposables.CompositeDisposable


abstract class BaseViewModel<T : AppState> (

    protected val liveDataForViewToObserve: MutableLiveData<T> =
        MutableLiveData(),
    protected val compositeDisposable: CompositeDisposable =
        CompositeDisposable(),
    protected val schedulerProvider: SchedulerProvider = SchedulerProviderImpl()
) : ViewModel() {
    abstract fun getData(word: String, isOnline: Boolean)
    override fun onCleared() {
        compositeDisposable.clear()
    }
}

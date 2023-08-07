package com.example.translator.viewmodel

import androidx.lifecycle.LiveData
import com.example.translator.model.data.AppState
import com.example.translator.model.data.DataModel
import com.example.translator.model.data.Meanings
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(private val interactor: MainInteractor) :
    BaseViewModel<AppState>() {

    private val liveDataForViewToObserve: LiveData<AppState> = mutableLiveData
    fun subscribe(): LiveData<AppState> {
        return liveDataForViewToObserve
    }

    override fun getData(word: String, isOnline: Boolean) {
        mutableLiveData.value = AppState.Loading(null)
        cancelJob()
        viewModelCoroutineScope.launch { startInteractor(word, isOnline) }
    }

    private suspend fun startInteractor(word: String, isOnline: Boolean) =
        withContext(Dispatchers.IO) {
            mutableLiveData.postValue(
                parseSearchResults(
                    interactor.getData(
                        word,
                        isOnline
                    )
                )
            )
        }

    override fun handleError(error: Throwable) {
        mutableLiveData.postValue(AppState.Error(error))
    }

    override fun onCleared() {
        mutableLiveData.value = AppState.Success(null)
        super.onCleared()
    }

    private fun parseSearchResults(data: AppState): AppState {
        val newSearchResults = arrayListOf<DataModel>()
        when (data) {
            is AppState.Success -> {
                val searchResults = data.data
                if (!searchResults.isNullOrEmpty()) {
                    for (searchResult in searchResults) {
                        parseResult(searchResult, newSearchResults)
                    }
                }
            }

            else -> println("error")
        }

        return AppState.Success(newSearchResults)
    }

    private fun parseResult(dataModel: DataModel, newDataModels: ArrayList<DataModel>) {
        if (!dataModel.text.isNullOrBlank() && !dataModel.meanings.isNullOrEmpty()) {
            val newMeanings = arrayListOf<Meanings>()
            for (meaning in dataModel.meanings) {
                if (meaning.translation != null && !meaning.translation.translation.isNullOrBlank()) {
                    newMeanings.add(Meanings(meaning.translation, meaning.imageUrl))
                }
            }
            if (newMeanings.isNotEmpty()) {
                newDataModels.add(DataModel(dataModel.text, newMeanings))
            }
        }
    }
}





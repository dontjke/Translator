package com.example.translator.viewmodel

import com.example.translator.model.data.AppState
import com.example.translator.model.data.DataModel
import com.example.translator.model.repository.Repository


class MainInteractor constructor(
    private val remoteRepository: Repository<List<DataModel>>,
    private val localRepository: Repository<List<DataModel>>
) : Interactor<AppState> {

    override suspend fun getData(word: String, fromRemoteSource: Boolean): AppState {
        return AppState.Success(
            if (fromRemoteSource) {
                remoteRepository
            } else {
                localRepository
            }.getData(word)
        )
    }
}


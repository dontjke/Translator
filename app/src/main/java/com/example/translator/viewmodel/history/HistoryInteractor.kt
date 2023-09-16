package com.example.translator.viewmodel.history

import com.example.model.data.DataModel
import com.example.repository.Repository
import com.example.repository.RepositoryLocal
import com.example.core.viewmodel.Interactor


class HistoryInteractor(
    private val repositoryRemote: com.example.repository.Repository<List<DataModel>>,
    private val repositoryLocal: com.example.repository.RepositoryLocal<List<DataModel>>
) : Interactor<com.example.model.AppState> {
    override suspend fun getData(word: String, fromRemoteSource: Boolean): com.example.model.AppState {
        return com.example.model.AppState.Success(
            if (fromRemoteSource) {
                repositoryRemote
            } else {
                repositoryLocal
            }.getData(word)
        )
    }
}
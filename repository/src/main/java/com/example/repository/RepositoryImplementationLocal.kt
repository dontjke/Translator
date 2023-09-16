package com.example.repository

import com.example.model.AppState
import com.example.model.data.DataModel
import com.example.repository.datasource.DataSourceLocal


class RepositoryImplementationLocal(private val dataSource: DataSourceLocal<List<DataModel>>) :
    RepositoryLocal<List<DataModel>> {
    override suspend fun saveToDB(appState: AppState) {
        dataSource.saveToDB(appState)
    }

    override suspend fun getData(word: String): List<DataModel> {
        return dataSource.getData(word)
    }
}
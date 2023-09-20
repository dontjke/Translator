package com.example.repository

import com.example.model.AppState
import com.example.model.dto.SearchResultDto
import com.example.repository.datasource.DataSourceLocal


class RepositoryImplementationLocal(private val dataSource: DataSourceLocal<List<SearchResultDto>>) :
    RepositoryLocal<List<SearchResultDto>> {
    override suspend fun saveToDB(appState: AppState) {
        dataSource.saveToDB(appState)
    }

    override suspend fun getData(word: String): List<SearchResultDto> {
        return dataSource.getData(word)
    }
}
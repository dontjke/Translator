package com.example.translator.model.datasource

import com.example.translator.model.AppState
import com.example.translator.model.data.DataModel
import com.example.translator.model.room.HistoryDao
import com.example.translator.utils.ui.convertDataModelSuccessToEntity
import com.example.translator.utils.ui.mapHistoryEntityToSearchResult


class RoomDataBaseImplementation(private val historyDao: HistoryDao) :
    DataSourceLocal<List<DataModel>> {
    override suspend fun saveToDB(appState: AppState) {
        convertDataModelSuccessToEntity(appState)?.let {
            historyDao.insert(it)
        }
    }

    override suspend fun getData(word: String): List<DataModel> {
        return mapHistoryEntityToSearchResult(historyDao.all())
    }
}

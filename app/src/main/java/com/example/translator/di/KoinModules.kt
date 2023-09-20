package com.example.translator.di

import androidx.room.Room
import com.example.model.dto.SearchResultDto
import com.example.repository.Repository
import com.example.repository.RepositoryImplementation
import com.example.repository.RepositoryImplementationLocal
import com.example.repository.RepositoryLocal
import com.example.repository.datasource.RetrofitImplementation
import com.example.repository.datasource.RoomDataBaseImplementation
import com.example.repository.room.HistoryDataBase
import com.example.translator.view.history.HistoryActivity
import com.example.translator.view.main.MainActivity
import com.example.translator.viewmodel.history.HistoryInteractor
import com.example.translator.viewmodel.history.HistoryViewModel
import com.example.translator.viewmodel.main.MainInteractor
import com.example.translator.viewmodel.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel


import org.koin.core.qualifier.named
import org.koin.dsl.module

val application = module {
    single { Room.databaseBuilder(get(), HistoryDataBase::class.java, "HistoryDB").build() }
    single { get<HistoryDataBase>().historyDao() }

    single<Repository<List<SearchResultDto>>> {
        RepositoryImplementation(
            RetrofitImplementation()
        )
    }
    single<RepositoryLocal<List<SearchResultDto>>> {
        RepositoryImplementationLocal(
            RoomDataBaseImplementation(get())
        )
    }
}

val mainScreen = module {
    scope(named<MainActivity>()) {
        scoped { MainInteractor(get(), get()) }
        viewModel { MainViewModel(get()) }
    }
}

val historyScreen = module {
    scope(named<HistoryActivity>()) {
        scoped { HistoryInteractor(get(), get()) }
        viewModel { HistoryViewModel(get()) }
    }
}

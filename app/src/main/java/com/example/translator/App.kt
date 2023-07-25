package com.example.translator

import android.app.Application
import com.example.translator.data.RepoImpl
import com.example.translator.presenter.BasePresenter
import com.example.translator.presenter.MainActivityPresenter

class App: Application() {
    val presenter: BasePresenter by lazy {
        MainActivityPresenter(RepoImpl())
    }
    override fun onCreate() {
        super.onCreate()
        instance = this

    }
    companion object {
       lateinit var instance: App
       private set
    }
}
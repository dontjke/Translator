package com.example.translator.view.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.translator.model.data.AppState
import com.example.translator.presenter.Presenter
import com.example.translator.viewmodel.BaseViewModel
import com.example.translator.viewmodel.Interactor

abstract class BaseActivity<T : AppState, I: Interactor<T>> : AppCompatActivity() {

    abstract val model: BaseViewModel<T>
    abstract fun renderData(appState: AppState)
}

package com.example.translator.view.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.translator.model.data.AppState
import com.example.translator.presenter.Presenter
import com.example.translator.viewmodel.BaseViewModel

abstract class BaseActivity<T : AppState> : AppCompatActivity() {

    abstract val model: BaseViewModel<T>
    abstract fun renderData(appState: T)
}

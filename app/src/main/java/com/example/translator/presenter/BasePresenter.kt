package com.example.translator.presenter

import com.example.translator.view.MainActivityContract

interface BasePresenter {
    fun attach(activity: MainActivityContract)
    fun detach()
    fun getDataFromRepo()
}
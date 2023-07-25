package com.example.translator.presenter

import com.example.translator.data.BaseRepo
import com.example.translator.view.MainActivityContract

class MainActivityPresenter(private val repo: BaseRepo) : BasePresenter {
    private var activity: MainActivityContract? = null

    override fun attach(activity: MainActivityContract) {
        this.activity = activity
    }

    override fun detach() {
        activity = null
    }

    override fun getDataFromRepo() {
        val data = repo.getData()
        activity?.showData(data)
    }
}
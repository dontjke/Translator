package com.example.translator.view.history

import android.os.Bundle
import androidx.lifecycle.Observer
import com.example.translator.databinding.ActivityHistoryBinding
import com.example.model.AppState
import com.example.model.data.DataModel
import com.example.core.BaseActivity
import com.example.translator.viewmodel.history.HistoryInteractor
import com.example.translator.viewmodel.history.HistoryViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class HistoryActivity : BaseActivity<com.example.model.AppState, HistoryInteractor>() {
    private lateinit var binding: ActivityHistoryBinding
    override lateinit var model: HistoryViewModel
    private val adapter: HistoryAdapter by lazy { HistoryAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        iniViewModel()
        initViews()
    }

    override fun onResume() {
        super.onResume()
        model.getData("", false)
    }

    override fun setDataToAdapter(data: List<com.example.model.data.DataModel>) {
        adapter.setData(data)
    }

    private fun iniViewModel() {
        if (binding.historyActivityRecyclerview.adapter != null) {
            throw IllegalStateException("The ViewModel should be initialised first")
        }
        val viewModel: HistoryViewModel by viewModel()
        model = viewModel
        model.subscribe().observe(this@HistoryActivity, Observer<com.example.model.AppState> { renderData(it) })
    }

    private fun initViews() {
        binding.historyActivityRecyclerview.adapter = adapter
    }
}
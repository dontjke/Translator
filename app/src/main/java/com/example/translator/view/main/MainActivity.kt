package com.example.translator.view.main

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.example.translator.BuildConfig.BOTTOM_SHEET_FRAGMENT_DIALOG_TAG
import com.example.translator.R
import com.example.translator.databinding.ActivityMainBinding
import com.example.utils.network.isOnline
import com.example.core.BaseActivity
import com.example.model.AppState
import com.example.model.data.DataModel
import com.example.translator.view.description.DescriptionActivity
import com.example.translator.view.history.HistoryActivity
import com.example.translator.view.main.adapter.MainAdapter
import com.example.translator.viewmodel.main.MainInteractor
import com.example.translator.viewmodel.main.MainViewModel
import com.example.utils.ui.viewById
import com.google.android.material.floatingactionbutton.FloatingActionButton
import org.koin.android.ext.android.inject


class MainActivity : BaseActivity<AppState, MainInteractor>() {

    private lateinit var binding: ActivityMainBinding
    override lateinit var model: MainViewModel
    private val adapter: MainAdapter by lazy { MainAdapter(onListItemClickListener) }

    private val mainActivityRecyclerview by
    viewById<RecyclerView>(R.id.main_activity_recyclerview)
    private val searchFAB by viewById<FloatingActionButton>(R.id.search_fab)


    private val fabClickListener: View.OnClickListener =
        View.OnClickListener {
            val searchDialogFragment = SearchDialogFragment.newInstance()
            searchDialogFragment.setOnSearchClickListener(onSearchClickListener)
            searchDialogFragment.show(supportFragmentManager, BOTTOM_SHEET_FRAGMENT_DIALOG_TAG)
        }

    private val onListItemClickListener: OnListItemClickListener =
        object : OnListItemClickListener {
            override fun onItemClick(data: DataModel) {
                startActivity(
                    DescriptionActivity.getIntent(
                        this@MainActivity,
                        data.text!!,
                        com.example.repository.convertMeaningsToString(data.meanings!!),
                        data.meanings!![0].imageUrl
                    )
                )
            }
        }

    private val onSearchClickListener: SearchDialogFragment.OnSearchClickListener =
        object : SearchDialogFragment.OnSearchClickListener {
            override fun onClick(searchWord: String) {
                isNetworkAvailable = isOnline(applicationContext)
                if (isNetworkAvailable) {
                    model.getData(searchWord, isNetworkAvailable)
                } else {
                    showNoInternetConnectionDialog()
                }
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        iniViewModel()
        initViews()
    }

    override fun setDataToAdapter(data: List<DataModel>) {
        adapter.setData(data)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.history_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_history -> {
                startActivity(Intent(this, HistoryActivity::class.java))
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun iniViewModel() {
        if (binding.mainActivityRecyclerview.adapter != null) {
            throw IllegalStateException("The ViewModel should be initialised first")
        }

        val viewModel: MainViewModel by inject()
        model = viewModel
        model.subscribe().observe(this@MainActivity, Observer<AppState> { renderData(it) })
    }

    private fun initViews() {
        searchFAB.setOnClickListener(fabClickListener)
        mainActivityRecyclerview.adapter = adapter

      //  binding.searchFab.setOnClickListener(fabClickListener)
       // binding.mainActivityRecyclerview.adapter = adapter
    }
}
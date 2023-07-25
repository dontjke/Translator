package com.example.translator.view


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.translator.App
import com.example.translator.databinding.ActivityMainBinding
import com.example.translator.entity.Person

class MainActivity : AppCompatActivity(), MainActivityContract {

    private lateinit var binding: ActivityMainBinding
    private val presenter = App.instance.presenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        presenter.attach(this)
        binding.button.setOnClickListener {
            presenter.getDataFromRepo()
        }
    }

    override fun showData(person: Person) {
        binding.textView.text = person.name
    }

    override fun onDestroy() {
        presenter.detach()
        super.onDestroy()
    }
}
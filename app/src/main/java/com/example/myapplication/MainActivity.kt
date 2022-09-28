package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.example.myapplication.databinding.MainActivityBinding
import com.example.myapplication.model.*

private const val GRID_COUNT = 2

class MainActivity : AppCompatActivity() {

    private lateinit var binding: MainActivityBinding
    private lateinit var adapter: MovieAdapter
    private val filmService: FilmService
        get() = (applicationContext as App).filmService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root) //потом посмотреть почему root

        adapter = MovieAdapter(onClicked = { openFilmDetails(it) }) //it - фильм который я передал
        adapter.filmList = filmService.filmList

        binding.mainActivityRv.apply {
            layoutManager = GridLayoutManager(this@MainActivity, GRID_COUNT)
            adapter = adapter
        }
    }

    //это переход на вторую вьюшку при нажатии на экран
    private fun openFilmDetails(film: FilmModel) {
        val intent = Intent(this, SecondActivity::class.java)
        intent.putExtra(SecondActivity.ID, binding.mainActivityRv.id)
        startActivity(intent)
    }
}

//чекнуть колбеки

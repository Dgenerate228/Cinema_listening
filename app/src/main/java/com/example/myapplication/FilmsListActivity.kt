package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.example.myapplication.databinding.FilmsListActivityRvBinding
import com.example.myapplication.model.*

private const val GRID_COUNT = 2

class FilmsListActivity : AppCompatActivity() {

    private lateinit var binding: FilmsListActivityRvBinding
    private lateinit var adapter: FilmAdapter
    private val filmService: FilmService
        get() = (applicationContext as App).filmService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FilmsListActivityRvBinding.inflate(layoutInflater)
        setContentView(binding.root)
        adapter = FilmAdapter(onClicked = { openFilmDetails(it) })
        adapter.filmList = filmService.filmList
        binding.filmsListRv.layoutManager = GridLayoutManager(this@FilmsListActivity, GRID_COUNT)
        binding.filmsListRv.adapter = adapter
    }

    private fun openFilmDetails(film: FilmModel) {
        val intent = Intent(this, DetailsFilmActivity::class.java)
      //  intent.putExtra(DetailsFilmActivity.ID, binding.filmsListRv.id)
        intent.putExtra(DetailsFilmActivity.FILM_NAME, film.name)
        intent.putExtra(DetailsFilmActivity.FILM_DESCRIPTION, film.description)
        intent.putExtra(DetailsFilmActivity.FILM_IMAGE, film.image)
        startActivity(intent)
    }
}

package com.example.myapplication

import android.app.Application
import com.example.myapplication.model.FilmService

class App : Application() {

    val filmService = FilmService()

}
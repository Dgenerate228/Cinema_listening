package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.myapplication.databinding.DetailsFilmActivityBinding

class DetailsFilmActivity : AppCompatActivity() {

    private lateinit var binding: DetailsFilmActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =
            DetailsFilmActivityBinding.inflate(layoutInflater).also { setContentView(it.root) }
        binding.detailsFilmName.findViewById<TextView>(R.id.detailsFilmName).also { onGet() }

        binding.detailsFilmDescription.findViewById<TextView>(R.id.detailsFilmDescription)
            .also { onGet() }

        binding.detailsPhotoImageView.findViewById<ImageView>(R.id.detailsPhotoImageView)
            .also { onGet() }

        binding.buttonBack.findViewById<Button>(R.id.buttonBack).also { goBack() }

    }

    private fun onGet() {
        Glide.with(binding.detailsPhotoImageView)
            .load(intent.getStringExtra(FILM_IMAGE))
            .into(binding.detailsPhotoImageView)

        binding.detailsFilmName.setText(intent.getStringExtra(FILM_NAME))
        binding.detailsFilmDescription.setText(intent.getStringExtra(FILM_DESCRIPTION))
    }

    fun goBack() {
        binding.buttonBack.setOnClickListener {
            finish()
        }
    }

    companion object {
        //   const val ID = "ID"
        const val FILM_NAME = "FILM NAME"
        const val FILM_DESCRIPTION = "FILM DESCRIPTION"
        const val FILM_IMAGE = "FILM IMAGE"

    }
}
package com.example.myapplication


import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.DetailsFilmActivityBinding
import com.example.myapplication.model.FilmModel

class DetailsFilmActivity : AppCompatActivity() {

    private lateinit var binding: DetailsFilmActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =
            DetailsFilmActivityBinding.inflate(layoutInflater).also { setContentView(it.root) }
        binding.secondFilmName.findViewById<TextView>(R.id.secondFilmName).also { onGet() }

        binding.secondFilmDescription.findViewById<TextView>(R.id.secondFilmDescription)
            .also { onGet() }

        binding.secondPhotoImageView.findViewById<ImageView>(R.id.secondPhotoImageView)
            .also { onGet() }
    }

    private fun onGet() {
       // binding.secondFilmName.setText(intent.getStringExtra())
        binding.secondFilmName.setText(intent.getCharSequenceExtra(FILM_NAME))
        binding.secondFilmDescription.setText(intent.getCharSequenceExtra(FILM_DESCRIPTION))
        binding.secondPhotoImageView.setTag(intent.getStringExtra(FILM_IMAGE))
    }

    companion object {
        const val ID = "ID"
        const val FILM_NAME = "FILM NAME"
        const val FILM_DESCRIPTION = "FILM DESCRIPTION"
        const val FILM_IMAGE = "FILM IMAGE"

    }
}
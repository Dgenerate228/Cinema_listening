package com.example.myapplication


import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.SecondActivityBinding

class SecondActivity : AppCompatActivity() {

    private lateinit var binding: SecondActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SecondActivityBinding.inflate(layoutInflater).also { setContentView(it.root) }
        binding.secondPhotoImageView.findViewById<ImageView>(R.id.secondPhotoImageView).also { onGet() }
        binding.secondFilmName.findViewById<TextView>(R.id.secondFilmName).also { onGet() }
        binding.secondFilmDescription.findViewById<TextView>(R.id.secondFilmDescription).also { onGet() }
    }

    private fun onGet() {
        binding.secondFilmName.setText(intent.getCharSequenceExtra(ID))
        binding.secondFilmDescription.setText(intent.getCharSequenceExtra(ID))
    }

    companion object {
        const val ID = "ID"
        const val FILM_NAME = "FILM NAME"
        const val FILM_DESCRIPTION = "FILM DESCRIPTION"
        const val FILM_IMAGE = "FILM IMAGE"

    }
}
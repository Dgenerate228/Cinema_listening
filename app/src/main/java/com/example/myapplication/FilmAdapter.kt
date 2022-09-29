package com.example.myapplication

import android.view.LayoutInflater
import com.bumptech.glide.Glide
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.RecyclerViewItemBinding
import com.example.myapplication.model.FilmModel

class FilmAdapter(

    private val onClicked: (FilmModel) -> Unit,
) : RecyclerView.Adapter<FilmAdapter.ViewHolder>() {

    var filmList: List<FilmModel> = emptyList()
        set(newValue) {
            field = newValue
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RecyclerViewItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val film = filmList[position]
        with(holder.binding) {
            filmName.text = film.name.ifEmpty { "Error" }
            recyclerViewItemCl.setOnClickListener { onClicked(film) }

            if (film.image.isNotBlank()) {
                Glide.with(photoImageView.context)
                    .load(film.image)
                    .circleCrop()
                    .placeholder(R.drawable.ic_launcher_foreground)
                    .error(R.drawable.ic_error)
                    .into(photoImageView)
            } else {
                photoImageView.setImageResource(R.drawable.ic_launcher_foreground)
            }

        }
    }

    class ViewHolder(val binding: RecyclerViewItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun getItemCount(): Int = filmList.size
}


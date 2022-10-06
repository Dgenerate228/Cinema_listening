package com.example.myapplication

import android.view.LayoutInflater
import com.bumptech.glide.Glide
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.RecyclerViewItemBinding
import com.example.myapplication.model.FilmModel

class FilmDiffUtilCallback(
    private val oldList: List<FilmModel>,
    private val newList: List<FilmModel>,
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size
    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldFilm = oldList[oldItemPosition]
        val newFilm = newList[newItemPosition]
        return oldFilm.id == newFilm.id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldFilm = oldList[oldItemPosition]
        val newFilm = newList[newItemPosition]
        return oldFilm == newFilm
    }

}

class FilmAdapter(
    private val onClicked: (FilmModel) -> Unit,
) : RecyclerView.Adapter<FilmAdapter.ViewHolder>() {

    var filmList: List<FilmModel> = emptyList()
        set(newValue) {
            val diffCallback = FilmDiffUtilCallback(field, newValue)
            val diffResult = DiffUtil.calculateDiff(diffCallback)
            field = newValue
            diffResult.dispatchUpdatesTo(this)
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
            itemViewCv.setOnClickListener { onClicked(film) }

            if (film.image.isNotBlank()) {
                Glide.with(photoImageView.context)
                    .load(film.image)
                    .error(R.drawable.ic_error)
                    .into(photoImageView)
            }
        }
    }

    // забыл че ета
    class ViewHolder(val binding: RecyclerViewItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun getItemCount(): Int = filmList.size
}


package com.example.myapplication


import android.view.LayoutInflater
import com.bumptech.glide.Glide
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.databinding.RecyclerViewItemBinding
import com.example.myapplication.model.FilmModel

class MovieAdapter(
    //это колбэк. то что в скобках это то что будет передаваться в активити
    private val onClicked: (FilmModel) -> Unit,  //тут была запятая, после Unit, - если че, вернуть
) : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    class ViewHolder(val binding: RecyclerViewItemBinding) : RecyclerView.ViewHolder(binding.root)

    var filmList: List<FilmModel> = emptyList()
        set(newValue) {
            field = newValue
            notifyDataSetChanged()  //извещаем адаптер о изменениях
        }

    //указываем то, где нам будет показываться
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RecyclerViewItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val film = filmList[position]
        with(holder.binding) {
            filmName.text = film.name
            recyclerViewItemCl.setOnClickListener { onClicked(film) } //клик конкретного фильма

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

    override fun getItemCount(): Int = filmList.size


}


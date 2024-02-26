package com.example.themovie.presentation.main.bottombar.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.themovie.R
import com.example.themovie.databinding.GenreItemBinding
import com.example.themovie.presentation.model.GenrePresentation

class GenreMovieAdapter(
    private val showAllListener: (Int, String) -> Unit,
    private val movieClickListener: (Int?) -> Unit
) :
    ListAdapter<GenrePresentation, GenreMovieAdapter.MyViewHolder>(DIFF_CALLBACK) {

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<GenrePresentation>() {
            override fun areItemsTheSame(
                oldItem: GenrePresentation,
                newItem: GenrePresentation
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: GenrePresentation,
                newItem: GenrePresentation
            ): Boolean {
                return oldItem == newItem
            }

        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            GenreItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    //Exibe a imagem desse filme
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        //Instancia da classe GenrePresentation
        val genre = getItem(position)


        holder.binding.genreName.text = genre.name

        //Instancia da classe MovieAdapter
        val movieAdapter = MovieAdapter(
            context = holder.binding.root.context,
            layoutInflater = R.layout.movie_item,
            movieClickListener = movieClickListener
        )

        val layoutManager =
            LinearLayoutManager(holder.binding.root.context, LinearLayoutManager.HORIZONTAL, false)

        holder.binding.textShowAll.setOnClickListener {
            genre.id?.let{
                showAllListener(genre.id, genre.name ?: "")
            }
        }

        holder.binding.recyclerMovies.layoutManager = layoutManager
        holder.binding.recyclerMovies.setHasFixedSize(true)
        holder.binding.recyclerMovies.adapter = movieAdapter
        movieAdapter.submitList(genre.movies)

    }

    inner class MyViewHolder(val binding: GenreItemBinding) : RecyclerView.ViewHolder(binding.root)
}
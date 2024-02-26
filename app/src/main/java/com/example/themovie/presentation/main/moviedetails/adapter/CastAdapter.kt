package com.example.themovie.presentation.main.moviedetails.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.themovie.databinding.CastItemBinding
import com.example.themovie.domain.model.Person

class CastAdapter(
) : ListAdapter<Person, CastAdapter.MyViewHolder>(DIFF_CALLBACK) {

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Person>() {
            override fun areItemsTheSame(
                oldItem: Person,
                newItem: Person
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: Person,
                newItem: Person
            ): Boolean {
                return oldItem == newItem
            }

        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            CastItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    //Exibe a imagem desse filme
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val person = getItem(position)

        Glide
            .with(holder.binding.root.context)
            .load("https://image.tmdb.org/t/p/w500${person.profilePath}")
            .into(holder.binding.personImage)

        holder.binding.personName.text = person.name
        holder.binding.textElenco.text = person.knownForDepartment

    }

    inner class MyViewHolder(val binding: CastItemBinding) : RecyclerView.ViewHolder(binding.root)
}
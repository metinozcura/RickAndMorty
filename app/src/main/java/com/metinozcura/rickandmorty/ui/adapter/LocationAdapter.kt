package com.metinozcura.rickandmorty.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.metinozcura.rickandmorty.data.model.Location
import com.metinozcura.rickandmorty.databinding.ItemLocationBinding
import javax.inject.Inject

class LocationAdapter @Inject constructor() :
    PagingDataAdapter<Location, LocationAdapter.LocationViewHolder>(LocationComparator) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        LocationViewHolder(
            ItemLocationBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    override fun onBindViewHolder(holder: LocationViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    inner class LocationViewHolder(private val binding: ItemLocationBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Location) = with(binding) {
            location = item
        }
    }

    object LocationComparator : DiffUtil.ItemCallback<Location>() {
        override fun areItemsTheSame(oldItem: Location, newItem: Location) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Location, newItem: Location) =
            oldItem == newItem
    }
}
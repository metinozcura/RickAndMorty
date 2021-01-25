package com.metinozcura.rickandmorty.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.metinozcura.rickandmorty.databinding.ItemLocationBinding
import com.metinozcura.rickandmorty.databinding.ItemSeparatorLocationBinding
import com.metinozcura.rickandmorty.ui.locations.model.LocationModel
import javax.inject.Inject

class LocationAdapter @Inject constructor() :
    PagingDataAdapter<LocationModel, RecyclerView.ViewHolder>(LocationComparator) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == TYPE_LOCATION) LocationDataViewHolder(
            ItemLocationBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
        else LocationSeparatorViewHolder(
            ItemSeparatorLocationBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        if (item is LocationModel.LocationData)
            (holder as LocationDataViewHolder).bind(item)
    }

    override fun getItemViewType(position: Int): Int {
        return if (getItem(position) is LocationModel.LocationData) TYPE_LOCATION
        else TYPE_SEPARATOR
    }

    inner class LocationDataViewHolder(private val binding: ItemLocationBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: LocationModel.LocationData) = with(binding) {
            location = item
        }
    }

    inner class LocationSeparatorViewHolder(private val binding: ItemSeparatorLocationBinding) :
        RecyclerView.ViewHolder(binding.root)

    object LocationComparator : DiffUtil.ItemCallback<LocationModel>() {
        override fun areItemsTheSame(oldItem: LocationModel, newItem: LocationModel): Boolean {
            val isSameLocationData = oldItem is LocationModel.LocationData
                    && newItem is LocationModel.LocationData
                    && oldItem.id == newItem.id

            val isSameSeparator = oldItem is LocationModel.LocationSeparator
                    && newItem is LocationModel.LocationSeparator
                    && oldItem.tag == newItem.tag

            return isSameLocationData || isSameSeparator
        }

        override fun areContentsTheSame(oldItem: LocationModel, newItem: LocationModel) =
            oldItem == newItem
    }

    companion object {
        private const val TYPE_LOCATION = 0
        private const val TYPE_SEPARATOR = 1
    }
}
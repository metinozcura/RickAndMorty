package com.metinozcura.rickandmorty.util.adapter.paging

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadState.Error
import androidx.paging.LoadState.Loading
import androidx.recyclerview.widget.RecyclerView
import com.metinozcura.rickandmorty.R
import com.metinozcura.rickandmorty.databinding.ItemNetworkStateBinding

class NetworkStateItemViewHolder(
    parent: ViewGroup,
    private val retryCallback: () -> Unit
) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.item_network_state, parent, false)
) {
    private val binding = ItemNetworkStateBinding.bind(itemView)
    private val progressBar = binding.progressBar
    private val errorMsg = binding.errorMsg
    private val retry = binding.retryButton.also { it.setOnClickListener { retryCallback() } }

    fun bindTo(loadState: LoadState) {
        progressBar.isVisible = loadState is Loading
        retry.isVisible = loadState is Error
        errorMsg.isVisible = !(loadState as? Error)?.error?.message.isNullOrBlank()
        errorMsg.text = (loadState as? Error)?.error?.message
    }
}
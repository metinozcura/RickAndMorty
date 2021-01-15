package com.metinozcura.rickandmorty.ui.episodes

import androidx.fragment.app.viewModels
import com.metinozcura.rickandmorty.R
import com.metinozcura.rickandmorty.base.BaseFragment
import com.metinozcura.rickandmorty.databinding.FragmentEpisodesBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EpisodesFragment : BaseFragment<FragmentEpisodesBinding, EpisodesViewModel>() {
    private val episodesViewModel: EpisodesViewModel by viewModels()
    override val layoutId: Int
        get() = R.layout.fragment_episodes

    override fun getVM(): EpisodesViewModel = episodesViewModel

    override fun bindVM(binding: FragmentEpisodesBinding, vm: EpisodesViewModel) {
    }
}
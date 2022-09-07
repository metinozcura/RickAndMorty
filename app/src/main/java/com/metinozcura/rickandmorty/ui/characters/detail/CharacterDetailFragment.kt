package com.metinozcura.rickandmorty.ui.characters.detail

import android.os.Bundle
import androidx.core.view.ViewCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.transition.TransitionInflater
import com.metinozcura.rickandmorty.R
import com.metinozcura.rickandmorty.base.BaseFragment
import com.metinozcura.rickandmorty.databinding.FragmentCharacterDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterDetailFragment :
    BaseFragment<FragmentCharacterDetailBinding, CharacterDetailViewModel>() {
    private val characterDetailViewModel: CharacterDetailViewModel by viewModels()
    private val args: CharacterDetailFragmentArgs by navArgs()

    override val layoutId: Int
        get() = R.layout.fragment_character_detail

    override fun getVM(): CharacterDetailViewModel = characterDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition =
            TransitionInflater.from(requireContext()).inflateTransition(android.R.transition.move)
    }

    override fun bindVM(binding: FragmentCharacterDetailBinding, vm: CharacterDetailViewModel) {
        with(binding) {
            character = args.character
            ViewCompat.setTransitionName(binding.ivAvatar, "avatar_${args.character.id}")
            ViewCompat.setTransitionName(binding.tvName, "name_${args.character.id}")
            ViewCompat.setTransitionName(binding.tvStatus, "status_${args.character.id}")
        }
    }
}
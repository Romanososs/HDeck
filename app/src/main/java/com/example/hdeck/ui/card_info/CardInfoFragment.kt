package com.example.hdeck.ui.card_info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import coil.load
import com.example.hdeck.R
import com.example.hdeck.databinding.FragmentCardInfoBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CardInfoFragment : Fragment() {
    private var _binding: FragmentCardInfoBinding? = null
    private val binding get() = _binding!!
    val args: CardInfoFragmentArgs by navArgs()
    private val viewModel: CardInfoViewModel by viewModels<CardInfoViewModelImpl>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCardInfoBinding.inflate(inflater, container, false)
        setStrings()
        viewModel.state.card.observe(viewLifecycleOwner) {
            binding.image.load(it.image)
            binding.title.text = it.name
            binding.set.text.text = it.set
            binding.heroClass.text.text = it.heroClass
            binding.rarity.text.text = it.rarity
            binding.type.text.text = it.type
            binding.artistName.text.text = it.artistName
        }
        return binding.root
    }

    private fun setStrings() {
        binding.set.title.text = getString(R.string.card_set)
        binding.heroClass.title.text = getString(R.string.card_heroClass)
        binding.rarity.title.text = getString(R.string.card_rarity)
        binding.type.title.text = getString(R.string.card_type)
        binding.artistName.title.text = getString(R.string.card_artistName)
    }

    override fun onStart() {
        super.onStart()
        viewModel.onViewShown()
    }

    override fun onStop() {
        super.onStop()
        viewModel.onViewHidden()
    }
}
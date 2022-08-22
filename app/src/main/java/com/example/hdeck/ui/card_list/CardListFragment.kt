package com.example.hdeck.ui.card_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import androidx.paging.LoadState
import com.example.hdeck.databinding.FragmentCardListBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CardListFragment : Fragment() {

    private var _binding: FragmentCardListBinding? = null
    private val binding get() = _binding!!
    val args: CardListFragmentArgs by navArgs()
    private val viewModel: CardListViewModel by viewModels<CardListViewModelImpl>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCardListBinding.inflate(inflater, container, false)
        val pagingAdapter = CardListAdapter(CardComparator) {
            viewModel.onItemClick(it)
        }
        binding.cards.adapter = pagingAdapter
        viewModel.state.cardApiList.observe(viewLifecycleOwner) { pagingData ->
            viewLifecycleOwner.lifecycleScope.launch {
                viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                    pagingAdapter.submitData(pagingData)
                }
            }
        }
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                pagingAdapter.loadStateFlow.collectLatest {
                    setLoading(it.refresh is LoadState.Loading)
                }
            }
        }
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        viewModel.onViewShown()
    }

    override fun onStop() {
        super.onStop()
        viewModel.onViewHidden()
    }

    private fun setLoading(isLoading: Boolean) {
        binding.progressBar.isVisible = isLoading
        binding.cards.isVisible = !isLoading
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
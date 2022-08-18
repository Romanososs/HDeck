package com.example.hdeck.ui.deck_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import androidx.paging.LoadState
import com.example.hdeck.databinding.FragmentDeckListBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DeckListFragment : Fragment() {

    private var _binding: FragmentDeckListBinding? = null
    private val binding get() = _binding!!
    val args: DeckListFragmentArgs by navArgs()
    private val viewModel: DeckListViewModel by viewModels<DeckListViewModelImpl>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentDeckListBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val pagingAdapter = DeckListAdapter(UserComparator)
        binding.cards.adapter = pagingAdapter
        viewModel.state.cardList.observe(viewLifecycleOwner) { pagingData ->
            viewLifecycleOwner.lifecycleScope.launch {
                pagingAdapter.submitData(pagingData)
            }
        }
        viewLifecycleOwner.lifecycleScope.launch {
            pagingAdapter.loadStateFlow.collectLatest {
                setLoading(it.refresh is LoadState.Loading)
            }
        }
        return root
    }
    override fun onStart() {
        super.onStart()
        viewModel.onViewShown()
    }
    override fun onStop() {
        super.onStop()
        viewModel.onViewHidden()
    }
    private fun setLoading(isLoading: Boolean){
        binding.progressBar.isVisible = isLoading
        binding.cards.isVisible = !isLoading
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
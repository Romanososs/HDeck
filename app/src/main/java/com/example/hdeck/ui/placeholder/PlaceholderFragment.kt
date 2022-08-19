package com.example.hdeck.ui.placeholder

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.hdeck.R
import com.example.hdeck.databinding.FragmentPlaceholderBinding
import com.example.hdeck.localization.LocaleService
import com.example.hdeck.localization.StringProvider
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class PlaceholderFragment : Fragment() {
    private var _binding: FragmentPlaceholderBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPlaceholderBinding.inflate(inflater, container, false)
//        binding.text.text = resources.getText(R.string.try_out)
        return binding.root
    }

    private fun setStrings() {
//        binding.text.text = localeService.getString(R.string.try_out)
    }
}
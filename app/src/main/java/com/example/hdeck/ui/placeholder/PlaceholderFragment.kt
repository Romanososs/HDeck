package com.example.hdeck.ui.placeholder

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.hdeck.databinding.FragmentPlaceholderBinding
import dagger.hilt.android.AndroidEntryPoint

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
        return binding.root
    }
}
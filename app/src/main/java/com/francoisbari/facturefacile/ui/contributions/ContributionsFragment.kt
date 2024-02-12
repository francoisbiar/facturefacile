package com.francoisbari.facturefacile.ui.contributions

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.francoisbari.facturefacile.databinding.FragmentContributionsBinding
import com.francoisbari.facturefacile.viewmodels.MainViewModel

class ContributionsFragment : Fragment() {

    private val viewModel: MainViewModel by viewModels({ requireParentFragment() })
    private var _binding: FragmentContributionsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentContributionsBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        return binding.root
    }

    companion object {
        fun newInstance(): ContributionsFragment {
            return ContributionsFragment()
        }
    }
}
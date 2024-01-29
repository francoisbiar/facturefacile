package com.francoisbari.facturefacile.ui.contributions

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.francoisbari.facturefacile.databinding.FragmentContributionsBinding
import com.francoisbari.facturefacile.viewmodels.ContributionsViewModel

class ContributionsFragment : Fragment() {

    private lateinit var viewModel: ContributionsViewModel
    private var _binding: FragmentContributionsBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[ContributionsViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentContributionsBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        return binding.root
    }

    companion object {
        fun newInstance(): ContributionsFragment {
            return ContributionsFragment()
        }
    }
}
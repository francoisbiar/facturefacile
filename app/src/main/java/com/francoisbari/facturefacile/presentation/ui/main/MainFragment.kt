package com.francoisbari.facturefacile.presentation.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.francoisbari.facturefacile.core.CompositionRoot
import com.francoisbari.facturefacile.databinding.FragmentMainBinding
import com.francoisbari.facturefacile.data.persistence.models.Months
import com.francoisbari.facturefacile.data.persistence.models.Years
import com.francoisbari.facturefacile.presentation.ui.contributions.ContributionsFragment
import com.francoisbari.facturefacile.presentation.viewmodels.MainViewModel

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private val viewModel: MainViewModel by viewModels {
        CompositionRoot.getMainViewModelFactory()
    }


    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        // Show the ContributionsFragment
        val contributionsFragment = ContributionsFragment.newInstance()
        childFragmentManager.beginTransaction().apply {
            replace(binding.contributionsContainerView.id, contributionsFragment)
            addToBackStack(null)
            commit()
        }

        setupYearSpinner()
        setupMonthSpinner()

        return binding.root
    }

    private fun setupYearSpinner() {
        val years = Years.toArrayList()

        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, years)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.yearSpinner.adapter = adapter
    }

    private fun setupMonthSpinner() {

        val months = Months.toArrayList()

        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, months)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.monthSpinner.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
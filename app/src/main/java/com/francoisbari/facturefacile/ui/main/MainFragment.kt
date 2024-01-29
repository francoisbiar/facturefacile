package com.francoisbari.facturefacile.ui.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.francoisbari.facturefacile.data.DataPersistenceFactory
import com.francoisbari.facturefacile.databinding.FragmentMainBinding
import com.francoisbari.facturefacile.ui.contributions.ContributionsFragment
import com.francoisbari.facturefacile.viewmodels.MainViewModel
import com.francoisbari.facturefacile.viewmodels.MainViewModelFactory

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val dataPersistence = DataPersistenceFactory.create(
            requireContext(), DataPersistenceFactory.DataPersistenceType.ROOM
        )
        val viewModelFactory = MainViewModelFactory(dataPersistence)
        viewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]
    }

    override fun onPause() {
        super.onPause()
        viewModel.saveData()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        viewModel.computeContributionsLiveData.observe(viewLifecycleOwner) {
            if (it) {
                // Show the ContributionsFragment
                val contributionsFragment = ContributionsFragment.newInstance()
                parentFragmentManager.beginTransaction().apply {
                    replace(binding.contributionsContainerView.id, contributionsFragment)
                    addToBackStack(null)
                    commit()

                }
            }
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.loadData()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
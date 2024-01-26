package com.francoisbari.facturefacile.ui.main

import android.os.Bundle
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.francoisbari.facturefacile.data.DataPersistenceFactory
import com.francoisbari.facturefacile.databinding.FragmentMainBinding

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
            requireContext(), DataPersistenceFactory.DataPersistenceType.FIRESTORE
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
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            nbOfDaysEditText.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                    // Nothing to do here
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                }

                override fun afterTextChanged(s: android.text.Editable?) {
                    viewModel.setNumberOfDays(s.toString().toIntOrNull() ?: 0)
                }
            })

            tjmEditText.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                    // Nothing to do here
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    // Nothing to do here
                }

                override fun afterTextChanged(s: android.text.Editable?) {
                    viewModel.setTjm(s.toString().toIntOrNull() ?: 0)
                }
            })

            addOneDayButton.setOnClickListener {
                viewModel.addOneDayClicked()
            }

            viewModel.total.observe(viewLifecycleOwner) { total ->
                totalTextView.text = total.toString()
            }

            viewModel.nbOfDays.observe(viewLifecycleOwner) { nbOfDays ->
                nbOfDaysEditText.setText(nbOfDays.toString())
            }

            viewModel.tjm.observe(viewLifecycleOwner) { tjm ->
                tjmEditText.setText(tjm.toString())
            }

            viewModel.loadData()

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
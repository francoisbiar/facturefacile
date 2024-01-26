package com.francoisbari.facturefacile.ui.main

import android.os.Bundle
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.francoisbari.facturefacile.R
import com.francoisbari.facturefacile.data.DataPersistenceFactory

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val dataPersistence = DataPersistenceFactory.create(
            requireContext(), DataPersistenceFactory.DataPersistenceType.SHARED_PREFERENCES
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
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val nbOfDaysEditText = view.findViewById<EditText>(R.id.nbOfDaysEditText)
        val tjmEditText = view.findViewById<EditText>(R.id.tjmEditText)
        val totalTextView = view.findViewById<TextView>(R.id.totalTextView)
        val addDayButton = view.findViewById<Button>(R.id.addOneDayButton)

        nbOfDaysEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // Nothing to do here
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: android.text.Editable?) {
                viewModel.setNumberOfDays(s.toString().toIntOrNull() ?: 0)
            }
        })

        tjmEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // Nothing to do here
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // Nothing to do here
            }

            override fun afterTextChanged(s: android.text.Editable?) {
                viewModel.setTjm(s.toString().toIntOrNull() ?: 0)
            }
        })

        addDayButton.setOnClickListener {
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
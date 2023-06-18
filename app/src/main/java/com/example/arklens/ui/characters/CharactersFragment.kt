package com.example.arklens.ui.characters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.arklens.databinding.FragmentCharactersBinding

class CharactersFragment : Fragment() {

    private var _binding: FragmentCharactersBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val viewModel = CharactersViewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        val charactersViewModel =
            ViewModelProvider(this)[CharactersViewModel::class.java]

        _binding = FragmentCharactersBinding.inflate(inflater, container, false)
        val root: View = binding.root

        viewModel.init()
        val textView: TextView = binding.textCharacters
        charactersViewModel.liveData.observe(viewLifecycleOwner) {
            textView.text = it.joinToString("\n")
        }
        return root
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
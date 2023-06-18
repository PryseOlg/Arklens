package com.example.arklens.ui.characters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.arklens.R
import com.example.arklens.adapters.CharacterAdapter
import com.example.arklens.databinding.FragmentCharactersBinding
import com.example.arklens.interfaces.CharacterListener
import com.example.arklens.models.Character


class CharactersFragment : Fragment(), CharacterListener {

    private var _binding: FragmentCharactersBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val viewModel = CharactersViewModel()
    private val characterAdapter = CharacterAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        val charactersViewModel =
            ViewModelProvider(this)[CharactersViewModel::class.java]

        _binding = FragmentCharactersBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.characters.adapter = characterAdapter
        val observer = Observer<List<Character>> { newValue ->
            characterAdapter.submitList(newValue)
        }
        viewModel.liveData.observe(viewLifecycleOwner, observer)
        viewModel.init()
        var view: RecyclerView = binding.characters
        charactersViewModel.liveData.observe(viewLifecycleOwner, observer)
        return root
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClick(character: Character) {
        val bundle = Bundle()
        bundle.apply {
            putString("character_id", character.id)
        }
        findNavController().navigate(R.id.navigation_characters, bundle)
    }
}
package com.example.arklens.ui.characters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.arklens.R
import com.example.arklens.adapters.CharactersAdapters
import com.example.arklens.databinding.FragmentCharactersBinding
import com.example.arklens.interfaces.CharacterListener
import com.example.arklens.models.Character
import com.example.arklens.repository.CharacterRepository
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class CharactersFragment : Fragment(), CharacterListener {

    private var _binding: FragmentCharactersBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val viewModel = CharactersViewModel()
    private val charactersAdapters = CharactersAdapters(this)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        val charactersViewModel =
            ViewModelProvider(this)[CharactersViewModel::class.java]

        _binding = FragmentCharactersBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.characters.adapter = charactersAdapters
        val observer = Observer<List<Character>> { newValue ->
            charactersAdapters.submitList(newValue)
        }
        binding.updateCharacter.setOnClickListener {
            updateCharacter()
        }

        viewModel.liveData.observe(viewLifecycleOwner, observer)
        viewModel.init(requireContext())
        charactersViewModel.liveData.observe(viewLifecycleOwner, observer)
        return root
    }

    private fun updateCharacter() {
        val characterRepository = CharacterRepository(requireContext())
        CoroutineScope(Dispatchers.IO).launch {
            val characters = characterRepository.fetchCharacters()
            charactersAdapters.submitList(characters)
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClick(character: Character) {
        val gson = Gson()
        val characterJson = gson.toJson(character)

        val bundle = Bundle()
        bundle.apply {
            putString("character", characterJson)
        }
        findNavController().navigate(R.id.navigation_full_character, bundle)
    }
}
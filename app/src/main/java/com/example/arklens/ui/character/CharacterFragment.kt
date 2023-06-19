package com.example.arklens.ui.character

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import com.example.arklens.R
import com.example.arklens.adapters.utils.ImageUtils
import com.example.arklens.databinding.FragmentCharacterBinding
import com.example.arklens.models.Character
import com.google.gson.Gson

class CharacterFragment : Fragment() {

    private var _binding: FragmentCharacterBinding? = null
    private val binding get() = _binding!!
    private val viewModel: CharacterViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        
        _binding = FragmentCharacterBinding.inflate(inflater, container, false)
        val root: View = binding.root
        init()

        val characterJson = arguments?.getString("character")
        if (characterJson != null) {
            val gson = Gson()
            val character = gson.fromJson(characterJson, Character::class.java)
            viewModel.init(character)
        }

        return root
    }

    private fun init() {
        initPortrait()
    }

    private fun initPortrait() {
        val characterObserver = Observer<Character> { newValue ->

            ImageUtils.loadCharacterPortrait(
                newValue.personalInfo.portraitUrl,
                binding.icon)
            binding.name.text = newValue.personalInfo.name
        }

        viewModel.liveData.observe(viewLifecycleOwner, characterObserver)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

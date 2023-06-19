package com.example.arklens.ui.full_character

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.arklens.adapters.utils.ImageUtils
import com.example.arklens.databinding.FullCharacterBinding
import com.example.arklens.models.Character
import com.google.gson.Gson

class FullCharacterFragment : Fragment() {

    private var _binding: FullCharacterBinding? = null
    private val binding get() = _binding!!
    private val viewModel: FullCharacterViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {


        _binding = FullCharacterBinding.inflate(inflater, container, false)
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
                binding.image)

            binding.name.text = newValue.personalInfo.name
            binding.race.text = newValue.race.name
            binding.background.text = newValue.personalInfo.background
            binding.gender.text = newValue.personalInfo.gender
            binding.className.text = newValue.`class`.name
            binding.age.text = newValue.personalInfo.age.toString()
            binding.alignment.text = newValue.personalInfo.alignment

            /*binding.characteristicSet.text = newValue.characteristicSet.strength.toString()
            binding.statSet.text = newValue.statSet.toString()
            binding.skillSet.text = newValue.skillSet.toString()
            binding.inventory.text = newValue.inventory.toString()*/
            /*binding.backButton.setOnClickListener {
                // Вернуться на предыдущий фрагмент
                findNavController().popBackStack()
            }*/

        }

        viewModel.liveData.observe(viewLifecycleOwner, characterObserver)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

package com.example.arklens.ui.full_character

import android.annotation.SuppressLint
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
import com.example.arklens.models.Characteristic
import com.example.arklens.models.Stat
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

    @SuppressLint("SetTextI18n")
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

            binding.strength.text = "Сила: " + displayCharacteristic(newValue.characteristics.strength)
            binding.dexterity.text = "Ловкость: "+  displayCharacteristic(newValue.characteristics.dexterity)
            binding.constitution.text =  "Выносливость: " + displayCharacteristic(newValue.characteristics.constitution)
            binding.intelligence.text = "Интеллект: " + displayCharacteristic(newValue.characteristics.intelligence)
            binding.wisdom.text = "Мудрость: " + displayCharacteristic(newValue.characteristics.wisdom)
            binding.charisma.text = "Харизма: " + displayCharacteristic(newValue.characteristics.charisma)

            binding.fortitude.text = "Сила духа: " + displayStat(newValue.stats.fortitude)
            binding.reflex.text = "Реакция: " + displayStat(newValue.stats.reflex)
            binding.will.text = "Воля: " + displayStat(newValue.stats.will)
            binding.concentration.text = "Концетрация: " + displayStat(newValue.stats.concentration)
            binding.perception.text = "Внимание: " + displayStat(newValue.stats.perception)

            binding.skillSet.text = newValue.skills.toString()
            binding.inventory.text = newValue.inventory.toString()


            /*binding.backButton.setOnClickListener {
                // Вернуться на предыдущий фрагмент
                findNavController().popBackStack()
            }*/

        }

        viewModel.liveData.observe(viewLifecycleOwner, characterObserver)
    }

    private fun displayCharacteristic(characteristic: Characteristic): String {
        val value = if (characteristic.value >= 0) "+${characteristic.value}" else characteristic.value.toString()
        return "${characteristic.base} $value"
    }

    private fun displayStat(stat: Stat): String {
        val value = if (stat.`class` >= 0) "+${stat.`class`}" else stat.`class`.toString()
        return "${stat.additional} $value"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

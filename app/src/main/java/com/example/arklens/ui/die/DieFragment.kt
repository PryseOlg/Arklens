package com.example.arklens.ui.die

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.arklens.models.Die
import com.example.arklens.databinding.FragmentDieBinding

class DieFragment : Fragment() {

    private lateinit var binding: FragmentDieBinding
    private lateinit var die: Die

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDieBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        die = Die(6)
        binding.rollButton.setOnClickListener {
            val result = die.throwDie()
            binding.resultTextView.text = result.toString()
        }
    }
}


package com.example.arklens.ui.die

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.arklens.adapters.DiceAdapter
import com.example.arklens.adapters.DiceClickListener
import com.example.arklens.databinding.FragmentDieBinding

class DieFragment : Fragment(), DiceClickListener {

    private lateinit var binding: FragmentDieBinding
    private lateinit var viewModel: DieViewModel
    private lateinit var adapter: DiceAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDieBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(DieViewModel::class.java)

        adapter = DiceAdapter(context, emptyList(), this)
        binding.diceRecyclerView.adapter = adapter
        binding.diceRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        viewModel.diceValues.observe(viewLifecycleOwner) { diceValues ->
            adapter.updateDiceList(diceValues)
        }

    }

    override fun onDiceClick(position: Int) {
        viewModel.rollDie(position)
    }
}



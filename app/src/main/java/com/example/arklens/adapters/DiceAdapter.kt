package com.example.arklens.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.arklens.R

class DiceAdapter(private val diceList: List<Int>) : RecyclerView.Adapter<DiceAdapter.DiceViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiceViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_die, parent, false)
        return DiceViewHolder(view)
    }

    override fun onBindViewHolder(holder: DiceViewHolder, position: Int) {
        val diceValue = diceList[position]
        holder.bind(diceValue)
    }

    override fun getItemCount(): Int {
        return diceList.size
    }

    inner class DiceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val diceValueTextView: TextView = itemView.findViewById(R.id.diceValueTextView)

        fun bind(diceValue: Int) {
            diceValueTextView.text = diceValue.toString()
        }
    }
}
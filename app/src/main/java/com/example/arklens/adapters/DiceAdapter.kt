package com.example.arklens.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.arklens.R
import com.example.arklens.models.Die

interface DiceClickListener {
    fun onDiceClick(position: Int)
}

class DiceAdapter(private var diceList: List<Die>, private val listener: DiceClickListener) : RecyclerView.Adapter<DiceAdapter.DiceViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiceViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_dice, parent, false)
        return DiceViewHolder(view)
    }

    override fun onBindViewHolder(holder: DiceViewHolder, position: Int) {
        val die = diceList[position]
        holder.bind(die)
    }

    override fun getItemCount(): Int {
        return diceList.size
    }

    fun updateDiceList(newDiceList: List<Die>) {
        diceList = newDiceList
        notifyDataSetChanged()
    }

    inner class DiceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val diceValueTextView: TextView = itemView.findViewById(R.id.diceValueTextView)
        private val rollButton: Button = itemView.findViewById(R.id.rollButton)

        fun bind(die: Die) {
            diceValueTextView.text = die.diceValue.toString()

            rollButton.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    listener.onDiceClick(position)
                }
            }
        }
    }
}



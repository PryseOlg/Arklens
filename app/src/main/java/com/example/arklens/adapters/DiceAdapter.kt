package com.example.arklens.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.arklens.R
import com.example.arklens.models.Die
import kotlin.math.max

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
        holder.bind(die, position)
    }

    override fun getItemCount(): Int {
        return diceList.size
    }

    fun updateDiceList(newDiceList: List<Die>) {
        diceList = newDiceList
        notifyDataSetChanged()
    }

    inner class DiceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val diceNameTextView: TextView = itemView.findViewById(R.id.diceNameTextView)
        private val diceValueTextView: TextView = itemView.findViewById(R.id.diceValueTextView)
        private val rollButton: Button = itemView.findViewById(R.id.rollButton)

        fun bind(die: Die, position: Int) {
            // Установка имени кубика
            diceNameTextView.text = "Кубик ${die.max}"
            // Установка значения кубика
            diceValueTextView.text = die.diceValue.toString()

            rollButton.setOnClickListener {
                val adapterPosition = adapterPosition
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    listener.onDiceClick(adapterPosition)
                }
            }
        }
    }
}



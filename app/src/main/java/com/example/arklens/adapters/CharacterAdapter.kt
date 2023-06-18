package com.example.arklens.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.arklens.R
import com.example.arklens.interfaces.CharacterListener
import com.example.arklens.models.Character

class CharacterAdapter(val listener: CharacterListener) :
    ListAdapter<Character, RecyclerView.ViewHolder>(MyDiffCallback()) {

    override fun getItemViewType(position: Int): Int {
        return R.id.navigation_characters

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            R.id.navigation_characters -> {
                val binding = CharacterBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent, false
                )
                CharacterHolder(parent.context, binding);
            }

            else -> throw IllegalStateException("Unknown view type $viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
            R.id.navigation_characters -> (holder as CharacterHolder).bind(getItem(position))
            else -> throw IllegalStateException("Unknown item view type ${holder.itemViewType}")
        }
    }

    class MyDiffCallback : DiffUtil.ItemCallback<Character>() {
        override fun areItemsTheSame(
            oldItem: Character,
            newItem: Character
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: Character,
            newItem: Character
        ): Boolean {
            return oldItem.id == newItem.id
        }
    }
}





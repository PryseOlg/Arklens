package com.example.arklens.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.arklens.R
import com.example.arklens.databinding.CharacterBinding
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

    inner class CharacterHolder(
        private val context: Context,
        private val binding: CharacterBinding
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(character: Character) = with(binding) {
            itemView.setOnClickListener {
                listener.onClick(character)
            }
            name.text = character.personalInfo.name
            /*characterBlock.backgroundTintList =
                ColorStateList.valueOf(
                    ContextCompat.getColor(
                        context,
                        ProfileUtils.getColorByStars(character.stars)
                    )
                )
            ProfileUtils.loadImage(
                ProfileUtils.getImageFromGoogle(character.image),
                this.character,
                R.drawable.loader_animation
            )
            ProfileUtils.loadImage(
                ProfileUtils.getImageFromGoogle(character.element.image),
                element,
                R.drawable.loader_animation
            )
            ProfileUtils.loadImage(
                ProfileUtils.getImageFromGoogle(character.weaponType.image),
                weaponType,
                R.drawable.loader_animation
            )
            if (character.stars in 4..5) {
                stars.setImageResource(ProfileUtils.getImageByStars(character.stars))
            } else {
                stars.setImageResource(R.drawable.broken_image)
            }*/
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





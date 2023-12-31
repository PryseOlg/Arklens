package com.example.arklens.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.arklens.R
import com.example.arklens.adapters.utils.ImageUtils
import com.example.arklens.databinding.FragmentCharacterBinding
import com.example.arklens.interfaces.CharacterListener
import com.example.arklens.models.Character

class CharactersAdapters(val listener: CharacterListener) :
    ListAdapter<Character, RecyclerView.ViewHolder>(MyDiffCallback()) {

    override fun getItemViewType(position: Int): Int {
        return R.id.navigation_characters

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            R.id.navigation_characters -> {
                val binding = FragmentCharacterBinding.inflate(
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
        private val binding: FragmentCharacterBinding
    ) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(character: Character) = with(binding) {
            itemView.setOnClickListener {
                listener.onClick(character)
            }
            val className = character.`class`.name
            val level = character.`class`.level
            name.text = character.personalInfo.name
            race.text = character.race.name
            role.text = "$className $level"

            ImageUtils.loadCharacterPortrait(character.personalInfo.portraitUrl, icon)

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





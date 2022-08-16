package com.example.hdeck.ui.deck_list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.hdeck.R
import com.example.hdeck.model.Card

class DeckListAdapter(diffCallback: DiffUtil.ItemCallback<Card>) :
    PagingDataAdapter<Card, DeckListViewHolder>(diffCallback) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DeckListViewHolder {
        return DeckListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_card, parent, false))
    }

    override fun onBindViewHolder(holder: DeckListViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class DeckListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val nameView: TextView = itemView.findViewById(R.id.title)
    private val flavorTextView: TextView = itemView.findViewById(R.id.flavorText)
    private val imageView: ImageView = itemView.findViewById(R.id.image)

    fun bind(card: Card?) {
            imageView.load(card?.image) {
            }
            nameView.text = card?.name
            flavorTextView.text = card?.flavorText
    }
}

object UserComparator : DiffUtil.ItemCallback<Card>() {
    override fun areItemsTheSame(oldItem: Card, newItem: Card): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Card, newItem: Card): Boolean {
        return oldItem == newItem
    }
}
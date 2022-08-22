package com.example.hdeck.ui.card_list

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
import com.example.hdeck.model.CardApi
import com.example.hdeck.utils.stringFromHTML

class CardListAdapter(
    diffCallback: DiffUtil.ItemCallback<CardApi>,
    private val clickListener: (String?) -> Unit
) :
    PagingDataAdapter<CardApi, CardListViewHolder>(diffCallback) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CardListViewHolder {
        return CardListViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_card, parent, false)
        )
    }

    override fun onBindViewHolder(holder: CardListViewHolder, position: Int) {
        holder.bind(getItem(position), clickListener)
    }
}

class CardListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val nameView: TextView = itemView.findViewById(R.id.title)
    private val flavorTextView: TextView = itemView.findViewById(R.id.flavorText)
    private val imageView: ImageView = itemView.findViewById(R.id.image)

    fun bind(card: CardApi?, clickListener: (String?) -> Unit) {
        itemView.setOnClickListener{
            clickListener(card?.slug)
        }
        imageView.load(card?.image)
        nameView.text = card?.name
        flavorTextView.text = stringFromHTML(card?.flavorText)
    }

}

object CardComparator : DiffUtil.ItemCallback<CardApi>() {
    override fun areItemsTheSame(oldItem: CardApi, newItem: CardApi): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: CardApi, newItem: CardApi): Boolean {
        return oldItem == newItem
    }
}
package com.srikarsams.senya.ui.fragment.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.srikarsams.senya.R
import com.srikarsams.senya.data.Attraction
import com.srikarsams.senya.databinding.ViewHolderAttractionBinding

class HomeFragmentAdapter(
    private val onClickedCallback: (String) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val attractions = ArrayList<Attraction>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return AttractionViewHolder(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as AttractionViewHolder).onBind(attractions[position], onClickedCallback)
    }

    override fun getItemCount(): Int {
        return attractions.size
    }

    fun setData(attractions: List<Attraction>) {
        this.attractions.clear()
        this.attractions.addAll(attractions)
        notifyDataSetChanged()
    }

    inner class AttractionViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.view_holder_attraction, parent, false)
    ) {
        private val binding = ViewHolderAttractionBinding.bind(itemView)

        fun onBind(attraction: Attraction, onClicked: (String) -> Unit) {
            binding.titleTextView.text = attraction.title
            Picasso.get().load(attraction.image_urls[0]).into(binding.headerImageView)
            binding.monthsToVisitTextView.text = attraction.months_to_visit

            binding.root.setOnClickListener {
                onClicked(attraction.id)
            }
        }
    }
}
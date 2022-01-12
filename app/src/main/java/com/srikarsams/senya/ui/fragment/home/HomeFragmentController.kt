package com.srikarsams.senya.ui.fragment.home

import com.airbnb.epoxy.EpoxyController
import com.squareup.picasso.Picasso
import com.srikarsams.senya.R
import com.srikarsams.senya.data.Attraction
import com.srikarsams.senya.databinding.ViewHolderAttractionBinding
import com.srikarsams.senya.ui.epoxy.ViewBindingKotlinModel

class HomeFragmentController(
    private val onClickedCallback: (String) -> Unit
) : EpoxyController() {

    var isLoading: Boolean = false
        set(value) {
            field = value
            requestModelBuild()
        }

    var attractions = ArrayList<Attraction>()
        set(value) {
            field = value
            isLoading = false
        }

    override fun buildModels() {
        if (isLoading) return

        if (attractions.isEmpty()) return

        attractions.forEach {
            AttractionEpoxyModel(it, onClickedCallback).id(it.id).addTo(this)
        }
    }

    data class AttractionEpoxyModel(
        val attraction: Attraction,
        val onClicked: (String) -> Unit
    ) : ViewBindingKotlinModel<ViewHolderAttractionBinding>(R.layout.view_holder_attraction) {
        override fun ViewHolderAttractionBinding.bind() {
            titleTextView.text = attraction.title
            Picasso.get().load(attraction.image_urls[0]).into(headerImageView)
            monthsToVisitTextView.text = attraction.months_to_visit

            root.setOnClickListener {
                onClicked(attraction.id)
            }
        }

    }
}
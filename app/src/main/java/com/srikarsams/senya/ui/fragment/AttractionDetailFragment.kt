package com.srikarsams.senya.ui.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.*
import androidx.navigation.fragment.navArgs
import com.squareup.picasso.Picasso
import com.srikarsams.senya.R
import com.srikarsams.senya.databinding.FragmentAttractionDetailBinding

class AttractionDetailFragment : BaseFragment() {
    private var _binding: FragmentAttractionDetailBinding? = null
    private val binding get() = _binding!!

    private val safeArgs: AttractionDetailFragmentArgs by navArgs()
    private val attraction by lazy {
        attractions.find { it.id == safeArgs.attractionId }!!
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAttractionDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.titleTextView.text = attraction.title
        Picasso.get().load(attraction.image_urls[0]).into(binding.headerImageView)
        binding.descriptionTextView.text =
            attraction.description
        binding.monthsToVisitTextView.text = attraction.months_to_visit
        binding.numberOfFactsTextView.text = "${attraction.facts.size.toString()} Facts"
        binding.numberOfFactsTextView.setOnClickListener {

        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_attraction_detail, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menuItemLocation -> {
                val gmmIntentURI =
                    Uri.parse("geo:${attraction.location.latitude},${attraction.location.longitude}?z=9&q=${attraction.title}")
                val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentURI)
                mapIntent.setPackage("com.google.android.apps.maps")
                startActivity(mapIntent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
package com.srikarsams.senya.ui.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AlertDialog
import androidx.navigation.fragment.navArgs
import com.squareup.picasso.Picasso
import com.srikarsams.senya.R
import com.srikarsams.senya.data.Attraction
import com.srikarsams.senya.databinding.FragmentAttractionDetailBinding

class AttractionDetailFragment : BaseFragment() {
    private var _binding: FragmentAttractionDetailBinding? = null
    private val binding get() = _binding!!

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

        activityViewModel.selectedAttractionLiveData.observe(viewLifecycleOwner) { attraction ->
            binding.titleTextView.text = attraction.title
            Picasso.get().load(attraction.image_urls[0]).into(binding.headerImageView)
            binding.descriptionTextView.text =
                attraction.description
            binding.monthsToVisitTextView.text = attraction.months_to_visit
            binding.numberOfFactsTextView.text = "${attraction.facts.size.toString()} Facts"
            binding.numberOfFactsTextView.setOnClickListener {
                val message = "\u2022 ${attraction.facts.joinToString("\n\n\u2022 ")}"
                AlertDialog.Builder(requireContext()).setTitle("${attraction.title} facts")
                    .setMessage(message).setPositiveButton("Okay") { dialog, _ ->
                        dialog.dismiss()
                    }.show()
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_attraction_detail, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menuItemLocation -> {
                val attraction = activityViewModel.selectedAttractionLiveData.value ?: return true
                activityViewModel.locationSelectedLiveData.postValue(attraction)
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

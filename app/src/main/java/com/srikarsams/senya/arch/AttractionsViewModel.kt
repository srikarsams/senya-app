package com.srikarsams.senya.arch

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.srikarsams.senya.data.Attraction

class AttractionsViewModel : ViewModel() {

    private val repository = AttractionsRepository()

    // HomeFragment
    val attractionListLiveData = MutableLiveData<List<Attraction>>()

    // AttractionDetailFragment
    val selectedAttractionLiveData = MutableLiveData<Attraction>()

    val locationSelectedLiveData = MutableLiveData<Attraction>()

    fun init(context: Context) {
        val attractionsList = repository.parseAttractions(context)
        attractionListLiveData.postValue(attractionsList)
    }

    fun onSelectedAttraction(attractionId: String) {
        val selectedAttraction = attractionListLiveData.value?.find {
            attractionId == it.id
        } ?: return
        selectedAttractionLiveData.postValue(selectedAttraction)
    }
}
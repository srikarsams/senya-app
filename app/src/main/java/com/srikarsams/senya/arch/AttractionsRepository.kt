package com.srikarsams.senya.arch

import android.content.Context
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.srikarsams.senya.R
import com.srikarsams.senya.data.Attraction

class AttractionsRepository {
    private val moshi: Moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()

    fun parseAttractions(context: Context): ArrayList<Attraction> {
        val textFromFile =
            context.resources.openRawResource(R.raw.croatia).bufferedReader().use { it.readText() }

        val type = Types.newParameterizedType(
            List::class.java,
            Attraction::class.java
        )
        val adapter: JsonAdapter<List<Attraction>> = moshi.adapter(type)
        return adapter.fromJson(textFromFile) as? ArrayList<Attraction> ?: ArrayList()
    }
}
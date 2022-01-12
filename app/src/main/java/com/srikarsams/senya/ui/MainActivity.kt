package com.srikarsams.senya.ui

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.srikarsams.senya.R
import com.srikarsams.senya.arch.AttractionsViewModel
import com.srikarsams.senya.data.Attraction

class MainActivity : AppCompatActivity() {

    lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    val viewModel: AttractionsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
        navController = navHostFragment.navController

        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

        viewModel.init(this)

        viewModel.locationSelectedLiveData.observe(this) { attraction ->
            val gmmIntentURI =
                Uri.parse("geo:${attraction.location.latitude},${attraction.location.longitude}?z=9&q=${attraction.title}")
            val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentURI)
            mapIntent.setPackage("com.google.android.apps.maps")
            startActivity(mapIntent)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}
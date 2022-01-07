package com.srikarsams.senya.ui.fragment

import androidx.fragment.app.Fragment
import com.srikarsams.senya.ui.MainActivity

abstract class BaseFragment: Fragment() {
    protected val navController by lazy {
        (activity as MainActivity).navController
    }
}
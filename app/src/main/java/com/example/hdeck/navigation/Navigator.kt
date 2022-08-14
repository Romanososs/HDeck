package com.example.hdeck.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import javax.inject.Inject

interface Navigator {
    var navController: NavController?
    //  fun
}

class NavigatorImpl @Inject constructor(

): Navigator {
    override var navController: NavController? = null

}
package com.example.hdeck.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import javax.inject.Inject

interface Navigator {
    var navController: NavController?
}


class NavigatorImpl @Inject constructor(

): Navigator {
    override var navController: NavController? = null
//    override fun navigateBack() {
//        navController?.popBackStack()
//    }
//
//    override fun navigateToMagazineCatalog() {
//        navController?.navigate(ScreenRoute.MagazineCatalog.name) {
//            popUpTo(navController!!.graph.findStartDestination().id) {
//                saveState = true
//            }
//            launchSingleTop = true
//            restoreState = true
//        }
//    }
//
//    override fun navigateToProfile() {
//        navController?.navigate(ScreenRoute.Profile.name) {
//            popUpTo(navController!!.graph.findStartDestination().id) {
//                saveState = true
//            }
//            launchSingleTop = true
//            restoreState = true
//        }
//    }
//
//    override fun navigateToPurchases() {
//        navController?.navigate(ScreenRoute.Purchases.name) {
//            popUpTo(navController!!.graph.findStartDestination().id) {
//                saveState = true
//            }
//            launchSingleTop = true
//            restoreState = true
//        }
//    }
//
//    override fun navigateToMagazinePage(issueId: String, headerTitle: String) {
//        navController?.navigate("${ScreenRoute.MagazinePage.name}/${issueId}/${headerTitle}")
//    }
//
//    override fun navigateToArticlePage(articleId: String, itemId: Int) {
//        navController?.navigate("${ScreenRoute.ArticlePage.name}/${articleId}/${itemId}")
//    }

}
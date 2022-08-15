package com.example.hdeck.navigation

import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import com.example.hdeck.R
import com.example.hdeck.model.Category
import com.example.hdeck.ui.deck_list.DeckListFragmentDirections
import javax.inject.Inject

interface Navigator {
    var navController: NavController?
    fun navigateToHeroClassList(slug: String?)
    fun navigateToCardSetList(slug: String?)
    fun navigateToCardRarityList(slug: String?)
}

class NavigatorImpl @Inject constructor(

) : Navigator {
    override var navController: NavController? = null
    override fun navigateToHeroClassList(slug: String?) {
        val action = DeckListFragmentDirections.actionGlobalDeckList(Category.HeroClass.ordinal, slug ?: "")
        navController?.navigate(action)
    }

    override fun navigateToCardSetList(slug: String?) {
        val action = DeckListFragmentDirections.actionGlobalDeckList(Category.CardSet.ordinal, slug ?: "")
        navController?.navigate(action)
    }

    override fun navigateToCardRarityList(slug: String?) {
        val action = DeckListFragmentDirections.actionGlobalDeckList(Category.CardRarity.ordinal, slug ?: "")
        navController?.navigate(action)
    }

}
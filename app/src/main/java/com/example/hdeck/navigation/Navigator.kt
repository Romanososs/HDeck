package com.example.hdeck.navigation

import androidx.navigation.NavController
import com.example.hdeck.model.enums.Category
import com.example.hdeck.ui.card_list.CardListFragmentDirections
import javax.inject.Inject

interface Navigator {
    var navController: NavController?
    fun navigateToHeroClassList(slug: String?)
    fun navigateToCardSetList(slug: String?)
    fun navigateToCardRarityList(slug: String?)
    fun navigateToCard(slug: String?)
}

class NavigatorImpl @Inject constructor(
) : Navigator {
    override var navController: NavController? = null
    override fun navigateToHeroClassList(slug: String?) {
        val action = CardListFragmentDirections.actionGlobalDeckList(Category.HeroClass.ordinal, slug ?: "")
        navController?.navigate(action)
    }

    override fun navigateToCardSetList(slug: String?) {
        val action = CardListFragmentDirections.actionGlobalDeckList(Category.CardSet.ordinal, slug ?: "")
        navController?.navigate(action)
    }

    override fun navigateToCardRarityList(slug: String?) {
        val action = CardListFragmentDirections.actionGlobalDeckList(Category.CardRarity.ordinal, slug ?: "")
        navController?.navigate(action)
    }

    override fun navigateToCard(slug: String?) {
        val action = CardListFragmentDirections.actionCardListToCardInfo(slug ?: "")
        navController?.navigate(action)
    }

}
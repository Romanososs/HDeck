package com.example.hdeck.ui.main

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.hdeck.R
import com.example.hdeck.databinding.ActivityMainBinding
import com.example.hdeck.localization.LocaleService
import com.example.hdeck.localization.StringProvider
import com.example.hdeck.navigation.Navigator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var navigator: Navigator

    @Inject
    lateinit var localeService: LocaleService
    private val viewModel: MainViewModel by viewModels<MainViewModelImpl>()

    lateinit var menuHeroClasses: DropDownMenu
    lateinit var menuCardSets: DropDownMenu
    lateinit var menuCardRarities: DropDownMenu

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.contentMain.toolbar)

        val navController = findNavController(R.id.fragment)
        navigator.navController = navController
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.placeholder, R.id.deck_list
            ), binding.drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        createMenus()
        //TODO rework(!)
        lifecycleScope.launch {
            localeService.setLocale()
        }
        viewModel.state.heroClassList.observe(this) {
            binding.navMain.navContentMain.dropdownMenuClasses.dropdownField.text =
                it.getCurrent().toString()
            menuHeroClasses.setData(it.list)
            binding.navMain.navContentMain.dropdownMenuClasses.dropdownLayout.visibility =
                if (it.isNotEmpty()) View.VISIBLE else View.GONE
        }
        viewModel.state.cardSetList.observe(this) {
            binding.navMain.navContentMain.dropdownMenuSets.dropdownField.text =
                it.getCurrent().toString()
            menuCardSets.setData(it.list)
            binding.navMain.navContentMain.dropdownMenuSets.dropdownLayout.visibility =
                if (it.isNotEmpty()) View.VISIBLE else View.GONE
        }
        viewModel.state.cardRarityList.observe(this) {
            binding.navMain.navContentMain.dropdownMenuRarity.dropdownField.text =
                it.getCurrent().toString()
            menuCardRarities.setData(it.list)
            binding.navMain.navContentMain.dropdownMenuRarity.dropdownLayout.visibility =
                if (it.isNotEmpty()) View.VISIBLE else View.GONE
        }
    }

    private fun createMenus() {
        menuHeroClasses = DropDownMenu(
            this, binding.navMain.navContentMain.dropdownMenuClasses.dropdownLayout
        ) {
            viewModel.onClassHeroListItemClick(it)
            binding.drawerLayout.closeDrawers()
        }
        binding.navMain.navContentMain.dropdownMenuClasses.dropdownField.setOnClickListener {
            viewModel.onClassHeroClick()
            binding.drawerLayout.closeDrawers()
        }
        binding.navMain.navContentMain.dropdownMenuClasses.dropdownButton.setOnClickListener {
            menuHeroClasses.show()
        }
        menuCardSets = DropDownMenu(
            this, binding.navMain.navContentMain.dropdownMenuSets.dropdownLayout
        ) {
            viewModel.onCardSetListItemClick(it)
            binding.drawerLayout.closeDrawers()
        }
        binding.navMain.navContentMain.dropdownMenuSets.dropdownField.setOnClickListener {
            viewModel.onCardSetClick()
            binding.drawerLayout.closeDrawers()
        }
        binding.navMain.navContentMain.dropdownMenuSets.dropdownButton.setOnClickListener {
            menuCardSets.show()
        }
        menuCardRarities = DropDownMenu(
            this, binding.navMain.navContentMain.dropdownMenuRarity.dropdownLayout
        ) {
            viewModel.onCardRarityListItemClick(it)
            binding.drawerLayout.closeDrawers()
        }
        binding.navMain.navContentMain.dropdownMenuRarity.dropdownField.setOnClickListener {
            viewModel.onCardRarityClick()
            binding.drawerLayout.closeDrawers()
        }
        binding.navMain.navContentMain.dropdownMenuRarity.dropdownButton.setOnClickListener {
            menuCardRarities.show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val locale = when (item.itemId) {
            R.id.ru -> "ru"
            else -> "en"
        }
        viewModel.onLocaleClick(locale)
        return super.onOptionsItemSelected(item)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onStart() {
        super.onStart()
        viewModel.onViewShown()
    }

    override fun onStop() {
        super.onStop()
        viewModel.onViewHidden()
    }

    override fun onDestroy() {
        super.onDestroy()
        navigator.navController = null
    }
}
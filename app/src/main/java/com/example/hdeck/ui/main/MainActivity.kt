package com.example.hdeck.ui.main

import android.content.Context
import android.content.res.Configuration
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
import com.example.hdeck.model.Language
import com.example.hdeck.model.SupportedLanguages
import com.example.hdeck.model.enums.Category
import com.example.hdeck.model.enums.SetType
import com.example.hdeck.navigation.Navigator
import dagger.hilt.EntryPoint
import dagger.hilt.EntryPoints
import dagger.hilt.InstallIn
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.EntryPointAccessors
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeoutOrNull
import java.util.*
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var navigator: Navigator

    @EntryPoint
    @InstallIn(SingletonComponent::class)
    interface HiltLocaleServiceEntryPoint {
        val service: LocaleService
    }
//    @Inject
//    lateinit var localeService: LocaleService

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
        viewModel.state.activeCategory.observe(this) {
            setCategory(it)
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

    override fun attachBaseContext(newBase: Context) {
        val config = Configuration()
        val service = EntryPointAccessors.fromApplication(
            newBase,
            HiltLocaleServiceEntryPoint::class.java
        ).service
        //FIXME usage of runBlocking
        val currentLocale = runBlocking { service.getLocale() }
        config.setLocale(Locale(currentLocale))
//        applyOverrideConfiguration(config)
        super.attachBaseContext(newBase.createConfigurationContext(config))
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

    private fun setCategory(category: Category) {
        binding.navMain.navContentMain.dropdownMenuClasses.line.visibility =
            if (category == Category.HeroClass) View.VISIBLE else View.GONE
        binding.navMain.navContentMain.dropdownMenuSets.line.visibility =
            if (category == Category.CardSet) View.VISIBLE else View.GONE
        binding.navMain.navContentMain.dropdownMenuRarity.line.visibility =
            if (category == Category.CardRarity) View.VISIBLE else View.GONE
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val locale = when (item.itemId) {
            R.id.de -> SupportedLanguages.GERMAN
            R.id.en -> SupportedLanguages.ENGLISH
            R.id.es_ES -> SupportedLanguages.SPANISH
            R.id.es_MX -> SupportedLanguages.MEXICAN
            R.id.fr -> SupportedLanguages.FRENCH
            R.id.it -> SupportedLanguages.ITALIAN
            R.id.ja -> SupportedLanguages.JAPANESE
            R.id.ko -> SupportedLanguages.KOREAN
            R.id.pl -> SupportedLanguages.POLISH
            R.id.pt -> SupportedLanguages.PORTUGUESE
            R.id.ru -> SupportedLanguages.RUSSIAN
            R.id.th -> SupportedLanguages.THAI
            R.id.zh -> SupportedLanguages.CHINESE
            else -> null
        }
        locale?.let { viewModel.onLocaleClick(it.code) { recreate() } }
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
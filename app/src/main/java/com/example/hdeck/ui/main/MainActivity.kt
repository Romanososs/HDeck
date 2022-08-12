package com.example.hdeck.ui.main

import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.ListPopupWindow
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import com.example.hdeck.R
import com.example.hdeck.auth.AuthService
import com.example.hdeck.databinding.ActivityMainBinding
import com.example.hdeck.navigation.Navigator
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var navigator: Navigator
    @Inject
    lateinit var authService: AuthService
    private val viewModel: MainViewModel by viewModels<MainViewModelImpl>()

    lateinit var menuHeroClasses: DropDownMenu
    lateinit var menuCardSets: DropDownMenu
    lateinit var menuCardRarities: DropDownMenu

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.contentMain.toolbar)
        authService.start()
        navigator.navController = findNavController(R.id.fragment)
        createMenus()
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
        }
        binding.navMain.navContentMain.dropdownMenuClasses.dropdownField.setOnClickListener {
            viewModel.onClassHeroClick()
        }
        binding.navMain.navContentMain.dropdownMenuClasses.dropdownButton.setOnClickListener {
            menuHeroClasses.show()
        }
        menuCardSets = DropDownMenu(
            this, binding.navMain.navContentMain.dropdownMenuSets.dropdownLayout
        ) {
            viewModel.onCardSetListItemClick(it)
        }
        binding.navMain.navContentMain.dropdownMenuSets.dropdownField.setOnClickListener {
            viewModel.onCardSetClick()
        }
        binding.navMain.navContentMain.dropdownMenuSets.dropdownButton.setOnClickListener {
            menuCardSets.show()
        }
        menuCardRarities = DropDownMenu(
            this, binding.navMain.navContentMain.dropdownMenuRarity.dropdownLayout
        ) {
            viewModel.onCardRarityListItemClick(it)
        }
        binding.navMain.navContentMain.dropdownMenuRarity.dropdownField.setOnClickListener {
            viewModel.onCardRarityClick()
        }
        binding.navMain.navContentMain.dropdownMenuRarity.dropdownButton.setOnClickListener {
            menuCardRarities.show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onDestroy() {
        super.onDestroy()
        navigator.navController = null
    }
}
package com.example.hdeck.ui

import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.ListPopupWindow
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.hdeck.R
import com.example.hdeck.databinding.ActivityMainBinding
import com.example.hdeck.navigation.Navigator
import com.google.android.material.navigation.NavigationView
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    @Inject
    lateinit var navigator: Navigator
    private val viewModel: MainViewModel by viewModels<MainViewModelImpl>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.contentMain.toolbar)

//        val navView: NavigationView = binding.navMain.navViewLayout
        val navController = findNavController(R.id.fragment)
        navigator.navController = navController
        viewModel.state.observe(this){

        }
//        appBarConfiguration = AppBarConfiguration(setOf(R.id.deck_list), binding.drawerLayout)
//        setupActionBarWithNavController(navController, appBarConfiguration)
//        navView.setupWithNavController(navController)

        val listPopupWindow =
            ListPopupWindow(this, null, com.google.android.material.R.attr.listPopupWindowStyle)
        listPopupWindow.anchorView = binding.navMain.navContentMain.dropdownMenu1.dropdownLayout
        val items = listOf(
            "Item 1",
            "Item 2",
            "Item 3",
            "Item 4",
            "Item 4",
            "Item 4",
            "Item 4",
            "Item 4",
            "Item 4",
            "Item 4",
            "Item 4",
            "Item 4"
        )
        val adapter = ArrayAdapter(this, R.layout.list_popup_window_item, items)

        listPopupWindow.setAdapter(adapter)
        listPopupWindow.height = 550

        listPopupWindow.setOnItemClickListener { parent: AdapterView<*>?, view: View?, position: Int, id: Long ->
            listPopupWindow.dismiss()
        }
        binding.navMain.navContentMain.dropdownMenu1.dropdownButton.setOnClickListener {
            listPopupWindow.show()
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
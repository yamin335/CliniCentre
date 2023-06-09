package com.clinicentre.androidApp.ui.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.LiveData
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.clinicentre.androidApp.R
import com.clinicentre.androidApp.databinding.MainActivityBinding
import com.clinicentre.androidApp.ui.base.BaseActivity
import com.clinicentre.androidApp.utils.NavigationHost
import com.clinicentre.androidApp.utils.hideKeyboard
import com.clinicentre.androidApp.utils.setupWithNavController
import dagger.hilt.android.AndroidEntryPoint

interface ShowHideBottomNavCallback {
    fun showOrHideBottomNav(showHide: Boolean)
}

@AndroidEntryPoint
class MainActivity : BaseActivity<MainActivityBinding, MainActivityViewModel>(), NavigationHost, ShowHideBottomNavCallback {
    private lateinit var binding: MainActivityBinding
    override val viewModel: MainActivityViewModel by viewModels()

    private var currentNavController: LiveData<NavController>? = null
    private var currentNavHostFragment: LiveData<NavHostFragment>? = null

    private val fragmentWithoutBottomNav = setOf(
        R.id.homeFragment,
        R.id.servicesFragment
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.showBottomNav = true

        // Setup multi-backStack supported bottomNav
        if (savedInstanceState == null) {
            setupBottomNavigationBar()
        } // Else, need to wait for onRestoreInstanceState
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        // Now that BottomNavigationBar has restored its instance state
        // and its selectedItemId, we can proceed with setting up the
        // BottomNavigationBar with Navigation
        setupBottomNavigationBar()
    }

    override fun registerToolbarWithNavigation(toolbar: Toolbar) {
        setSupportActionBar(toolbar)
        currentNavController?.value?.let {
            setupActionBarWithNavController(it)
        }
    }

    /**
     * Called on first creation and when restoring state.
     */
    private fun setupBottomNavigationBar() {

        val navGraphIds = listOf(
            R.navigation.home_nav_graph,
            R.navigation.service_nav_graph,
            R.navigation.more_nav_graph
        )

        // Setup the bottom navigation view with a payment_graph of navigation graphs
        val (controller, navHost) = binding.bottomNav.setupWithNavController(
            navGraphIds = navGraphIds,
            fragmentManager = supportFragmentManager,
            containerId = R.id.nav_host_container,
            intent = intent
        )

        // Whenever the selected controller changes, setup the action bar.
        controller.observe(this, { navController ->
//            appBarConfiguration = AppBarConfiguration(
//                navGraph = navController.graph,
//                drawerLayout = drawer_layout
//            )
            // Set up ActionBar
//            setSupportActionBar(toolbar)
//            setupActionBarWithNavController(navController)

            navController.addOnDestinationChangedListener { _, destination, _ ->
                hideKeyboard()
                //binding.showBottomNav = destination.id !in fragmentWithoutBottomNav
            }

//            setupActionBarWithNavController(navController)
        })

        currentNavController = controller
        currentNavHostFragment = navHost

//        currentNavController?.observe(this, { homeController ->
//            if (homeController.graph.startDestination == R.id.home2Fragment && preferencesHelper.shouldClearBackStackOfHomeNav) {
//                CoroutineScope(Dispatchers.Main.immediate).launch {
//                    delay(700)
//                    homeController.popBackStack(
//                        homeController.graph.startDestination, false
//                    )
//                }
//                preferencesHelper.shouldClearBackStackOfHomeNav = false
//            }
//        })
    }

    override fun onBackPressed() {
        onBackPressedDispatcher.onBackPressed()
    }

    override fun onSupportNavigateUp(): Boolean {
        return currentNavController?.value?.navigateUp() ?: false || super.onSupportNavigateUp()
    }

    override fun showOrHideBottomNav(showHide: Boolean) {
        binding.showBottomNav = showHide
    }
}
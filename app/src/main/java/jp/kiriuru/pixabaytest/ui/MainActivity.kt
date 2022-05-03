package jp.kiriuru.pixabaytest.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import jp.kiriuru.pixabaytest.R
import jp.kiriuru.pixabaytest.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityMainBinding

    //    private lateinit var navController: NavController // old
    private lateinit var appBarConfiguration: AppBarConfiguration
    private val navController: NavController by lazy {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_main) as NavHostFragment
        navHostFragment.navController
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        //old
//        val navHostFragment =
//            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_main) as NavHostFragment
//        navController = navHostFragment.navController

        val bottomNavView: BottomNavigationView = mBinding.bottomNavView
        bottomNavView.setupWithNavController(navController)
        mBinding.bottomNavView.isVisible = false

        val toolbar = mBinding.toolbar
        setSupportActionBar(toolbar)

        //скрытие editText в ImageDetailFragment
        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.image_detail) {
                mBinding.searchFieldTB.visibility = View.GONE
            } else {
                mBinding.searchFieldTB.visibility = View.VISIBLE
            }
        }



        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.image_list, R.id.navigation_notifications, R.id.navigation_dashboard
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration)
    }
}





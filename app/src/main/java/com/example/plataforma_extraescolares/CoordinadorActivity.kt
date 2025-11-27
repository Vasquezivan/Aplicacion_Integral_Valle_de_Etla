package com.example.plataforma_extraescolares

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.plataforma_extraescolares.databinding.ActivityCoordinadorBinding
import com.google.android.material.navigation.NavigationView

class CoordinadorActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCoordinadorBinding
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCoordinadorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // ✔ Ahora funciona correctamente
        setSupportActionBar(binding.appBarCoordinador.toolbar)

        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navController = findNavController(R.id.nav_host_fragment_content_coordinador)

        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home,
                R.id.nav_vestudiantes,
                R.id.nav_constancias,
                R.id.nav_resultados,
                R.id.nav_informe
            ),
            drawerLayout
        )

        setupActionBarWithNavController(navController, appBarConfiguration)
        binding.navView.setupWithNavController(navController)

        binding.navView.setNavigationItemSelectedListener { menuItem ->
            handleNavigation(menuItem, navController)
            binding.drawerLayout.closeDrawers()
            true
        }
    }

    private fun handleNavigation(menuItem: MenuItem, navController: androidx.navigation.NavController) {
        when (menuItem.itemId) {
            R.id.nav_home -> navController.navigate(R.id.nav_home) {
                popUpTo(R.id.nav_home) { inclusive = true }
            }
            R.id.nav_vestudiantes -> navController.navigate(R.id.nav_vestudiantes)
            R.id.nav_constancias -> navController.navigate(R.id.nav_constancias)
            R.id.nav_resultados -> navController.navigate(R.id.nav_resultados)
            R.id.nav_informe -> navController.navigate(R.id.nav_informe)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_coordinador)
        return NavigationUI.navigateUp(navController, appBarConfiguration) || super.onSupportNavigateUp()
    }
}

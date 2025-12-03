package com.example.plataforma_extraescolares

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.plataforma_extraescolares.databinding.ActivityCoordinadorUhBinding

class Union_Hidalgo : AppCompatActivity() {

    private lateinit var binding: ActivityCoordinadorUhBinding
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflar layout
        binding = ActivityCoordinadorUhBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Configurar Toolbar
        setSupportActionBar(binding.appBarCoordinadorUh.toolbar)

        // Obtener NavController correctamente
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment_content_coordinador_uh) as NavHostFragment
        val navController = navHostFragment.navController

        // Configuración Drawer + Navigation
        val drawerLayout: DrawerLayout = binding.drawerLayout
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home,
                R.id.nav_vestudiantes_uh,
                R.id.nav_constancias_uh,
                R.id.nav_resultados_uh,
                R.id.nav_informe_uh
            ),
            drawerLayout
        )

        setupActionBarWithNavController(navController, appBarConfiguration)
        binding.navView.setupWithNavController(navController)

        // Manejar clicks manuales del menú (opcional, si necesitas lógica extra)
        binding.navView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_home -> navController.navigate(R.id.nav_home)
                R.id.nav_vestudiantes_uh -> navController.navigate(R.id.nav_vestudiantes_uh)
                R.id.nav_constancias_uh -> navController.navigate(R.id.nav_constancias_uh)
                R.id.nav_resultados_uh -> navController.navigate(R.id.nav_resultados_uh)
                R.id.nav_informe_uh -> navController.navigate(R.id.nav_informe_uh)
            }
            drawerLayout.closeDrawers()
            true
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // Si quieres agregar opciones en la Toolbar, hazlo aquí
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment_content_coordinador_uh) as NavHostFragment
        val navController = navHostFragment.navController
        return NavigationUI.navigateUp(navController, appBarConfiguration) || super.onSupportNavigateUp()
    }
}

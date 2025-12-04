package com.example.plataforma_extraescolares

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.example.plataforma_extraescolares.databinding.ActivityCoordinadorUhBinding
import android.view.View


class Union_Hidalgo : AppCompatActivity() {

    private lateinit var binding: ActivityCoordinadorUhBinding
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCoordinadorUhBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // ❌ Ya no usamos Toolbar → Así evitamos que aparezca barra
        // setSupportActionBar(binding.appBarCoordinadorUh.toolbar)

        // Hacer la pantalla fullscreen sin barra superior
        window.decorView.systemUiVisibility =
            View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment_content_coordinador_uh) as NavHostFragment

        val navController = navHostFragment.navController
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

        // Drawer funcionando normalmente
        binding.navView.setupWithNavController(navController)

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

    override fun onSupportNavigateUp(): Boolean {
        return false
    }
}

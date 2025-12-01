package com.example.plataforma_extraescolares

import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import com.example.plataforma_extraescolares.databinding.ActivityCoordinadorDvBinding
import com.google.android.material.navigation.NavigationView

class Dimitrio_Vallejo : AppCompatActivity() {

    private lateinit var binding: ActivityCoordinadorDvBinding
    private lateinit var toggle: ActionBarDrawerToggle   // <<< IMPORTANTE

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCoordinadorDvBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Toolbar
        setSupportActionBar(binding.appBarCoordinadorDv.toolbar)

        // Drawer
        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView

        // ============================================
        // 🔥 AQUI ESTÁ LO QUE TE HACÍA FALTA
        // ============================================
        toggle = ActionBarDrawerToggle(
            this,
            drawerLayout,
            binding.appBarCoordinadorDv.toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )

        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()   // <<< Esto hace aparecer el ICONO del menú
        // ============================================


        // Listener del menú
        navView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {

                R.id.nav_home -> {}

                R.id.nav_vestudiantes_dv -> {}

                R.id.nav_constancias_dv -> {}

                R.id.nav_resultados_dv -> {}

                R.id.nav_informe_dv -> {}
            }

            drawerLayout.closeDrawers()
            true
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return true
    }
}

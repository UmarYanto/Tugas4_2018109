package com.example.tugas4_2018109

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private var drawer: DrawerLayout? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //mengganti actionbar dengan toolbar
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        //memanggil drawer_layout dari activity_main.xml
        drawer = findViewById(R.id.drawer_layout)
        val navigationView = findViewById<NavigationView>(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this)
        //membuat hamburger icon pada toolbar dan animasinya
        val toggle = ActionBarDrawerToggle(
            this, drawer, toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        with(drawer) {
            this?.addDrawerListener(toggle)
        }
        toggle.syncState()
        //membuat default navigation menu select
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().replace(
                R.id.fragment_container,
                fragment_profile()
            ).commit()
            navigationView.setCheckedItem(R.id.nav_message)
        }
    }

    //drawer menu fragment handler
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_profile -> supportFragmentManager.beginTransaction().replace(
                R.id.fragment_container,
                fragment_profile()
            ).commit()
            R.id.nav_message -> supportFragmentManager.beginTransaction().replace(
                R.id.fragment_container,
                fragment_chat()
            ).commit()
            R.id.nav_keranjang -> supportFragmentManager.beginTransaction().replace(
                R.id.fragment_container,
                fragment_keranjang()
            ).commit()
        }
        drawer!!.closeDrawer(GravityCompat.START)
        return true
    }

    //on back press behavior
    override fun onBackPressed() {
        if (drawer!!.isDrawerOpen(GravityCompat.START)) {
            drawer!!.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
}

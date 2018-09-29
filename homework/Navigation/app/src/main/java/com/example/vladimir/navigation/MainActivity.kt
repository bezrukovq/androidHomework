package com.example.vladimir.navigation

import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, MyListener {
    private lateinit var tvLogin: TextView
    private lateinit var tvEmail: TextView
    private lateinit var frProfile: ProfileFragment
    private lateinit var frGalery: Fragment
    private lateinit var frTools: Fragment
    private lateinit var manager: FragmentManager
    private lateinit var transaction: FragmentTransaction

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        val drawer = findViewById<View>(R.id.drawer_layout) as DrawerLayout
        val toggle = ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer.addDrawerListener(toggle)
        toggle.syncState()
        frProfile = ProfileFragment()
        frGalery = GaleryFragment()
        frTools = ToolsFragment()
        manager = supportFragmentManager
        val navigationView = findViewById<View>(R.id.nav_view) as NavigationView
        navigationView.setNavigationItemSelectedListener(this)
        val hView = navigationView.getHeaderView(0)
        tvLogin = hView.findViewById(R.id.tv_Hlogin)
        tvEmail = hView.findViewById(R.id.tv_Hemail)

    }

    override fun onBackPressed() {
        val drawer = findViewById<View>(R.id.drawer_layout) as DrawerLayout
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }


    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        transaction = manager.beginTransaction()
        when (id) {
            R.id.nav_gallery -> {
                transaction.replace(R.id.container, frGalery, GaleryFragment.TAG)
                transaction.addToBackStack(null)


            }
            R.id.nav_profile -> {
                transaction.replace(R.id.container, frProfile, ProfileFragment.TAG)
                transaction.addToBackStack(null)


            }
            R.id.nav_manage -> {
                transaction.replace(R.id.container, frTools, ToolsFragment.TAG)
                transaction.addToBackStack(null)
            }
        }
        transaction.commit()

        val drawer = findViewById<View>(R.id.drawer_layout) as DrawerLayout
        drawer.closeDrawer(GravityCompat.START)
        return true
    }

    override fun callback(login: String, email: String) {
        frProfile.saveData(login, email)
        tvLogin.text = login
        tvEmail.text = email
    }

}

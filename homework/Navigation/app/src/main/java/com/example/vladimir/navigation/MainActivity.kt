package com.example.vladimir.navigation

import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.nav_header_main.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, MyListener {
    private lateinit var frProfile: ProfileFragment
    private lateinit var frGalery: Fragment
    private lateinit var frTools: Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()
        frProfile = ProfileFragment()
        frGalery = GaleryFragment()
        frTools = ToolsFragment()
        nav_view.setNavigationItemSelectedListener(this)
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
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
        val transaction = supportFragmentManager.beginTransaction()
        when (id) {
            R.id.nav_gallery -> {
                transaction.replace(R.id.container, frGalery, GaleryFragment.TAG)
                transaction.addToBackStack(GaleryFragment.TAG)
            }
            R.id.nav_profile -> {
                transaction.replace(R.id.container, frProfile, ProfileFragment.TAG)
                transaction.addToBackStack(ProfileFragment.TAG)
            }
            R.id.nav_manage -> {
                transaction.replace(R.id.container, frTools, ToolsFragment.TAG)
                transaction.addToBackStack(ToolsFragment.TAG)
            }
        }
        transaction.commit()

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun callback(login: String, email: String) {
        frProfile.saveData(login, email)
        tv_h_login.text = login
        tv_h_email.text = email
    }

}

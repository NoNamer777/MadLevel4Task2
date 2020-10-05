package com.nonamer777.madlevel4task2.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.nonamer777.madlevel4task2.R
import com.nonamer777.madlevel4task2.repository.GameRepository

class MainActivity : AppCompatActivity() {

    /** Navigation controller for navigating */
    private lateinit var navController: NavController

    companion object {
        /** A Game Repository. */
        lateinit var gamesRepo: GameRepository
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        setSupportActionBar(findViewById(R.id.toolbar))

        navController = findNavController(R.id.nav_host_fragment)
        gamesRepo = GameRepository(this)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)

        // Listen to Navigation Events to dynamically show / hide icons.
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.label == "fragment_history") {
                true -> {
                    menu?.findItem(R.id.menuClearHistory)?.isVisible = true
                    menu?.findItem(R.id.menuHistory)?.isVisible = false

                    supportActionBar?.setDisplayHomeAsUpEnabled(true)
                    supportActionBar?.setDisplayShowHomeEnabled(true)
                }
                else -> {
                    menu?.findItem(R.id.menuClearHistory)?.isVisible = false
                    menu?.findItem(R.id.menuHistory)?.isVisible = true

                    supportActionBar?.setDisplayHomeAsUpEnabled(false)
                    supportActionBar?.setDisplayShowHomeEnabled(false)
                }
            }
        }
        return super.onCreateOptionsMenu(menu)
    }

    /** Handles click events on different menu action items. */
    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        R.id.menuHistory -> {
            navController.navigate(R.id.action_gameFragment_to_historyFragment)

            true
        }

        R.id.menuClearHistory -> {
            HistoryFragment.clearHistory()

            true
        }

        android.R.id.home -> {
            navController.navigate(R.id.action_historyFragment_to_gameFragment)

            true
        }

        else -> {
            super.onOptionsItemSelected(item)
        }
    }
}

package com.horie1024.bottomappbarssample

import android.content.res.Resources
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var fab: FloatingActionButton
    private lateinit var bar: BottomAppBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fab = findViewById(R.id.fab)
        bar = findViewById(R.id.bar)
        setSupportActionBar(bar)

        bar.setNavigationOnClickListener {
            Log.i("test", "navigation click")
        }

        val host: NavHostFragment = supportFragmentManager
            .findFragmentById(R.id.my_nav_host_fragment) as NavHostFragment? ?: return

        // Set up Action Bar
        val navController = host.navController
        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            val dest: String = try {
                resources.getResourceName(destination.id)
            } catch (e: Resources.NotFoundException) {
                Integer.toString(destination.id)
            }

            when (dest) {
                "com.horie1024.bottomappbarssample:id/home" -> {
                    bar.fabAlignmentMode = BottomAppBar.FAB_ALIGNMENT_MODE_CENTER
                }
                "com.horie1024.bottomappbarssample:id/detail" -> {
                    bar.fabAlignmentMode = BottomAppBar.FAB_ALIGNMENT_MODE_END
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.option, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            R.id.search -> {
                Snackbar.make(findViewById<View>(android.R.id.content), "test", Snackbar.LENGTH_SHORT)
                    .setAction("ACTION") {}
                    .setAnchorView(fab)
                    .show()

                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}

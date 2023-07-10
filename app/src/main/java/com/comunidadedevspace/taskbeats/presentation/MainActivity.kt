package com.comunidadedevspace.taskbeats.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import com.comunidadedevspace.taskbeats.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNavView = findViewById<BottomNavigationView>(R.id.bottom_nav_view)

        val taskListFragment = TaskListFragment.newInstance()
        val newsListFragment = NewsListFragment.newInstance()

        bottomNavView.setOnItemSelectedListener { menuItem ->
            val fragment = when(menuItem.itemId) {
                R.id.task_list -> taskListFragment
            }
        }


        supportFragmentManager.commit {
            add(R.id.fragment_container_view, taskListFragment)
            setReorderingAllowed(true)
        }
    }
}
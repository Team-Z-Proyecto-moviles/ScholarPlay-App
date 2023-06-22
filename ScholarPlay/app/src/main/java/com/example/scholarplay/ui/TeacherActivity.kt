package com.example.scholarplay.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.scholarplay.R
import com.example.scholarplay.databinding.ActivityTeacherBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class TeacherActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTeacherBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTeacherBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.bottomNavigationView.itemActiveIndicatorColor = getColorStateList(R.color.green)
        binding.bottomNavigationView.selectedItemId = R.id.teacherHomeFragment2

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        val navController = findNavController(R.id.fragmentContainerView)

        NavigationUI.setupWithNavController(bottomNavigationView, navController)

        navController.addOnDestinationChangedListener{_, destination, _ ->
            when(destination.id){
                R.id.teacherHomeFragment2 -> showBottomNav()
                R.id.profileFragment -> showBottomNav()
                R.id.settingsFragment2 -> showBottomNav()
                else -> hideBottomNav()
            }
        }
    }

    private fun showBottomNav(){
        binding.bottomNavigationView.visibility = View.VISIBLE
    }

    private fun hideBottomNav(){
        binding.bottomNavigationView.visibility = View.GONE
    }


}
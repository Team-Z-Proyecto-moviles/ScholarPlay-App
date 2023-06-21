package com.example.scholarplay.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.example.scholarplay.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.example.scholarplay.databinding.ActivityStudentBinding
import com.example.scholarplay.ui.homepage.student.HomeFragment
import com.example.scholarplay.ui.profile.ProfileFragment

class StudentActivity : AppCompatActivity() {

    private lateinit var binding: ActivityStudentBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityStudentBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.bottomNavigationView.itemActiveIndicatorColor = getColorStateList(R.color.green)
        // replaceFragment(HomeFragment())
        binding.bottomNavigationView.selectedItemId = R.id.homeFragment

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
         val navController = findNavController(R.id.fragmentContainerView)

        NavigationUI.setupWithNavController(bottomNavigationView, navController)

        navController.addOnDestinationChangedListener{_, destination, _ ->
            when(destination.id){
                R.id.homeFragment -> showBottomNav()
                R.id.profileFragment -> showBottomNav()
                R.id.settingsFragment -> showBottomNav()
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


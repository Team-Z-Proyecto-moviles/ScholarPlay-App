package com.example.scholarplay.ui.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import com.example.scholarplay.R
import com.example.scholarplay.ScholarPlayApplication
import com.example.scholarplay.databinding.FragmentProfileBinding
import com.example.scholarplay.ui.profile.viewmodel.ProfileViewModel

class ProfileFragment : Fragment() {

    private val profileViewModel : ProfileViewModel by activityViewModels {
        ProfileViewModel.Factory
    }

    private lateinit var binding: FragmentProfileBinding

    val app by lazy {
        requireActivity().application as ScholarPlayApplication
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val user = app.getToken()

        profileViewModel.getUser(user)

        observeUser()





        binding.logoutLinearLayout.setOnClickListener {

            app.saveId("")
            app.saveAuthToken("")
            it.findNavController().navigate(R.id.action_profileFragment_to_nav_graph)
        }
    }

    private fun observeUser(){
        profileViewModel.userData.observe(viewLifecycleOwner){
            binding.nameTextView.text = it.user.name
            binding.roleTextView.text = it.user.status

            Glide
                .with(this)
                .load(it.user.avatar)
                .into(binding.pfpImageView)
        }
    }

}
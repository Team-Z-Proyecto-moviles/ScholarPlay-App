package com.scholar.scholarplay.ui.profile

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.scholar.scholarplay.R
import com.scholar.scholarplay.ScholarPlayApplication
import com.scholar.scholarplay.databinding.FragmentProfileBinding
import com.scholar.scholarplay.ui.profile.viewmodel.ProfileViewModel

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

        binding.editImageView.setOnClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_updateProfileFragment)
        }

        val user = app.getToken()

        profileViewModel.getUser(user)

        observeUser()







        binding.logoutLinearLayout.setOnClickListener {

            app.saveId("")
            app.saveAuthToken("")
            app.saveRole("")
            it.findNavController().navigate(R.id.action_profileFragment_to_nav_graph)
        }
    }

    private fun observeUser(){
        profileViewModel.userData.observe(viewLifecycleOwner){
            binding.nameTextView.text = it.user.name
            binding.roleTextView.text = it.user.status

            Log.d("AAA!!", it.user.avatar)
            Log.d("AAA!!", it.user.name)

            Glide
                .with(this)
                .load(it.user.avatar)
                .into(binding.pfpImageView)
        }
    }

}
package com.example.scholarplay.ui.updateprofile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.scholarplay.R
import com.example.scholarplay.databinding.FragmentUpdateProfileBinding
import com.example.scholarplay.ui.profile.viewmodel.ProfileViewModel

class UpdateProfileFragment : Fragment() {

    private val profileViewModel : ProfileViewModel by activityViewModels {
        ProfileViewModel.Factory
    }

    private lateinit var binding: FragmentUpdateProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       binding = FragmentUpdateProfileBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setAvatar()

        setViewModel()

        binding.linearLayout.setOnClickListener {
            findNavController().navigate(R.id.action_updateProfileFragment_to_avatarFragment)
        }


    }

    private fun setViewModel(){
        binding.viewmodel = profileViewModel
    }

    private fun setAvatar(){
     profileViewModel.userData.observe(viewLifecycleOwner){
         Glide
             .with(this)
             .load(it.user.avatar)
             .into(binding.pfpImageView)
     }
    }

}
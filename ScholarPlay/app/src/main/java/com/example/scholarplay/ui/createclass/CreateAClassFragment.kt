package com.example.scholarplay.ui.createclass

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.scholarplay.R
import com.example.scholarplay.ScholarPlayApplication
import com.example.scholarplay.databinding.FragmentCreateAClassBinding
import com.example.scholarplay.network.retrofit.RetrofitInstance
import com.example.scholarplay.ui.createclass.viewmodel.CreateClassViewModel


class CreateAClassFragment : Fragment() {

    private val createClassViewModel: CreateClassViewModel by activityViewModels {
        CreateClassViewModel.Factory
    }

    private lateinit var binding: FragmentCreateAClassBinding

    val app by lazy {
        requireActivity().application as ScholarPlayApplication
    }




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCreateAClassBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val url = RetrofitInstance.getApiUrl()

        binding.addImageCard.setOnClickListener {

            var popup = WallpaperPopUp()

            popup.show(childFragmentManager,"customPop")
        }

        createClassViewModel.image.observe(viewLifecycleOwner){
            when(it){
                url + "math_wallpaper.png" -> binding.wallpaperImageView.setImageResource(R.drawable.math_wallpaper)
                url + "sciene_wallpaper.png" -> binding.wallpaperImageView.setImageResource(R.drawable.sciene_wallpaper)
                url + "geography_wallpaper.png" -> binding.wallpaperImageView.setImageResource(R.drawable.geography_wallpaper)
                url + "code_wallpaper.png" -> binding.wallpaperImageView.setImageResource(R.drawable.code_wallpaper)
                url + "chemestry_wallpaper.png" -> binding.wallpaperImageView.setImageResource(R.drawable.chemestry_wallpaper)
            }
        }

        binding.createButton.setOnClickListener {
            createListener()
        }
    }

    private fun createListener(){

        val teacher = app.getId()
        val name = binding.ClassnameEditText.text.toString()
        val section = binding.sectionInputText.text.toString()


        createClassViewModel.onCreateClass(name, teacher, section)
        createClassViewModel.clearData()
        findNavController().navigate(R.id.action_createAClassFragment_to_teacherHomeFragment)
    }

}
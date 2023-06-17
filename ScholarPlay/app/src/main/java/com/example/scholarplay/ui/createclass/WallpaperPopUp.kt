package com.example.scholarplay.ui.createclass

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels

import com.example.scholarplay.databinding.FragmentAddWallpaperBinding
import com.example.scholarplay.ui.createclass.viewmodel.CreateClassViewModel

class WallpaperPopUp: DialogFragment() {

    private val createClassViewModel : CreateClassViewModel by activityViewModels {
        CreateClassViewModel.Factory
    }

    private lateinit var binding: FragmentAddWallpaperBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddWallpaperBinding.inflate(inflater,container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.wallpaperGroup.setOnCheckedChangeListener { _, _ ->
            binding.joinButton.isEnabled = isSelected()
        }

        binding.joinButton.isEnabled = false

        binding.joinButton.setOnClickListener {

            val selectedID = binding.wallpaperGroup.checkedRadioButtonId
            val radio = view.findViewById<RadioButton>(selectedID)

            var result = radio.text.toString()?: ""


            createClassViewModel.getImage(result)

            dismiss()
        }
    }

    private fun isSelected() : Boolean {
        val checkedId = binding.wallpaperGroup.checkedRadioButtonId
        return checkedId != -1
    }




    override fun onResume() {
        super.onResume()
        dialog?.window?.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
    }



}
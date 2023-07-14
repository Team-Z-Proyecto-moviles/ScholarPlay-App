package com.example.scholarplay.ui.levelscore

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.example.scholarplay.databinding.FragmentLevelScoreBinding

class LevelScorePopUp: DialogFragment() {

    private val levelScoreViewModel: LevelScoreViewModel by activityViewModels {
        LevelScoreViewModel.Factory
    }

    private lateinit var binding: FragmentLevelScoreBinding



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLevelScoreBinding.inflate(inflater,container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setViewModel()
        binding.okButton.setOnClickListener {
            dismiss()
        }
    }

    private fun setViewModel(){
        binding.viewmodel = levelScoreViewModel
    }

    override fun onResume() {
        super.onResume()
        dialog?.window?.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

}
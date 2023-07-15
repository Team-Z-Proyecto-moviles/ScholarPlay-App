package com.scholar.scholarplay.ui.newlevel

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.scholar.scholarplay.R
import com.scholar.scholarplay.databinding.FragmentNewLevelBinding
import com.scholar.scholarplay.ui.newlevel.viewmodel.NewLevelViewModel

class NewLevelFragment : Fragment() {

    private val newLevelViewModel: NewLevelViewModel by activityViewModels {
        NewLevelViewModel.Factory
    }

    private lateinit var binding: FragmentNewLevelBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewLevelBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setViewModel()
        binding.button2.setOnClickListener {
            newLevelViewModel.onNewLevel()
            findNavController().navigate(R.id.action_newLevelFragment_to_teacherLevelMenuFragment)
        }
    }

    private fun setViewModel(){
        binding.viewmodel = newLevelViewModel
    }


}
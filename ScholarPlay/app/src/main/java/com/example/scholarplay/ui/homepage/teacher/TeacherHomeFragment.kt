package com.example.scholarplay.ui.homepage.teacher

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.scholarplay.R
import com.example.scholarplay.ScholarPlayApplication
import com.example.scholarplay.databinding.FragmentTeacherHomeBinding

class TeacherHomeFragment : Fragment() {

    private lateinit var binding: FragmentTeacherHomeBinding

    val app by lazy {
        requireActivity().application as ScholarPlayApplication
    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       binding = FragmentTeacherHomeBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setTokenOnView()

        binding.recyclerviewClasses.layoutManager = GridLayoutManager(view.context,2)
    }

    private fun setTokenOnView() {
        val tokenValue = app.getToken()
        binding.tokenTextView.text = tokenValue
    }




}
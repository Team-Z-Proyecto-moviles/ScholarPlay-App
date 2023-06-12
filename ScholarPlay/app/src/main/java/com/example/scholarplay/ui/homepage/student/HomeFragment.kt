package com.example.scholarplay.ui.homepage.student

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.scholarplay.R
import com.example.scholarplay.ScholarPlayApplication
import com.example.scholarplay.databinding.FragmentHomeBinding
import com.example.scholarplay.repository.CredentialsRepository
import com.example.scholarplay.ui.homepage.student.recyclerview.StudentHomePageAdapter
import com.example.scholarplay.ui.homepage.student.viewmodel.StudentHomeViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class HomeFragment : Fragment() {

    private val studentHomeViewModel: StudentHomeViewModel by activityViewModels {
        StudentHomeViewModel.Factory
    }

    private lateinit var binding: FragmentHomeBinding

    val app by lazy {
        requireActivity().application as ScholarPlayApplication
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val idStudent = app.getId()

        studentHomeViewModel.getClassRooms(idStudent)
        binding.recyclerviewClasses.layoutManager = GridLayoutManager(view.context,2)

        val adapter = StudentHomePageAdapter()

        binding.recyclerviewClasses.adapter = adapter

        studentHomeViewModel.classrooms.observe(viewLifecycleOwner){
            adapter.setData(it)
            Log.d("AAA!!", it.toString())
        }
       /* binding.recyclerviewClasses.layoutManager = GridLayoutManager(view.context,2)
        val adapter = StudentHomePageAdapter()

        binding.recyclerviewClasses.adapter = adapter

        adapter.setData(studentHomeViewModel.getClassRooms(idStudent))*/


        setTokenOnView()





    }

    private fun setTokenOnView() {
        val tokenValue = app.getToken()
        binding.tokenTextView.text = tokenValue
    }





}
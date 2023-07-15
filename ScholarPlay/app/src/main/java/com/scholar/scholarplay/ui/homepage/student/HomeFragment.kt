package com.scholar.scholarplay.ui.homepage.student

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import com.scholar.scholarplay.R
import com.scholar.scholarplay.ScholarPlayApplication
import com.scholar.scholarplay.data.models.ClassModel
import com.scholar.scholarplay.databinding.FragmentHomeBinding
import com.scholar.scholarplay.ui.homepage.student.recyclerview.StudentClassRoomAdapter
import com.scholar.scholarplay.ui.homepage.student.viewmodel.StudentHomeViewModel
import com.scholar.scholarplay.ui.levelmenu.student.viewmodel.LevelMenuViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class HomeFragment : Fragment(), StudentClassRoomAdapter.OnItemClickListener {

    private val studentHomeViewModel: StudentHomeViewModel by activityViewModels {
        StudentHomeViewModel.Factory
    }

    private val levelMenuViewModel : LevelMenuViewModel by activityViewModels {
        LevelMenuViewModel.Factory
    }

    private lateinit var binding: FragmentHomeBinding

    val app by lazy {
        requireActivity().application as ScholarPlayApplication
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val rv = binding.recyclerviewClasses

        rv.layoutManager = GridLayoutManager(context, 2)

        val adapter = StudentClassRoomAdapter(StudentClassRoomAdapter.ClassRoomComparator, this)

        val userValue = app.getId()

        lifecycleScope.launch {
            studentHomeViewModel.getClassRoom(userValue).collectLatest { data ->
                adapter.submitData(data)
            }
        }

        lifecycleScope.launch {
            adapter.loadStateFlow.collectLatest { loadStates ->
                binding.progressBar.isVisible =
                    loadStates.append is LoadState.Loading
                            || loadStates.prepend is LoadState.Loading
                            || loadStates.append is LoadState.Loading
                            || loadStates.refresh is LoadState.Loading
            }
        }

        rv.adapter = adapter

        binding.floatingActionButton.setOnClickListener {
            it.findNavController().navigate(R.id.action_homeFragment_to_joinAClassFragment)
        }
    }

    override fun onItemClick(classRoom: ClassModel) {
        levelMenuViewModel.setSelectedClass(classRoom)
        findNavController().navigate(R.id.action_homeFragment_to_levelMenuFragment)
    }
}
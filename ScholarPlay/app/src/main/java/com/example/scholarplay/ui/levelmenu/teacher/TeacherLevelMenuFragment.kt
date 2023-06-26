package com.example.scholarplay.ui.levelmenu.teacher

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.scholarplay.R
import com.example.scholarplay.databinding.FragmentTeacherLevelMenuBinding
import com.example.scholarplay.ui.levelmenu.student.recyclerview.LevelMenuAdapter
import com.example.scholarplay.ui.levelmenu.student.recyclerview.ZigZagItemDecoration
import com.example.scholarplay.ui.levelmenu.student.viewmodel.LevelMenuViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class TeacherLevelMenuFragment : Fragment() {

    private val levelMenuViewModel: LevelMenuViewModel by activityViewModels {
        LevelMenuViewModel.Factory
    }

    private lateinit var binding: FragmentTeacherLevelMenuBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTeacherLevelMenuBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.newLevelActionButtom.setOnClickListener {
            findNavController().navigate(R.id.action_teacherLevelMenuFragment_to_newLevelFragment)
        }

        val rv = binding.levelRecyclerView

        rv.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, true)

        rv.addItemDecoration(ZigZagItemDecoration())

        val adapter = LevelMenuAdapter(LevelMenuAdapter.LevelComparator)


        lifecycleScope.launch {
            levelMenuViewModel.getLevels().collectLatest { data ->
                adapter.submitData(data)
            }
        }

        lifecycleScope.launch {
            adapter.loadStateFlow.collectLatest { loadStates ->
                binding.progressBar4.isVisible =
                    loadStates.append is LoadState.Loading
                            || loadStates.prepend is LoadState.Loading
                            || loadStates.append is LoadState.Loading
                            || loadStates.refresh is LoadState.Loading
            }
        }

        rv.adapter = adapter



    }

}
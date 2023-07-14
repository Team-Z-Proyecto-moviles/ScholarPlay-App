package com.example.scholarplay.ui.levelmenu.student

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.scholarplay.R
import com.example.scholarplay.ScholarPlayApplication
import com.example.scholarplay.data.models.LevelModel
import com.example.scholarplay.databinding.FragmentLevelMenuBinding
import com.example.scholarplay.ui.createclass.WallpaperPopUp
import com.example.scholarplay.ui.levelmenu.student.recyclerview.LevelMenuAdapter
import com.example.scholarplay.ui.levelmenu.student.recyclerview.ZigZagItemDecoration
import com.example.scholarplay.ui.levelmenu.student.viewmodel.LevelMenuViewModel
import com.example.scholarplay.ui.levelscore.LevelScorePopUp
import com.example.scholarplay.ui.levelscore.LevelScoreViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class LevelMenuFragment : Fragment(), LevelMenuAdapter.OnItemClickListener {

    private val levelMenuViewModel : LevelMenuViewModel by activityViewModels {
        LevelMenuViewModel.Factory
    }
    private val levelScoreViewModel: LevelScoreViewModel by activityViewModels {
        LevelScoreViewModel.Factory
    }

    private lateinit var binding: FragmentLevelMenuBinding

    val app by lazy {
        requireActivity().application as ScholarPlayApplication
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLevelMenuBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val rv = binding.levelRecyclerView

        rv.layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,true)

        rv.addItemDecoration(ZigZagItemDecoration())

        val adapter = LevelMenuAdapter(LevelMenuAdapter.LevelComparator, this)



        lifecycleScope.launch {
            levelMenuViewModel.getLevels().collectLatest { data ->
                adapter.submitData(data)

                Log.d("AAA!!!", data.toString())
            }
        }

        lifecycleScope.launch {
            adapter.loadStateFlow.collectLatest { loadStates ->
                binding.progressBar3.isVisible =
                    loadStates.append is LoadState.Loading
                            || loadStates.prepend is LoadState.Loading
                            || loadStates.append is LoadState.Loading
                            || loadStates.refresh is LoadState.Loading
            }
        }

        rv.adapter = adapter




    }

    override fun OnItemClick(Level: LevelModel) {
        levelScoreViewModel.setSelectedClass(Level)
        LevelScorePopUp().show(childFragmentManager,"scorePopUp")

    }


}
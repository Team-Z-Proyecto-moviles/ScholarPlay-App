package com.scholar.scholarplay.ui.joinclass

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.scholar.scholarplay.R
import com.scholar.scholarplay.ScholarPlayApplication
import com.scholar.scholarplay.databinding.FragmentJoinAClassBinding
import com.scholar.scholarplay.ui.joinclass.viewmodel.JoinAClassViewModel


class JoinAClassFragment : Fragment() {

    private val joinAClassViewModel: JoinAClassViewModel by activityViewModels {
        JoinAClassViewModel.Factory
    }

    private lateinit var binding: FragmentJoinAClassBinding

    val app by lazy {
        requireActivity().application as ScholarPlayApplication
    }








    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentJoinAClassBinding.inflate(inflater, container, false)
        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        binding.joinButton.setOnClickListener {
           joinListener()
        }

        observeStatus()

    }

    private fun joinListener(){
        val student = app.getId()
        val code = binding.classcodeText.text.toString()

        joinAClassViewModel.joinAClass(student, code)
    }

    private fun observeStatus() {
        joinAClassViewModel.status.observe(viewLifecycleOwner){ status ->
            handleStatus(status)
        }
    }

    private fun handleStatus(uiStatus: JoinUiStatus ){
        when(uiStatus){
            is JoinUiStatus.Error ->{
                Toast.makeText(requireContext(),"An error has ocurred", Toast.LENGTH_SHORT)
            }

            is JoinUiStatus.ErrorWithMessage -> {
                Toast.makeText(requireContext(), uiStatus.message, Toast.LENGTH_SHORT)
            }

            is JoinUiStatus.Success -> {
                joinAClassViewModel.clearStatus()
                joinAClassViewModel.clearData()

                findNavController().navigate(R.id.action_joinAClassFragment_to_homeFragment)
            }

            else -> {}
        }
    }

}
package com.example.scholarplay.ui.avatar

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import android.widget.RadioButton
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.scholarplay.R
import com.example.scholarplay.ScholarPlayApplication
import com.example.scholarplay.databinding.FragmentAvatarBinding
import com.example.scholarplay.databinding.FragmentTeacherAvatarBinding
import com.example.scholarplay.network.retrofit.RetrofitInstance

class TeacherAvatarFragment : Fragment() {

    private val avatarViewModel : AvatarViewModel by activityViewModels {
        AvatarViewModel.Factory
    }

    private lateinit var bindig: FragmentTeacherAvatarBinding

    val app by lazy {
        requireActivity().application as ScholarPlayApplication
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bindig = FragmentTeacherAvatarBinding.inflate(inflater, container, false)
        return bindig.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val teacher = app.getId()

        val url = RetrofitInstance.getApiUrl()

        val gridLayout: GridLayout = bindig.gridLayout

        val radioButtons = mutableListOf<RadioButton>()

        for (i in 0 until gridLayout.childCount) {
            val view: View = gridLayout.getChildAt(i)
            if (view is RadioButton) {
                val radioButton = view as RadioButton
                radioButtons.add(radioButton)
                radioButton.setOnClickListener {
                    for (j in 0 until radioButtons.size){
                        if (radioButtons[j] != radioButton){
                            radioButtons[j].isChecked = false
                        }
                    }
                    bindig.selectButton.isEnabled = true
                }

            }
        }

        bindig.selectButton.setOnClickListener {

            val selected = radioButtons.firstOrNull { it.isChecked }

            val avatar = url + selected?.text

            avatarViewModel.updateTeacherAvatar(teacher, avatar)

            findNavController().navigate(R.id.action_teacherAvatarFragment_to_profileFragment)




        }
    }




}
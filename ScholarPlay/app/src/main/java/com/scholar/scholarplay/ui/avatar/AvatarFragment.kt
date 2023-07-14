package com.scholar.scholarplay.ui.avatar

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import android.widget.RadioButton
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.scholar.scholarplay.R
import com.scholar.scholarplay.ScholarPlayApplication
import com.scholar.scholarplay.databinding.FragmentAvatarBinding
import com.scholar.scholarplay.network.retrofit.RetrofitInstance


class AvatarFragment : Fragment() {

    private val avatarViewModel : AvatarViewModel by activityViewModels {
        AvatarViewModel.Factory
    }

    private lateinit var bindig: FragmentAvatarBinding

    val app by lazy {
        requireActivity().application as ScholarPlayApplication
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       bindig = FragmentAvatarBinding.inflate(inflater, container, false)
        return bindig.root



    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val user = app.getId()

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

            avatarViewModel.updateAvatar(user, avatar)

            findNavController().navigate(R.id.action_avatarFragment_to_profileFragment)




        }
    }


}
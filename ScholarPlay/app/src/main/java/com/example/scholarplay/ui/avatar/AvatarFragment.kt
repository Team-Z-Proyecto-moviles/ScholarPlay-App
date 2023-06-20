package com.example.scholarplay.ui.avatar

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import android.widget.RadioButton
import android.widget.Toast
import com.example.scholarplay.R
import com.example.scholarplay.databinding.FragmentAvatarBinding


class AvatarFragment : Fragment() {

    private lateinit var bindig: FragmentAvatarBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       bindig = FragmentAvatarBinding.inflate(inflater, container, false)
        return bindig.root



    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


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

            Toast.makeText(this.context, "Selected: ${selected?.text}", Toast.LENGTH_SHORT).show()

        }
    }
}
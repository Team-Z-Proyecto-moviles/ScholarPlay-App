package com.example.scholarplay.ui.register

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.scholarplay.R
import com.example.scholarplay.databinding.FragmentRegisterBinding
import com.example.scholarplay.ui.register.viewmodel.RegisterViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class RegisterFragment : Fragment() {

    private val registerViewModel : RegisterViewModel by activityViewModels {
        RegisterViewModel.Factory
    }

    private lateinit var binding: FragmentRegisterBinding



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        setViewmodel()
        observeStatus()

        CoroutineScope(Dispatchers.Main).launch {
            getSpinnerLaunch()
        }



        binding.goToLoginTextView.setOnClickListener {
            it.findNavController().navigate(R.id.action_registerFragment_to_loginFragment3)
        }

    }

    private fun setViewmodel(){

        binding.viewmodel = registerViewModel

    }

    private fun observeStatus(){
        registerViewModel.status.observe(viewLifecycleOwner){ status ->
            handleUiStatus(status)

        }
    }

    private suspend fun getSpinnerLaunch(){
        setSpinner()
    }

    private fun setSpinner(){
        val spinner: Spinner = binding.occupationSpinner
        ArrayAdapter.createFromResource(requireContext(), R.array.ocuupations_arrat, R.layout.spinner_item).also { adapter ->
            adapter.setDropDownViewResource(R.layout.spinner_item)
            spinner.adapter = adapter
        }

        binding.occupationSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                registerViewModel.getOccupation(p2)
                Log.d(p2.toString(),"AAAPLIS")
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                //
            }
        }
    }

    private fun handleUiStatus(status: RegisterUiStatus){
        when(status){
            is RegisterUiStatus.Error -> {
                Toast.makeText(requireContext(), "An error has ocurred", Toast.LENGTH_SHORT).show()
            }

            is RegisterUiStatus.ErrorWithMessage -> {
                Toast.makeText(requireContext(), status.message, Toast.LENGTH_SHORT).show()
            }
            is RegisterUiStatus.Success -> {
                registerViewModel.clearStatus()
                registerViewModel.clearData()
                findNavController().navigate(R.id.action_registerFragment_to_loginFragment3)
            }

            else -> {}
        }
    }


}
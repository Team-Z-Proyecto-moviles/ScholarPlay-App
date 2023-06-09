package com.example.scholarplay.ui.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.scholarplay.R
import com.example.scholarplay.ScholarPlayApplication
import com.example.scholarplay.databinding.FragmentLoginBinding
import com.example.scholarplay.ui.login.viewmodel.LoginViewmodel

class LoginFragment : Fragment() {

    private val loginViewmodel : LoginViewmodel by activityViewModels {
        LoginViewmodel.Factory
    }

    private lateinit var binding: FragmentLoginBinding

    val app by lazy {
        requireActivity().application as ScholarPlayApplication
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setViewModel()
        observeStatus()

    }

    private fun setViewModel(){
        binding.viewmodel = loginViewmodel
    }

    private fun observeStatus() {
        loginViewmodel.status.observe(viewLifecycleOwner) { status ->
            handleUiStatus(status)
        }
    }

    private fun handleUiStatus(status: LoginUiStatus) {
        when(status) {
            is LoginUiStatus.Error -> {
                Toast.makeText(requireContext(), "An error has ocurred", Toast.LENGTH_SHORT).show()
            }

            is LoginUiStatus.ErrorWithMessage -> {
                Toast.makeText(requireContext(), status.message, Toast.LENGTH_SHORT).show()

            }

            is LoginUiStatus.Success -> {
                loginViewmodel.clearStatus()
                loginViewmodel.clearData()
                app.saveAuthToken(status.token)
                findNavController().navigate(R.id.action_loginFragment3_to_homeFragment2)
            }

            else -> {}
        }
    }


}
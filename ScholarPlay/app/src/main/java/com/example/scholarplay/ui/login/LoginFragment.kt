package com.example.scholarplay.ui.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
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

        binding.createAccountTextView.setOnClickListener {
            it.findNavController().navigate(R.id.action_loginFragment3_to_registerFragment)
        }

    }

    private fun setViewModel(){
        binding.viewmodel = loginViewmodel
    }

    private fun observeStatus() {
        loginViewmodel.status.observe(viewLifecycleOwner) { status ->
            handleUiStatus(status)
        }
    }


    private fun handleUiStatus(uiStatus: LoginUiStatus) {
        when(uiStatus) {
            is LoginUiStatus.Error -> {
                Toast.makeText(requireContext(), "An error has ocurred", Toast.LENGTH_SHORT).show()
            }

            is LoginUiStatus.ErrorWithMessage -> {
                Toast.makeText(requireContext(), uiStatus.message, Toast.LENGTH_SHORT).show()

            }

            is LoginUiStatus.Success -> {
                loginViewmodel.clearStatus()
                loginViewmodel.clearData()
                app.saveAuthToken(uiStatus.token)
                loginViewmodel.getUser(uiStatus.token)
            }

            is LoginUiStatus.Succes2 -> {
                loginViewmodel.clearStatus()
                app.saveId(uiStatus.user_data.user.id)

                if (uiStatus.user_data.user.status == "student"){
                    findNavController().navigate(R.id.action_loginFragment3_to_studentActivity2)
                }

                if (uiStatus.user_data.user.status == "teacher"){
                    findNavController().navigate(R.id.action_loginFragment3_to_teacherHomeFragment)
                }
            }

            else -> {}
        }
    }


}
package com.example.themovie.presentation.auth.forgot

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.themovie.R
import com.example.themovie.databinding.FragmentForgotBinding
import com.example.themovie.util.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ForgotFragment : Fragment() {
    private var _binding: FragmentForgotBinding? = null
    private val binding get() = _binding!!

    private val forgotViewModel: ForgotViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentForgotBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolbar(toolbar = binding.toolbar)

        initListener()
    }

    private fun initListener(){
        binding.btnForgot.setOnClickListener {
            validateData()
        }
    }

    private fun validateData(){
        val email = binding.etEmail.text.toString()

        if (email.isEmailValid()){
            hideKeyboard()
            forgotUser(email)
        }else{
            showSnackBar(message = R.string.invalid_email_register_fragment)
        }
    }

    private fun forgotUser(email: String){
        forgotViewModel.forgot(email).observe(viewLifecycleOwner){
            when(it){
                is StateView.Loading -> {
                    binding.progressbar.isVisible = true
                }
                is StateView.Success -> {
                    showSnackBar(message = R.string.text_send_email_success_forgot_fragment)
                }
                is StateView.Error -> {
                    binding.progressbar.isVisible = false

                    showSnackBar(message = FirebaseHelper.validError(it.message ?: ""))
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
package com.example.themovie.presenter.auth.login

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.themovie.R
import com.example.themovie.databinding.FragmentLoginBinding
import com.example.themovie.presenter.main.activity.MainActivity
import com.example.themovie.util.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private val loginViewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolbar(toolbar = binding.toolbar)

        initListener()
    }

    private fun initListener(){
        binding.btnLogin.setOnClickListener {
            validateData()
        }

        binding.btnForgotPassword.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_forgotFragment)
        }


        Glide
            .with(requireContext())
            .load(R.drawable.loading)
            .into(binding.progressbar)
    }

    private fun validateData(){
        val email = binding.etEmail.text.toString()
        val password = binding.etPassword.text.toString()

        if(email.isEmailValid()){
            if (password.isNotEmpty()){
                hideKeyboard()
                loginUser(email, password)
            }else{
                showSnackBar(message = R.string.text_password_empty_login_fragment)
            }
        }else{
            showSnackBar(R.string.text_email_empty_login_fragment)
        }
    }

    private fun loginUser(email: String, password: String){
        loginViewModel.login(email, password).observe(viewLifecycleOwner){
            when(it){
                is StateView.Loading -> {
                    binding.progressbar.isVisible = true
                }
                is StateView.Success -> {
                    startActivity(Intent(requireContext(), MainActivity::class.java))
                    requireActivity().finish()
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
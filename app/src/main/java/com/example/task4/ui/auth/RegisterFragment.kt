package com.example.task4.ui.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.example.task4.R
import com.example.task4.databinding.FragmentRegisterBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class RegisterFragment : Fragment() {

    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!
//para validação com senha
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentRegisterBinding.inflate(layoutInflater, container,false)
        return binding.root
    }
    //pra validação com senha
    override fun onViewCreated (view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)
        auth = Firebase.auth
        initClicks()
    }
    //pra validação com senha
    private fun initClicks(){
        binding.btnRegister.setOnClickListener{ValidadeData()}
    }

    //função pra validar email e senha no RegisterFragment
    private fun ValidadeData(){
        val email = binding.edtEmail.text.toString().trim()
        val password = binding.edtPassword.text.toString().trim()

        if (email.isNotEmpty()){
            if (password.isNotEmpty()){
                binding.progressbar.isVisible = true
            }
            else{
                Toast.makeText(requireContext(), "Informe sua senha", Toast.LENGTH_SHORT).show()
            }
        }else{
            Toast.makeText(requireContext(), "Informe seu e-mail.", Toast.LENGTH_LONG).show()
        }
    }
    private fun registerUser(email:String,password:String) {
    auth.createUserWithEmailAndPassword(email, password)
        .addOnCompleteListener(requireActivity()) { task ->
            if (task.isSuccessful) {
                findNavController().navigate(R.id.action_global_homeFragment)
            } else {
                //acontece erro
                binding.progressbar.isVisible = false
            }
        }
}

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
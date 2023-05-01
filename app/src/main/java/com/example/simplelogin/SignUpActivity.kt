package com.example.simplelogin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.simplelogin.databinding.ActivitySignUpBinding
import com.google.firebase.auth.FirebaseAuth
import java.lang.Exception

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private var fileLogger: CustomFileLogger = CustomFileLogger()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        firebaseAuth = FirebaseAuth.getInstance()
        initUI()
    }

    private fun initUI(){
        binding.tvSignIn.setOnClickListener {
            val intent = Intent(this,SignInActivity::class.java)
            startActivity(intent)
        }

        binding.fabBackSignUp.setOnClickListener {
            val intent = Intent(this,SignInActivity::class.java)
            startActivity(intent)
        }

        binding.btnSignUp.setOnClickListener {
            val email = binding.etEmelSignUp.text.toString()
            val pass = binding.etPasswordSignUp.text.toString()
            val passRe = binding.etPasswordSignUpRe.text.toString()

            if (email.isNotEmpty() && pass.isNotEmpty() && passRe.isNotEmpty()){
                if(pass == passRe){
                        firebaseAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener {
                            if (it.isSuccessful){
                                val intent = Intent(this,SignInActivity::class.java)
                                startActivity(intent)
                            }
                            else{
                                fileLogger.logError(it.exception.toString())
                                Toast.makeText(this,"Failed to create account", Toast.LENGTH_SHORT).show()
                            }
                        }
                }else {
                    Toast.makeText(this,"Password is not matching", Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(this,"Empty field is not allowed", Toast.LENGTH_SHORT).show()
            }
        }

    }
}
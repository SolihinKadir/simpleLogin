package com.example.simplelogin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
//import androidx.compose.ui.focus.DefaultFocusProperties.start
import com.example.simplelogin.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import java.lang.Exception
import kotlin.system.exitProcess
//import com.example.simplelogin.FileLogger

 class SignInActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private var fileLogger: CustomFileLogger = CustomFileLogger()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        firebaseAuth = FirebaseAuth.getInstance()

        initUI()
    }

    private fun initUI(){

        binding.fabBack.setOnClickListener {
            moveTaskToBack(true)
            exitProcess(-1)
        }

        binding.btnSignIn.setOnClickListener {
            val email = binding.etEmel.text.toString()
            val pass = binding.etPassword.text.toString()

            if (email.isNotEmpty() && pass.isNotEmpty()){
                firebaseAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener {
                    if(it.isSuccessful){
                        val intent = Intent(this, MainPageActivity::class.java)
                        startActivity(intent)
                    }else{
                        fileLogger.logError(it.exception.toString())
                        Toast.makeText(this, "Invalid email or password", Toast.LENGTH_SHORT).show()
                    }
                }
            }else{
                Toast.makeText(this,"Empty field is not allowed", Toast.LENGTH_SHORT).show()
            }
        }

        binding.tvSignUp.setOnClickListener {
            val intent = Intent(this,SignUpActivity::class.java)
            startActivity(intent)
        }
    }


}
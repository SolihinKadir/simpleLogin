package com.example.simplelogin

import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await
import java.lang.Exception

interface Authenticator {

      fun  signInWithEmailAndPassword(email:String, password:String)
      fun  signUpCreateUserWithEmailAndPassword(email:String, password:String)

}

class FirebaseAuthenticator:Authenticator{
    private var firebaseAuth:FirebaseAuth = FirebaseAuth.getInstance()

    override fun signInWithEmailAndPassword(email: String, password: String) {
        firebaseAuth.signInWithEmailAndPassword(email, password)
    }


    override fun signUpCreateUserWithEmailAndPassword(email: String, password: String) {
        firebaseAuth.createUserWithEmailAndPassword(email, password)
    }
}
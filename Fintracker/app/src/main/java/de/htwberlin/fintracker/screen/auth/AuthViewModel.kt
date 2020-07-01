package de.htwberlin.fintracker.screen.auth

import android.view.View
import androidx.lifecycle.ViewModel
import de.htwberlin.fintracker.data.repositories.UserRepository

class AuthViewModel : ViewModel() {

    // Getting the email and the password from the UI
    var email: String? = null
    var password: String? = null

    var authListener: AuthListener? = null
    fun onLoginButtonClick(view: View){

        // when login button clicked, onStarted() from AuthListener will be called
        authListener?.onStarted()
        if(email.isNullOrEmpty() || password.isNullOrEmpty()){
            authListener?.onFailure("Invalid email or password")
            return

        }

        //Bad Practice (13:55)
        val loginResponse = UserRepository().userLogin(email!!, password!!)
        // Authenticated
        authListener?.onSuccess(loginResponse)

    }
}
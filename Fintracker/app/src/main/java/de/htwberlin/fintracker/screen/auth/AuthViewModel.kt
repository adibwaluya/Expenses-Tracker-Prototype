package de.htwberlin.fintracker.screen.auth

import android.view.View
import androidx.lifecycle.ViewModel
import de.htwberlin.fintracker.data.repositories.UserRepository
import de.htwberlin.fintracker.util.Coroutines

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

        Coroutines.main {
            // TODO: This will be fixed using dependency injection
            // type of this response is Response of type AuthResponse
            val response = UserRepository().userLogin(email!!, password!!)
            if(response.isSuccessful){
                // Authenticated
                authListener?.onSuccess(response.body()?.user!!)
            }

            // TODO: Because of Coroutines, failure needs to be fixed!
            else{
                authListener?.onFailure("Error code: ${response.code()}")
            }
        }

    }
}
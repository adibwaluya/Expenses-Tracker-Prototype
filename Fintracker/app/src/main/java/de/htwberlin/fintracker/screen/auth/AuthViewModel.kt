package de.htwberlin.fintracker.screen.auth

import android.view.View
import androidx.lifecycle.ViewModel
import de.htwberlin.fintracker.data.repositories.UserRepository
import de.htwberlin.fintracker.util.ApiExceptions
import de.htwberlin.fintracker.util.Coroutines
import de.htwberlin.fintracker.util.NoInternetException

class AuthViewModel (
        private val repository: UserRepository
) : ViewModel() {

    // Getting the email and the password from the UI
    var email: String? = null
    var password: String? = null

    var authListener: AuthListener? = null

    // Observe user changes in the local db
    fun getLoggedInUser() = repository.getUser()

    fun onLoginButtonClick(view: View){

        // when login button clicked, onStarted() from AuthListener will be called
        authListener?.onStarted()
        if(email.isNullOrEmpty() || password.isNullOrEmpty()){
            authListener?.onFailure("Invalid email or password")
            return

        }

        Coroutines.main {

            try{

                val authResponse = repository.userLogin(email!!, password!!)

                authResponse.user?.let {
                    // Authenticated
                    authListener?.onSuccess(it)

                    // When the response is successful, the user will be saved to the local db
                    repository.saveUser(it)

                    // so that onFailure process below will not be executed
                    return@main
                }
                // in case the user is null
                authListener?.onFailure(authResponse.message!!)
            }
            catch (e: ApiExceptions){
                authListener?.onFailure(e.message!!)
            }
            catch (e: NoInternetException){
                authListener?.onFailure(e.message!!)
            }

        }

    }

}
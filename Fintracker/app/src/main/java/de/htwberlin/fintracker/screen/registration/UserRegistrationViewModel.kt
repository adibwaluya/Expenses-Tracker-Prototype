package de.htwberlin.fintracker.screen.registration

import android.accounts.NetworkErrorException
import android.view.View
import androidx.lifecycle.ViewModel
import de.htwberlin.fintracker.data.repositories.UserRepository
import de.htwberlin.fintracker.screen.auth.AuthListener
import de.htwberlin.fintracker.util.ApiExceptions
import de.htwberlin.fintracker.util.Coroutines

class UserRegistrationViewModel(
    private val repository: UserRepository
): ViewModel() {

    var name: String? = null
    var email: String? = null
    var password: String? = null
    var passwordConfirm: String? = null

    var authListener: AuthListener? = null
    fun SignedUpUser() = repository.getUser()

    fun onSignUpButton(view: View){

        authListener?.onStarted()

        if(name.isNullOrEmpty()){
            authListener?.onFailure("Name is required")
            return
        }
        if(email.isNullOrEmpty()){
            authListener?.onFailure("email is required")
            return
        }
        if(password.isNullOrEmpty()){
            authListener?.onFailure("password is required")
            return
        }
        if(password != passwordConfirm){
            authListener?.onFailure("The password does not match!")
            return
        }

        Coroutines.main {
            try {

                val authResponse = repository.userSignup(name!!, email!!, password!!)

                authResponse.user?.let {
                    authListener?.onSuccess(it)
                    repository.saveUser(it)
                    return@main
                }

                authListener?.onFailure(authResponse.message!!)
            }
            catch(e: ApiExceptions){
                authListener?.onFailure(e.message!!)
            }
            catch (e: NetworkErrorException){
                authListener?.onFailure(e.message!!)
            }
        }
    }
}
package de.htwberlin.fintracker.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import de.htwberlin.fintracker.data.network.MyApi
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// Required so we can communicate with the user repo from AuthViewModel
class UserRepository {
    // Initiate the actual login
    fun userLogin(email: String, password: String) : LiveData<String>{

        // The response which will be obtained from the API will be stored here
        val loginResponse = MutableLiveData<String>()

        //TODO: REVISED! BAD PRACTICE! Probably implementing COROUTINES!
        // Creating instance of the API inside this class means that our class
        // will dependent on MyApi
        // Instead: we need to inject it (dependency injection)
        MyApi().userLogin(email, password)
                .enqueue(object: Callback<ResponseBody>{
                    override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                        loginResponse.value = t.message
                    }

                    override fun onResponse(
                            call: Call<ResponseBody>,
                            response: Response<ResponseBody>) {

                        if(response.isSuccessful){
                            loginResponse.value = response.body()?.string()
                        } else{
                            loginResponse.value = response.errorBody()?.string()
                        }
                    }
                })

        // returning liveData
        return loginResponse
    }
}
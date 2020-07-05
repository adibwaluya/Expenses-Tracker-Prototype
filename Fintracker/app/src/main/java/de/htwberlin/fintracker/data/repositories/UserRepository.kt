package de.htwberlin.fintracker.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import de.htwberlin.fintracker.data.db.entities.AppDatabase
import de.htwberlin.fintracker.data.db.entities.User
import de.htwberlin.fintracker.data.network.MyApi
import de.htwberlin.fintracker.data.network.SafeApiRequest
import de.htwberlin.fintracker.data.network.responses.AuthResponse
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// Required so we can communicate with the user repo from AuthViewModel
class UserRepository(
        // constructor parameters (constructor injections)
        private val api: MyApi,
        private val db: AppDatabase
): SafeApiRequest() {

    // Initiate the actual login
    // this function will be called in viewModel
    suspend fun userLogin(email: String, password: String) :AuthResponse{

        // Suspend fun should be either called by suspend fun or coroutine fun!
        return apiRequest { api.userLogin(email, password) }

    }

    suspend fun userSignup(
        name: String,
        email: String,
        password: String
    ) : AuthResponse{

        return apiRequest { api.userSignUp(name, email, password) }
    }

    // perform db operations and save the user asynchronously
    suspend fun saveUser(user: User) = db.getUserDao().upsert(user)

    // this function will return the user which is saved in local db
    // this will give the LiveData which can be observed
    fun getUser() = db.getUserDao().getUser()
}

/**
 * // The response which will be obtained from the API will be stored here
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

 **/
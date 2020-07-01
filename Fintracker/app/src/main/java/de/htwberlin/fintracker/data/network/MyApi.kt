package de.htwberlin.fintracker.data.network

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface MyApi {

    @FormUrlEncoded
    @POST("userlogin")
    /** This fun cannot be called from LoginActivity because the architecture pattern stated
     *  that LoginActivity will only communicate with ViewModel.
     *  Email and password will be sent to the viewModel and the viewModel will interact with
     *  the repository to send the email and password to back-end server
     */
    fun userLogin(
            @Field("email") email: String,
            @Field("password") password: String
    ) : Call<ResponseBody>

    companion object{

        operator fun invoke(): MyApi {
            return Retrofit.Builder()
                    .baseUrl("http://192.168.43.251/MyApi/public/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(MyApi::class.java)
        }
    }
}
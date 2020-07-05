package de.htwberlin.fintracker.data.network

import de.htwberlin.fintracker.data.network.responses.AuthResponse
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
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
     *
     *  Suspending functions are the center of everything in coroutines!
     *  A suspending function is simply a function that can be paused and resumed at later time
     *  This type of function can execute a long running operation and wait for it to complete
     *  without blocking
     *
     *  TODO: Don't forget to apply suspend fun as well in UserRepository!
     */
    suspend fun userLogin(
            @Field("email") email: String,
            @Field("password") password: String
    ) : Response<AuthResponse>

    @FormUrlEncoded
    @POST("createuser")
    suspend fun userSignup(
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("password") password: String
    ) : Response<AuthResponse>

    companion object{

        operator fun invoke(
            // Network connection interceptor as context
            networkConnectionInterceptor: NetworkConnectionInterceptor
        ): MyApi {

            val okkHttpClient = OkHttpClient.Builder()
                .addInterceptor(networkConnectionInterceptor)
                .build()

            return Retrofit.Builder()
                .client(okkHttpClient)
                .baseUrl("http://192.168.43.251/MyApi/public/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MyApi::class.java)
        }
    }
}
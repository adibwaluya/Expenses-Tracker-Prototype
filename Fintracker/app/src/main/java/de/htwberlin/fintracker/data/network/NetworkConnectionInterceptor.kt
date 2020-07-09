package de.htwberlin.fintracker.data.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import de.htwberlin.fintracker.util.NoInternetException
import okhttp3.Interceptor
import okhttp3.Response

class NetworkConnectionInterceptor(
    context: Context
): Interceptor {

    private val applicationContext = context.applicationContext
    override fun intercept(chain: Interceptor.Chain): Response {

        if (!isInternetAvailable())
            throw NoInternetException("Make sure you are connected to the internet")

        return chain.proceed(chain.request())

    }

    private fun isInternetAvailable() : Boolean{

        // Store the result whether the network is available or not
        var result = false

        // Context is required
        // Connectivity manager is also required
        val connectivityManager =
            applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        connectivityManager?.let {
            it.getNetworkCapabilities(connectivityManager.activeNetwork)?.apply {
                result = when{
                    hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                    hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                    else -> false
                }
            }
        }
        return result
    }
}
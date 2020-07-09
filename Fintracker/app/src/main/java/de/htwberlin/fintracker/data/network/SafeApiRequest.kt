package de.htwberlin.fintracker.data.network

import de.htwberlin.fintracker.util.ApiExceptions
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Response

// Perform API request
// TODO: Implement in UserRepository
abstract class SafeApiRequest{

    // network operation is an asynchronous operation
    // Generic function
    suspend fun<T: Any> apiRequest(call: suspend () -> Response<T>) : T {
        val response = call.invoke()

        if (response.isSuccessful) {
            return response.body()!!
        } else {
            val error = response.errorBody()?.string()
            val message = StringBuilder()

            // Before validating the JSON, check if this error is not null
            error?.let {
                try {

                    // if we have JSON response (designed in my API code) in error response
                    // convert error string to a JSON object (only for JSON object)
                    message.append(JSONObject(it).getString("message"))
                } catch (e: JSONException) {

                    // when there's no JSON response (404, API not found, host is not working)
                }
                message.append("\n")
            }
            message.append("Error code: ${response.code()}")
            throw ApiExceptions(toString())
        }
    }
}
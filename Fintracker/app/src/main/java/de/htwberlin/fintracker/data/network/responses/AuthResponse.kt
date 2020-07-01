package de.htwberlin.fintracker.data.network.responses

import de.htwberlin.fintracker.data.db.entities.User

/**
 * Package responses will be used to store all the classes that will be used
 * to map the JSON response into kotlin classes
 *
 * The result that you see in the Postman is basically JSON Object, dib!
 * */
data class AuthResponse (
    val isSuccessful: Boolean?,
    val message: String?,
    val user: User?
){
}
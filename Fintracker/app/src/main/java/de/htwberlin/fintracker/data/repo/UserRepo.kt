package de.htwberlin.fintracker.data.repo

import de.htwberlin.fintracker.data.firebase.FirebaseSource

class UserRepo (private val firebase: FirebaseSource){

    fun login(email:String, password:String) = firebase.login(email, password)
    fun register(email: String, password: String) = firebase.register(email, password)
    fun currentUser() = firebase.currentUser()
    fun logout() = firebase.logout()
}
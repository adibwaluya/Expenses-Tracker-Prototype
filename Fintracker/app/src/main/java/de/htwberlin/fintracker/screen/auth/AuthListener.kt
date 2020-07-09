package de.htwberlin.fintracker.screen.auth

import androidx.lifecycle.LiveData
import de.htwberlin.fintracker.data.db.entities.User

// Callback from viewModel to Fragment/Activity (display error/sucess message)
interface AuthListener {

    // When network operations is ongoing, progressbar will be displayed
    fun onStarted()

    // Authentication successfull
    fun onSuccess(user: User)

    // When operation fails, the reason will be displayed
    fun onFailure(message: String)
}
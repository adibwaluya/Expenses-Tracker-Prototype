package de.htwberlin.fintracker.screen.registration

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import de.htwberlin.fintracker.data.repositories.UserRepository

class UserRegistrationViewModelFactory(
    private val repository: UserRepository
): ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return UserRegistrationViewModel(repository) as T
    }
}
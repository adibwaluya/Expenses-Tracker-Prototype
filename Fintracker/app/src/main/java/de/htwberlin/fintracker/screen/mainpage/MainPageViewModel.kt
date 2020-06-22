package de.htwberlin.fintracker.screen.mainpage

import android.util.Log
import androidx.lifecycle.ViewModel

class MainPageViewModel : ViewModel() {
    // Added logs to track when VM is initialised and destroyed
    init {
        Log.i("MainPageViewModel", "MainPageVM created!")
    }
    override fun onCleared() {
        super.onCleared()
        Log.i("MainPageViewModel", "MainPageVM destroyed!")
    }
}
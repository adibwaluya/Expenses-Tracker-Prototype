package de.htwberlin.fintracker.util

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

// Functions that will be need for coroutines
object Coroutines {

    // main thread
    // This function takes another function as a parameter and the function will be executed in scope
    // with this function, userLogin can be called inside AuthViewModel
    fun main(work: suspend (() -> Unit)) =

            // for this score, the thread which will be used will be defined
            CoroutineScope(Dispatchers.Main).launch {

                // launch returns a Job. This Job is returned by main function
                work()
            }
}
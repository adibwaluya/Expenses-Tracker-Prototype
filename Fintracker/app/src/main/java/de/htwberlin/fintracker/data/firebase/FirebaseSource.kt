package de.htwberlin.fintracker.data.firebase

import androidx.lifecycle.LiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import io.reactivex.Completable

/**
 * This class observe the current FirebaseUser. If there is no logged in user, FirebaseUser will
 * be null
 */
class FirebaseSource : LiveData<FirebaseUser?>() {

    /**
     * FirebaseAuth will only be initialized when some piece of codes needs it, not in the
     * creation of the class
     */
    private val firebaseAuth: FirebaseAuth by lazy {
        FirebaseAuth.getInstance()
    }

    private val authStateListener = FirebaseAuth.AuthStateListener { firebaseAuth ->
        value = firebaseAuth.currentUser
    }

    // When this object has an active observer, start observing the FirebaseAuth state to see if
    // there is currently a logged in user.
    override fun onActive() {
        firebaseAuth.addAuthStateListener(authStateListener)
    }

    // When this object no longer has an active observer, stop observing the FirebaseAuth state to
    // prevent memory leaks.
    override fun onInactive() {
        firebaseAuth.removeAuthStateListener(authStateListener)
    }

    // Implementing Completable: Observer from RxJava
    // Completable holds something that will complete and we can get the indication when it is
    // completed or failed
    fun login(email:String, password:String) = Completable.create { emitter ->
        // Performing authentication inside Completable
        // Emitter will be used to indicate whether the task is completed or failed
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
            if(!emitter.isDisposed) {
                if (it.isSuccessful)
                    emitter.onComplete()
                else
                    emitter.onError(it.exception!!)
            }
        }
    }

    fun register(email: String, password: String) = Completable.create { emitter ->
        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
            if(!emitter.isDisposed) {
                if(it.isSuccessful)
                    emitter.onComplete()
                else
                    emitter.onError(it.exception!!)
            }
        }
    }

    fun logout() = firebaseAuth.signOut()
    fun currentUser() = authStateListener
}
package de.htwberlin.fintracker.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

const val CURRENT_USER_ID = 0               // In local db only authenticated user will be stored
@Entity
data class User(
    // Entities for ROOM db
    var id: Int? = null,
    var email: String? = null,
    var name: String? = null
){
    @PrimaryKey(autoGenerate = false)
    var uid: Int = CURRENT_USER_ID
}
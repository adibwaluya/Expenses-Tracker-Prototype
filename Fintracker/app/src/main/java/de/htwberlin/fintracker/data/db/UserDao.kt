package de.htwberlin.fintracker.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import de.htwberlin.fintracker.data.db.entities.CURRENT_USER_ID
import de.htwberlin.fintracker.data.db.entities.User

// Db Operations
@Dao
interface UserDao {

    // insert or update
    // in case a user is successfully inserted, the inserted row id will be obtain
    // with the help of datatype Long
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(user: User) : Long

    // give back the stored user
    @Query("SELECT * FROM user WHERE uid = $CURRENT_USER_ID")
    fun getUser() : LiveData<User>
}
package de.htwberlin.fintracker.data.db.entities

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import de.htwberlin.fintracker.data.db.ExpenseDAO
import de.htwberlin.fintracker.data.db.UserDao
import de.htwberlin.fintracker.screen.login.UserLoginFragment

@Database(
        entities = [User::class, Expense::class],
        version = 1,
        exportSchema = false
)
abstract class AppDatabase : RoomDatabase(){

    abstract fun getUserDao() : UserDao
    abstract fun getExpenseDao() : ExpenseDAO

    // Create AppDatabase
    companion object{

        // Volatile annotation means this variable is immediately visible to all the
        // other threads
        @Volatile
        private var instance: AppDatabase? = null
        private val LOCK = Any()                        // Make sure not to create two instances of db

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){
            instance?:buildDatabase(context).also {
                instance = it
            }
        }

        // This function is returning a db
        private fun buildDatabase(context: Context) =
                Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "MyDatabase.db"
                ).build()
    }

}
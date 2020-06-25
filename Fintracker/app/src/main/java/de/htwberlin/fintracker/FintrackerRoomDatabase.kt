package de.htwberlin.fintracker

import ExpenseListDao
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import de.htwberlin.fintracker.screen.expense.ExpenseData
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.synchronized

@Database(entities = arrayOf(ExpenseData::class), version = 1)
public abstract class FintrackerRoomDatabase : RoomDatabase() {

    // Abstract getter method for each @Dao
    abstract fun expenseListDao(): ExpenseListDao

    // Singleton
    companion object {
        @Volatile
        private var INSTANCE: FintrackerRoomDatabase? = null

        fun getDatabase(context: Context): FintrackerRoomDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {  // Check whether there already is a database
                return tempInstance
            }
            else {  // TODO: Change to synchronized(this)?
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    FintrackerRoomDatabase::class.java,
                    "Finance_Tracker_Database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}
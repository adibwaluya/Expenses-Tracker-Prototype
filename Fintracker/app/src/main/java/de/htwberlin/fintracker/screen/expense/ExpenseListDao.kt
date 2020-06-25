import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import de.htwberlin.fintracker.screen.expense.ExpenseData

@Dao
interface ExpenseListDao {
    // Show all expenses as a list of ExpenseData
    @Query("SELECT * from ListOfExpenses ORDER BY date ASC")
    fun getAllExpenses(): LiveData<List<ExpenseData>>  // The List will be tracked with LiveData

    // Function to insert more expenses to the database (in coroutine)
    @Insert
    suspend fun insert(expense: ExpenseData)
}
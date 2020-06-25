import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import de.htwberlin.fintracker.screen.expense.ExpenseList

@Dao
interface ExpenseListDao {

    // Function to insert more expenses to the database (in coroutine)
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(expense: ExpenseList)
}
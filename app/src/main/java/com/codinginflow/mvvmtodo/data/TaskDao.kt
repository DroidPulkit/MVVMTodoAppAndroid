package com.codinginflow.mvvmtodo.data

import androidx.room.*
import com.codinginflow.mvvmtodo.ui.tasks.SortOrder
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(task: Task)

    @Update
    suspend fun update(task: Task)

    @Delete
    suspend fun delete(task: Task)

    fun getTasks(query: String, sortOrder: SortOrder, hideCompleted: Boolean) : Flow<List<Task>> =
        when(sortOrder)
        {
            SortOrder.BY_DATE -> getTasksSortedByDate(query, hideCompleted)
            SortOrder.BY_NAME -> getTasksSortedByName(query, hideCompleted)

        }

    @Query("Select * from task_table WHERE (isCompleted != :hideCompleted OR isCompleted = 0) AND taskName LIKE '%' || :searchQuery || '%' ORDER BY isImportant DESC, taskName")
    fun getTasksSortedByName(searchQuery: String, hideCompleted: Boolean) : Flow<List<Task>>

    @Query("Select * from task_table WHERE (isCompleted != :hideCompleted OR isCompleted = 0) AND taskName LIKE '%' || :searchQuery || '%' ORDER BY isImportant DESC, taskCreatedDate")
    fun getTasksSortedByDate(searchQuery: String, hideCompleted: Boolean) : Flow<List<Task>>

}
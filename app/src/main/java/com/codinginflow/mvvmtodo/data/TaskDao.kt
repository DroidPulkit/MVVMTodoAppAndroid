package com.codinginflow.mvvmtodo.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(task: Task)

    @Update
    suspend fun update(task: Task)

    @Delete
    suspend fun delete(task: Task)

    @Query("Select * from  task_table WHERE taskName LIKE '%' || :searchQuery || '%' ORDER BY isImportant DESC")
    fun getAllTasks(searchQuery: String) : Flow<List<Task>>

}
package com.codinginflow.mvvmtodo.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.codinginflow.mvvmtodo.di.ApplicationScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Provider

@Database(entities = [Task::class], version = 1)
abstract class TaskDatabase : RoomDatabase() {

    abstract fun taskDao(): TaskDao

    class Callback @Inject constructor(
        private val database: Provider<TaskDatabase>,
        @ApplicationScope private val applicationScope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)

            //db operations
            val dao = database.get().taskDao()

            applicationScope.launch {
                dao.insert(Task("Work on project", isImportant = true))
                dao.insert(Task("Buy eggs", isCompleted = true))
                dao.insert(Task("Complete the assignment"))
                dao.insert(Task("Project on random project"))
                dao.insert(Task("123 abc"))
                dao.insert(Task("Does it works?", isCompleted = true, isImportant = true))
            }

        }
    }
}
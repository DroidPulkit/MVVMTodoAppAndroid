package com.codinginflow.mvvmtodo.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.text.DateFormat

@Entity(tableName = "task_table")
@Parcelize
data class Task(
    val taskName: String,
    val isImportant: Boolean = false,
    val isCompleted: Boolean = false,
    val taskCreatedDate: Long = System.currentTimeMillis(),
    @PrimaryKey(autoGenerate = true) val id : Int = 0
) : Parcelable {
    val createdDateFormatted: String
        get() = DateFormat.getDateTimeInstance().format(taskCreatedDate)
}
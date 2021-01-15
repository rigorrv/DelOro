package net.android.delorotheather.model.users

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class UserItem(
    val email: String,
    @PrimaryKey
    val id: String,
    val last: String,
    val name: String,
    val pass: String
)
package net.android.delorotheather.model.login

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "items_table")
data class Login(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val login: String,
    val name: String
)
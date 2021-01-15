package net.android.delorotheather.model.movies

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity
@Parcelize
data class AuthorDetails(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val avatar_path: String,
    val name: String,
    val rating: Double?,
    val username: String
):Parcelable
package net.android.delorotheather.model.movies

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity
@Parcelize
data class ResultX(
    val author: String,
    val author_details: AuthorDetails,
    val content: String,
    val created_at: String,
    @PrimaryKey
    val id: String,
    val updated_at: String,
    val url: String
): Parcelable
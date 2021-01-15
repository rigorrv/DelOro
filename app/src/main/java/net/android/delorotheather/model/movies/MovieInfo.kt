package net.android.delorotheather.model.movies

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity
@Parcelize
data class MovieInfo(
    val adult: Boolean,
    val backdrop_path: String,
    val homepage: String,
    @PrimaryKey
    val id: Int,
    val imdb_id: String,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String
) : Parcelable
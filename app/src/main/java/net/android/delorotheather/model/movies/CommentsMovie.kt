package net.android.delorotheather.model.movies

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize


@Entity
@Parcelize
data class CommentsMovie(
    @PrimaryKey
    val id: Int,
    val page: Int,
    val results: List<ResultX>,
    val total_pages: Int,
    val total_results: Int
):Parcelable
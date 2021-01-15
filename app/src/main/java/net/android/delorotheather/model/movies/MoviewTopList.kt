package net.android.delorotheather.model.movies

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize


@Entity
@Parcelize
data class MoviewTopList(
    val page: Int,
    @PrimaryKey
    val results: List<Result>,
    val total_pages: Int,
    val total_results: Int
):Parcelable
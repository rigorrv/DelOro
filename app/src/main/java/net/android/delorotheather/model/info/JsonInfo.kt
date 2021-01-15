package net.android.delorotheather.model.info

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity
@Parcelize
data class JsonInfo(
    @PrimaryKey
    val comments: Comments,
    val contact: Contact,
    val findus: Findus,
    val home: Home,
    val login: Login,
    val movies: Movies,
    val register: Register
) : Parcelable
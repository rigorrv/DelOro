package net.android.delorotheather.model.info

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity
@Parcelize
data class Register(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val background: String,
    val infoTitle: String,
    val title: String,
    val titleBG: String
) : Parcelable
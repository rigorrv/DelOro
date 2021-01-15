package net.android.delorotheather.model.info

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity
@Parcelize
data class Contact(
    @PrimaryKey(autoGenerate = true)val id : Int,
    val address: String,
    val background: String,
    val email: String,
    val phone: String,
    val title: String,
    val titleBG: String
) : Parcelable
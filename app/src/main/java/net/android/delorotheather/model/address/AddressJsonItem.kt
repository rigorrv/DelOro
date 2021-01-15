package net.android.delorotheather.model.address

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity
@Parcelize
data class AddressJsonItem(
    val address: String,
    @PrimaryKey
    val id: String,
    val latitude: String,
    val longitude: String,
    val picture: String,
    val schedule: String,
    val state: String
) : Parcelable
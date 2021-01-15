package net.android.delorotheather.model.info

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity
@Parcelize
data class Home(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val background: String,
    val infoAbout: String,
    val infoAboutBg: String,
    val infoAboutImage: String,
    val infoSubtitle1: String,
    val infoTitle: String,
    val subtitle1: String,
    val title: String,
    val titleBG: String,
    val titleLisMoview: String,
    val whoBG: String
) : Parcelable
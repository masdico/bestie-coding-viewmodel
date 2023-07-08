package my.masdico.viewmodel

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class BasketballTeam(
    var name: String = "",
    var details: String = "",
    var imgLogo: Int = 0
): Parcelable

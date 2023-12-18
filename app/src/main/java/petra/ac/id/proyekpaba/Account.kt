package petra.ac.id.proyekpaba

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Account(
    val namaAkun: String,
    val passwordAkun: String,
    val emailAkun: String,
): Parcelable

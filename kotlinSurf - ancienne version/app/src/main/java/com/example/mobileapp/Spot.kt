import com.google.gson.annotations.SerializedName
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SpotRecord(
    val records: List<Spot>
) : Parcelable

//@Parcelize
//data class Record(
//    val id: String,
//    val createdTime: String,
//    val fields: Spot
//) : Parcelable

@Parcelize
data class Spot(
    @SerializedName("id")
    val id: Int,

    @SerializedName("surfBreak")
    val surfBreak: List<String>,

    @SerializedName("photo_url")
    val photos: Photo,

    @SerializedName("address")
    val address: String,

    @SerializedName("destination")
    val destination: String,

    @SerializedName("difficulty_level")
    val difficultyLevel: Int,

    @SerializedName("Peak Surf Season Begins")
    val peakSeasonBegins: String,

    @SerializedName("Peak Surf Season Ends")
    val peakSeasonEnds: String,

    @SerializedName("Destination State/Country")
    val destinationCountry: String,

    @SerializedName("Magic Seaweed Link")
    val magicSeaweedLink: String
) : Parcelable

@Parcelize
data class Photo(
    val id: String,
    val width: Int,
    val height: Int,
    val url: String,
    val filename: String,
    val size: Int,
    val type: String,
    val thumbnails: Thumbnails
) : Parcelable

@Parcelize
data class Thumbnails(
    val small: ThumbnailInfo,
    val large: ThumbnailInfo,
    val full: ThumbnailInfo
) : Parcelable

@Parcelize
data class ThumbnailInfo(
    val url: String,
    val width: Int,
    val height: Int
) : Parcelable
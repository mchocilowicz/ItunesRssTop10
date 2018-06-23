package marmar.com.top10.model

import com.google.gson.annotations.SerializedName

data class Response(val feed: Feed)
data class Feed(val entry: List<FeedEntry>)
data class FeedEntry(@SerializedName("im:name") val name: Field,
                     val summary: Field,
                     @SerializedName("im:artist") val artist: Field,
                     @SerializedName("im:releaseDate") val releaseDate: Field)
data class Field(val label: String)
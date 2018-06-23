package marmar.com.top10

import marmar.com.top10.model.Response
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by Marcin on 17/05/2018.
 */
interface ItunesRssService {
    @GET("{feed}/limit={limit}/json")
    fun getFeedWithLimit(@Path("feed") feed: String, @Path("limit") limit: Int): Call<Response>
}
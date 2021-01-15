package net.android.delorotheather.networking

import net.android.delorotheather.model.address.AddressJson
import net.android.delorotheather.model.comments.CommentsForm
import net.android.delorotheather.model.info.JsonInfo
import net.android.delorotheather.model.movies.CommentsMovie
import net.android.delorotheather.model.movies.FindMovie
import net.android.delorotheather.model.movies.MovieInfo
import net.android.delorotheather.model.movies.MoviewTopList
import net.android.delorotheather.model.users.User
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*


interface Api {

    @GET
    fun getInfo(
        @Url url: String
    ): Call<JsonInfo>


    @GET("/3/discover/movie?")
    fun getMovieTopList(@Query("api_key") apikey: String?): Call<MoviewTopList>

    @GET("/3/movie/{movie_id}?")
    fun getMoviInfo(
        @Path("movie_id") movie: Int,
        @Query("api_key") apikey: String?
    ): Call<MovieInfo>


    @GET("/3/movie/{movie_id}/reviews?")
    fun getCommentsMovie(
        @Path("movie_id") movie: Int,
        @Query("api_key") apikey: String?
    ): Call<CommentsMovie>


    @GET("/3/search/movie?")
    fun findMovie(
        @Query("query") movie: String?,
        @Query("api_key") apikey: String?
    ): Call<FindMovie>


    //My API


    @GET
    fun getComments(@Url url: String): Call<CommentsForm>

    @FormUrlEncoded
    @POST()
    fun login(
        @Url url: String,
        @Field("email") user: String,
        @Field("pass") pass: String
    ): Call<ResponseBody>

    @FormUrlEncoded
    @POST()
    fun register(
        @Url url: String,
        @Field("email") user: String,
        @Field("pass") pass: String
    ): Call<User>

    @FormUrlEncoded
    @POST()
    fun getAddress(
        @Url url: String,
        @Field("address") user: String,
    ): Call<AddressJson>
}


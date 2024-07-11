package ca.georgiancollege.ice8_android

import retrofit2.Call
import retrofit2.http.*

interface MovieAPIService
{
    // Display the movie list
    @GET("list")
    fun getAllMovies(): Call<ApiResponse<List<Movie>>>

    // Display a movie by ID
    @GET("find/{id}")
    fun getMovieById(@Path("id") id: String?): Call<ApiResponse<Movie>>

    // Add a new movie
    @POST("add")
    fun addMovie(@Body movie: Movie): Call<ApiResponse<Movie>>

    // Update a movie by ID
    @PUT("update/{id}")
    fun updateMovie(@Path("id") id: String?, @Body movie: Movie): Call<ApiResponse<Movie>>

    // Delete a movie by ID
    @DELETE("delete/{id}")
    fun deleteMovie(@Path("id") id: String?): Call<ApiResponse<String>>
}

package ca.georgiancollege.ice7_android

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieViewModel : ViewModel()
{
    private val movieList = MutableLiveData<List<Movie>>()
    val movies: LiveData<List<Movie>> = movieList

    private val individualMovie = MutableLiveData<Movie>()
    val movie: LiveData<Movie> = individualMovie

    private val dataManager = DataManager.instance

    fun getAllMovies()
    {
        dataManager.getAllMovies(object : Callback<ApiResponse<List<Movie>>> {
            override fun onResponse(call: Call<ApiResponse<List<Movie>>>, response: Response<ApiResponse<List<Movie>>>) {
                if (response.isSuccessful)
                {
                    val apiResponse = response.body()
                    if(apiResponse != null && apiResponse.success)
                    {
                        movieList.postValue(response.body()?.data!!)
                    }
                    else
                    {
                        println("API Access Error")
                    }
                }
                else
                {
                    println("Response NOT successful Code: ${response.code()} Error: ${response.errorBody()?.string()}")
                }
            }

            override fun onFailure(call: Call<ApiResponse<List<Movie>>>, t: Throwable)
            {
                println("getAllMovies failed with error: ${t.message}")
            }
        })
    }
}

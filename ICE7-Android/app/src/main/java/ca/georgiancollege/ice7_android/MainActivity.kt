package ca.georgiancollege.ice7_android

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import ca.georgiancollege.ice7_android.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity()
{
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MovieViewModel by viewModels()
    private lateinit var firstAdapter: FirstAdapter
    private lateinit var movieList: MutableList<Movie>

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.movies.observe(this) { movies ->
            movieList = movies.toMutableList()
            firstAdapter = FirstAdapter(movies)

            binding.FirstRecyclerView.apply {
                layoutManager = LinearLayoutManager(this@MainActivity)
                adapter = firstAdapter
            }
        }

        viewModel.getAllMovies()
    }
}
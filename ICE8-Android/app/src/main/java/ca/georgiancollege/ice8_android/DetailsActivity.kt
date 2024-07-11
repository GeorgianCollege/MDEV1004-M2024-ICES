package ca.georgiancollege.ice8_android

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import ca.georgiancollege.ice8_android.databinding.ActivityDetailsBinding

class DetailsActivity : AppCompatActivity()
{
    private lateinit var binding: ActivityDetailsBinding
    private val viewModel: MovieViewModel by viewModels()
    private var isUpdate: Boolean = false
    private var movieId: String? = null

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        isUpdate = intent.getBooleanExtra("IS_UPDATE", false)
        movieId = intent.getStringExtra("MOVIE_ID")

        if (isUpdate && movieId != null) {
            viewModel.getMovieById(movieId!!)
        }

        viewModel.movie.observe(this) { movie ->
            movie?.let {
                binding.movieIDEditText.setText(it.movieID)
                binding.titleEditText.setText(it.title)
                binding.studioEditText.setText(it.studio)
                binding.genreEditText.setText(it.genres?.joinToString(", "))
                binding.directorsEditText.setText(it.directors?.joinToString(", "))
                binding.writersEditText.setText(it.writers?.joinToString(", "))
                binding.actorsEditText.setText(it.actors?.joinToString(", "))
                binding.yearEditText.setText(it.year?.toString())
                binding.lengthEditText.setText(it.length?.toString())
                binding.descriptionMultiLine.setText(it.shortDescription)
                binding.mpaRatingEditText.setText(it.mpaRating)
                binding.criticsRatingEditText.setText(it.criticsRating?.toString())
            }
        }

        binding.updateButton.setOnClickListener {
            val movie = Movie(
                id = movieId,
                movieID = binding.movieIDEditText.text.toString(),
                title = binding.titleEditText.text.toString(),
                studio = binding.studioEditText.text.toString(),
                genres = binding.genreEditText.text.toString().split(",").map { it.trim() },
                directors = binding.directorsEditText.text.toString().split(",").map { it.trim() },
                writers = binding.writersEditText.text.toString().split(",").map { it.trim() },
                actors = binding.actorsEditText.text.toString().split(",").map { it.trim() },
                year = binding.yearEditText.text.toString().toIntOrNull(),
                length = binding.lengthEditText.text.toString().toIntOrNull(),
                shortDescription = binding.descriptionMultiLine.text.toString(),
                mpaRating = binding.mpaRatingEditText.text.toString(),
                criticsRating = binding.criticsRatingEditText.text.toString().toDoubleOrNull()
            )

            if (isUpdate) {
                viewModel.updateMovie(movieId, movie)
            } else {
                viewModel.addMovie(movie)
            }

            setResult(RESULT_OK) // Indicate successful update/add
            finish()
        }

        binding.cancelButton.setOnClickListener{
            setResult(RESULT_CANCELED)
            finish()
        }
    }
}
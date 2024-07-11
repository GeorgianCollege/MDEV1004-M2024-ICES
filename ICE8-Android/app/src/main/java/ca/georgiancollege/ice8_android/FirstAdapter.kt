package ca.georgiancollege.ice8_android

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ca.georgiancollege.ice8_android.databinding.TextRowItemBinding

class FirstAdapter(private var dataSet: List<Movie>) :
RecyclerView.Adapter<FirstAdapter.ViewHolder>()
{
    var onMovieClick: ((Movie)-> Unit)? = null

    class ViewHolder(val binding: TextRowItemBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder
    {
        val binding = TextRowItemBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ViewHolder(binding)
    }

    override fun getItemCount() = dataSet.size

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int)
    {
        val movie = dataSet[position]
        viewHolder.binding.title.text = movie.title
        viewHolder.binding.studio.text = movie.studio

        viewHolder.binding.root.setOnClickListener{
            onMovieClick?.invoke(dataSet[position])
        }

        val rating = movie.criticsRating

        if(rating != null)
        {
            viewHolder.binding.rating.text = rating.toString()

            // Set background drawable based on rating
            when {
                rating > 7 -> {
                    viewHolder.binding.rating.setBackgroundResource(R.drawable.background_green)
                    viewHolder.binding.rating.setTextColor(Color.BLACK)
                }
                rating > 5 -> {
                    viewHolder.binding.rating.setBackgroundResource(R.drawable.background_yellow)
                    viewHolder.binding.rating.setTextColor(Color.BLACK)
                }
                else -> {
                    viewHolder.binding.rating.setBackgroundResource(R.drawable.background_red)
                    viewHolder.binding.rating.setTextColor(Color.WHITE)
                }
            }
        }
        else
        {
            viewHolder.binding.rating.text = "N/A"
            viewHolder.binding.rating.setBackgroundResource(R.drawable.background_gray)
            viewHolder.binding.rating.setTextColor(Color.WHITE)
        }
    }
    // Method to update the dataset
    @SuppressLint("NotifyDataSetChanged")
    fun updateMovies(newMovies: List<Movie>) {
        dataSet = newMovies
        notifyDataSetChanged()
    }

}
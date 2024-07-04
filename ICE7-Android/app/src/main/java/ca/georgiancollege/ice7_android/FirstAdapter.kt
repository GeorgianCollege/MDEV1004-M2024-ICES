package ca.georgiancollege.ice7_android

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ca.georgiancollege.ice7_android.databinding.TextRowItemBinding

class FirstAdapter(private var dataSet: List<Movie>) :
RecyclerView.Adapter<FirstAdapter.ViewHolder>()
{
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

        val rating = movie.criticsRating

        if(rating != null)
        {
            viewHolder.binding.rating.text = rating.toString()

        }
        else
        {
            viewHolder.binding.rating.text = "N/A"
        }
    }


}
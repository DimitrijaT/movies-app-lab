package mk.ukim.finki.labmoviesapp.adapter

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commit
import androidx.recyclerview.widget.RecyclerView
import mk.ukim.finki.labmoviesapp.R
import mk.ukim.finki.labmoviesapp.domain.movie.model.Movie
import mk.ukim.finki.labmoviesapp.ui.movies.fragments.MovieDetailsFragment
import mk.ukim.finki.labmoviesapp.ui.movies.fragments.MovieListFragment

class MovieAdapter(private val context: Context, private val movies: ArrayList<Movie> = ArrayList<Movie>(), private val fragmentManager: FragmentManager) :
    RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    class MovieViewHolder(view: View, private val fragmentManager: FragmentManager) : RecyclerView.ViewHolder(view) {

        private var titleText: TextView = view.findViewById(R.id.movie_title)
        private var idText: TextView = view.findViewById(R.id.movie_id)
        private var directorText: TextView = view.findViewById(R.id.movie_director)
        private var detailsButton: Button = view.findViewById(R.id.movie_details_btn)

        fun bind(movie: Movie) {
            titleText.text = movie.name
            idText.text = movie.movieId.toString()
            directorText.text = movie.director



            detailsButton.setOnClickListener {
                val detailFragment = MovieDetailsFragment()
                val bundle = Bundle()
                bundle.putInt("MOVIE_ID", movie.movieId)
                detailFragment.arguments = bundle

                this.fragmentManager.beginTransaction()
                    .replace(R.id.fragment_container_view, detailFragment)
                    .addToBackStack(null)
                    .commit()

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.movie_item, parent, false)
        return MovieViewHolder(view, fragmentManager)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movies[position])
    }


    fun updateMovies(movies: List<Movie>) {
        this.movies.clear()
        if (movies != null) {
            this.movies.addAll(movies)
        }
        notifyDataSetChanged()
    }

}


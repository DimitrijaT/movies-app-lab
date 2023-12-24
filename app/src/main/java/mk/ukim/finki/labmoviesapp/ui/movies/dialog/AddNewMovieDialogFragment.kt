package mk.ukim.finki.labmoviesapp.ui.movies.dialog

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import mk.ukim.finki.labmoviesapp.R


class AddNewMovieDialogFragment : DialogFragment() {


    interface AddNewMovieDialogListener {
        fun OnDialogPositiveClick(
            movieName: String, movieDirector: String, movieDescription: String
        )
    }


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            val inflater = requireActivity().layoutInflater;
            val view: View = inflater.inflate(R.layout.fragment_add_new_movie_dialog, null)
            val movieName = view.findViewById<EditText>(R.id.new_movie_title)
            val movieDirector = view.findViewById<EditText>(R.id.new_movie_director)
            val movieDescription = view.findViewById<EditText>(R.id.new_movie_description)


            builder.setView(view).setPositiveButton(R.string.add_movie,
                DialogInterface.OnClickListener { dialog, id ->
                    listener.OnDialogPositiveClick(
                        movieName.text.toString(),
                        movieDirector.text.toString(),
                        movieDescription.text.toString()
                    )

                }).setNegativeButton(
                R.string.cancel, DialogInterface.OnClickListener({ dialog, id -> })
            )
            builder.create()

        } ?: throw IllegalStateException("Activity cannot be null")

    }

    lateinit var listener: AddNewMovieDialogListener

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            listener = context as AddNewMovieDialogListener
        } catch (e: ClassCastException) {
            throw ClassCastException((context.toString() + " must implement AddNewMovieDialogListener"))
        }
    }

}
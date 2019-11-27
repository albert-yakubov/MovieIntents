package com.stepasha.movieintents


import android.app.Activity

import android.content.Intent

import androidx.appcompat.app.AppCompatActivity

import android.os.Bundle
import com.stepasha.movieintents.model.FavoriteMovie
import kotlinx.android.synthetic.main.activity_favorites.*


class FavoritesActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_favorites)


        //value the movie in the movie list
        val movie :FavoriteMovie= intent.getSerializableExtra(MainActivity.EXTRA_STRING) as FavoriteMovie


        //null exception
        if (movie.title!=getString(R.string.search_movie_by_name)) {

            edit_entry.setText(movie.title)

            checkbox.isChecked=movie.watched

        }



        button_save.setOnClickListener {

            val intent = Intent()

            intent.putExtra(MainActivity.EXTRA_STRING, FavoriteMovie(edit_entry.text.toString(),false))

            setResult(Activity.RESULT_OK, intent)

            finish()

        }



        button_delete.setOnClickListener {

            onBackPressed()

        }

    }



    /**
     * Take care of popping the fragment back stack or finishing the activity
     * as appropriate.
     */

    override fun onBackPressed() {

        val intent = Intent()

        intent.putExtra(MainActivity.EXTRA_STRING, FavoriteMovie(getString(R.string.search_movie_by_name), false))

        setResult(Activity.RESULT_CANCELED, intent)

        finish()

    }

}


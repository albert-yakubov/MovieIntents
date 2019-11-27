package com.stepasha.movieintents

import android.annotation.SuppressLint
import android.app.Activity

import android.content.Intent

import android.graphics.Paint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.stepasha.movieintents.model.FavoriteMovie



import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.list_layout


class MainActivity : AppCompatActivity() {

    companion object {

        val EXTRA_STRING: String = "data"

        val RESULT_INT: Int = 54321

    }


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        text_view_sample_1.setOnClickListener {
            intentGenerator(FavoriteMovie(text_view_sample_1.text.toString(), text_view_sample_1.background != null))
            list_layout.removeView(it)
        }




        button_add_movie.setOnClickListener {
            intentGenerator(FavoriteMovie(getString(R.string.search_movie_by_name), false))
        }
    }

    private fun intentGenerator(name:  FavoriteMovie) {
        val intent = Intent(this,  FavoritesActivity::class.java)
        intent.putExtra(EXTRA_STRING, name)
        startActivityForResult(intent, RESULT_INT)
    }



    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == RESULT_INT && resultCode == Activity.RESULT_OK) {

            val name: FavoriteMovie = data?.getSerializableExtra(MainActivity.EXTRA_STRING) as FavoriteMovie
            val textView: TextView = textViewGenerator(name)
            list_layout.addView(textView)
        }
        super.onActivityResult(requestCode, resultCode, data)
    }


    fun textViewGenerator(name: FavoriteMovie): TextView {
        val textView: TextView = TextView(this)
        textView.textSize = 30f
        textView.text = name.title

        if (name.watched) textView.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG)

        textView.setOnClickListener {
            intentGenerator(name)
            list_layout.removeView(it)
        }
        return textView
    }
}




package com.stepasha.movieintents

import android.app.Activity

import android.content.Intent

import android.graphics.Paint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.stepasha.movieintents.model.MovieList


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
            intentGenerator(MovieList(text_view_sample_1.text.toString(), text_view_sample_1.background != null))
            list_layout.removeView(it)
        }


        text_view_sample_2.setOnClickListener {
            intentGenerator(MovieList(text_view_sample_2.text.toString(), text_view_sample_2.background != null))
            list_layout.removeView(it)
        }



        text_view_sample_3.setOnClickListener {
            intentGenerator(MovieList(text_view_sample_3.text.toString(), text_view_sample_3.background != null))
            list_layout.removeView(it)
        }

        button_add_movie.setOnClickListener {
            intentGenerator(MovieList(getString(R.string.search_movie_by_name)))
        }
    }

    private fun intentGenerator(name:  MovieList) {
        val intent = Intent(this,  FavoritesActivity::class.java)
        intent.putExtra(EXTRA_STRING, name)
        startActivityForResult(intent, RESULT_INT)
    }



    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == RESULT_INT && resultCode == Activity.RESULT_OK) {

            val name: MovieList = data?.getSerializableExtra(MainActivity.EXTRA_STRING) as MovieList
            val textView: TextView = textViewGenerator(name)
            list_layout.addView(textView)
        }
        super.onActivityResult(requestCode, resultCode, data)
    }


    fun textViewGenerator(name: MovieList): TextView {
        val textView: TextView = TextView(this)
        textView.textSize = 30f
        textView.text = name.name

        if (name.watched) textView.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG)

        textView.setOnClickListener {
            intentGenerator(name)
            list_layout.removeView(it)
        }
        return textView
    }
}





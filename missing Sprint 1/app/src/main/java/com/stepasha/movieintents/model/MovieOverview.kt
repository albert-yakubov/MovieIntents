package com.stepasha.movieintents.model

class MovieSearchResult(val results: List<MovieOverview>)

class MovieOverview(
    val title: String,
    val movieId: Int = 0

)
package com.example.triviaapp.data

data class TriviaModel(
    val response_code: Int,
    val results: List<Result>
)
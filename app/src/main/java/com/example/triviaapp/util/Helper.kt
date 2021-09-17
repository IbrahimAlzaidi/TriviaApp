package com.example.triviaapp.util

class Helper {

    fun alphabetChars(): Char {
        var c: Char
        c = 'A'
        while (c <= 'Z') {
            print("$c ")
            ++c
        }
        return c
    }
}
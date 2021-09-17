package com.example.triviaapp.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.triviaapp.R
import com.example.triviaapp.databinding.FragmentQuizBinding

class QuizFragment:BaseFragment<FragmentQuizBinding>() {
    var points:Int = 0
    override val LOG_TAG: String
        get()= "Quiz fragment"

    override val bindingInflater: (LayoutInflater) -> FragmentQuizBinding = FragmentQuizBinding::inflate


    override fun setup() {

    }


    override fun addCallBack() {
    }
}
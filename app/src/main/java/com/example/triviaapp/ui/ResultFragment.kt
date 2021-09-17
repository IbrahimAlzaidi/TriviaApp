package com.example.triviaapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.triviaapp.R
import com.example.triviaapp.databinding.FragmentQuizBinding
import com.example.triviaapp.databinding.FragmentResultBinding

class ResultFragment:BaseFragment<FragmentResultBinding>() {
    var points:Int = 0
    override val LOG_TAG: String
        get()= "Result fragment"

    override val bindingInflater: (LayoutInflater) -> FragmentResultBinding = FragmentResultBinding::inflate


    override fun setup() {

    }


    override fun addCallBack() {
    }
}
package com.example.triviaapp.ui

import android.content.Intent
import android.view.LayoutInflater
import com.example.triviaapp.databinding.ActivityHomeBinding

class HomeActivity : BaseActivity<ActivityHomeBinding>() {
    //type the content after make override
    override val LOG_TAG: String = "HOME_ACTIVITY"

    override val bindingInflater: (LayoutInflater) -> ActivityHomeBinding =
        ActivityHomeBinding::inflate

    override fun setup() {

    }

    override fun addCallbacks() {
        binding?.button?.setOnClickListener {
            val intent = Intent(this, QuizActivity::class.java)
            startActivity(intent)
        }
    }
}

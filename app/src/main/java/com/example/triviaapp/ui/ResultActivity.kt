package com.example.triviaapp.ui

import android.content.Intent
import android.view.LayoutInflater
import com.example.triviaapp.databinding.ActivityResultBinding
import com.example.triviaapp.util.Constants

class ResultActivity : BaseActivity<ActivityResultBinding>() {
    //type the content after make override
    override val LOG_TAG: String = "HOME_ACTIVITY"

    override val bindingInflater: (LayoutInflater) -> ActivityResultBinding =
        ActivityResultBinding::inflate

    override fun setup() {
        val total = intent.getStringExtra(Constants.POINTS)
        binding?.score?.text = "$total / 100"
    }

    override fun addCallbacks() {

        binding?.button2?.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }

    }

}
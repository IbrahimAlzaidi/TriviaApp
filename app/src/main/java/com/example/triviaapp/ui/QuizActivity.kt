package com.example.triviaapp.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.widget.TextView
import com.example.triviaapp.R
import com.example.triviaapp.data.TriviaModel
import com.example.triviaapp.databinding.ActivityQuizBinding
import com.example.triviaapp.util.Constants
import com.google.gson.GsonBuilder
import okhttp3.*
import java.io.IOException


class QuizActivity : BaseActivity<ActivityQuizBinding>() {
    //type the content after make override
    override val LOG_TAG: String = "Quiz_ACTIVITY"
    private val client = OkHttpClient()
    private val gson = GsonBuilder().create()
    var index: Int = 0
    private var point: Int = 0
    private var correctAnswer = ""
    var answerQuestion = mutableListOf<String?>()

    override val bindingInflater: (LayoutInflater) -> ActivityQuizBinding =
        ActivityQuizBinding::inflate

    override fun setup() {
        makeRequest()

    }

    override fun addCallbacks() {
        binding?.top?.setOnClickListener { goBack() }
        getNextQuestion()
        getCorrectAnswer()
    }

    private fun goBack() {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
    }

    private fun makeRequest() {
        if (index >= 5) displayResult()
        else {
            isClicked(one = true, two = true, three = true, four = true)
            val request = Request.Builder().url(Constants.URL).build()

            client.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    e.message?.let { Log.i(LOG_TAG, it) }
                }

                @SuppressLint("SetTextI18n")
                override fun onResponse(call: Call, response: Response) {
                    val body = response.body?.string()
                    val result = gson.fromJson(body, TriviaModel::class.java)
                     val info = result.results.toList().get(index)
                    answerQuestion = mutableListOf(
                        info.incorrect_answers[0],
                        info.incorrect_answers[1],
                        info.incorrect_answers[2],
                        info.correct_answer
                    ).shuffled().toMutableList()
                    runOnUiThread {
                        binding?.apply {
                            binding?.textQuestion?.text =
                            info.question.replace("&quot;", "").replace("&#039;", "")
                            binding?.textFirstAnswer?.text = answerQuestion[0]
                            binding?.textSecondAnswer?.text = answerQuestion[1]
                            binding?.textThirdAnswer?.text = answerQuestion[2]
                            binding?.textFourthAnswer?.text = answerQuestion[3]
                            binding?.totalNum?.text = index.toString()
                            isEnabledButton(value = false)
                        }
                        index++
                    }
                }
            })}

    }

    private fun getCorrectAnswer() {
        binding?.textFirstAnswer?.setOnClickListener {
            getAnswer(binding?.textFirstAnswer!!)
            isClicked(one = true, two = false, three = false, four = false)
            countPoints(binding?.textFirstAnswer!!)
        }
        binding?.textSecondAnswer?.setOnClickListener {
            getAnswer(binding?.textSecondAnswer!!)
            isClicked(one = false, two = true, three = false, four = false)
            countPoints(binding?.textSecondAnswer!!)

        }
        binding?.textThirdAnswer?.setOnClickListener {
            getAnswer(binding?.textThirdAnswer!!)
            isClicked(one = false, two = false, three = true, four = false)
            countPoints(binding?.textThirdAnswer!!)

        }
        binding?.textFourthAnswer?.setOnClickListener {
            getAnswer(binding?.textFourthAnswer!!)
            isClicked(one = false, two = false, three = false, four = true)
            countPoints(binding?.textFourthAnswer!!)

        }
    }


    private fun isClicked(one : Boolean,two: Boolean,three: Boolean,four: Boolean){
        binding?.textFirstAnswer?.isClickable = one
        binding?.textSecondAnswer?.isClickable = two
        binding?.textThirdAnswer?.isClickable = three
        binding?.textFourthAnswer?.isClickable = four
    }
    private fun displayResult() {
        val intent = Intent(this, ResultActivity::class.java)
        Log.i(LOG_TAG, point.toString())
        intent.putExtra(Constants.POINTS, (point+10).toString())
        startActivity(intent)
    }

    private fun isEnabledButton(value: Boolean) {
        when (value) {
            false -> binding?.nextBtn?.isEnabled = false
            true -> binding?.nextBtn?.isEnabled = true
        }
    }

    private fun getNextQuestion() {
        binding?.nextBtn?.setOnClickListener {
            makeRequest()
            getDefaultStyle()
        }
    }

    private fun getDefaultStyle() {
        binding?.textFirstAnswer?.setBackgroundResource(R.drawable.text_background)
        binding?.textSecondAnswer?.setBackgroundResource(R.drawable.text_background)
        binding?.textThirdAnswer?.setBackgroundResource(R.drawable.text_background)
        binding?.textFourthAnswer?.setBackgroundResource(R.drawable.text_background)
    }

    private fun getAnswer(textView: TextView) {
        when (textView) {
            binding?.textFirstAnswer -> binding?.textFirstAnswer!!.setBackgroundResource(R.drawable.answer)
            binding?.textSecondAnswer -> binding?.textSecondAnswer!!.setBackgroundResource(R.drawable.answer)
            binding?.textThirdAnswer -> binding?.textThirdAnswer!!.setBackgroundResource(R.drawable.answer)
            binding?.textFourthAnswer -> binding?.textFourthAnswer!!.setBackgroundResource(R.drawable.answer)
        }
        isEnabledButton(true)
    }

    private fun countPoints(answerText: TextView) {
        if (answerText.text == correctAnswer)
            point++
    }
}
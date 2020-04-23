package com.muhammednazeer.geoquiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var trueButton: Button
    private lateinit var falseButton: Button
    private lateinit var nextButton: ImageButton
    private lateinit var prevButton: ImageButton
    private lateinit var questionTextView: TextView

    private val questionBank = listOf(
        Question(R.string.question_australia, true),
        Question(R.string.question_oceans, true),
        Question(R.string.question_mideast, false),
        Question(R.string.question_africa, false),
        Question(R.string.question_americas, true),
        Question(R.string.question_asia, true))
    private var currentIndex = 0
    private  var totalScore = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        trueButton = true_button
        falseButton = findViewById(R.id.false_button)
        nextButton = next_button
        prevButton = prev_button
        questionTextView = question_text_view

        trueButton.setOnClickListener {
           checkAnswer(true)

        }


        falseButton.setOnClickListener {
            checkAnswer(false)
        }

        nextButton.setOnClickListener {
            currentIndex = (currentIndex + 1) % questionBank.size
            updateQuestion()
            if(currentIndex == questionBank.size)
                Toast.makeText(this, "SCORE: ${(totalScore/questionBank.size)*100}%", Toast.LENGTH_LONG).show()
        }
        prevButton.setOnClickListener {
             currentIndex = if((currentIndex  - 1) % questionBank.size == 0){
                 questionBank.size
             } else{
                 (currentIndex  - 1) % questionBank.size
             }
            updateQuestion()

        }
        questionTextView.setOnClickListener {
            currentIndex = (currentIndex + 1) % questionBank.size
            updateQuestion()
        }

       updateQuestion()

    }
   private fun updateQuestion(){
        val questionTextResId = questionBank[currentIndex].textResId
        questionTextView.setText(questionTextResId)
    }
    private fun checkAnswer(userAnswer: Boolean){
        val correctAnswer = questionBank[currentIndex].answer
        val messageResId = if(userAnswer == correctAnswer){
            R.string.correct_toast
        }else{
            R.string.incorrect_toast
        }
        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show()
        if(userAnswer == correctAnswer){
            totalScore += totalScore
        }

    }


}


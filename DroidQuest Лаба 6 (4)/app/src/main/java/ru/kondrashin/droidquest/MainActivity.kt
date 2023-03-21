package ru.kondrashin.droidquest

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log

import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts


class MainActivity : AppCompatActivity() {
    companion object{
        private val TAG = "MainActivity"
        private val KEY_INDEX = "index"
//        private val REQUEST_CODE_DECEIT = 0
        private val KEY_BOLLEN = "decite"
    }

    private lateinit var mTrueButton: Button
    private lateinit var mFalseButton: Button
    private lateinit var mNextButton: ImageButton
    private lateinit var mPreviewButton: ImageButton
    private lateinit var mQuestionTextView: TextView
    private lateinit var mDeceitButton: Button
    private var mIsDeceiter = false
    private val mQuestionBank = listOf(
        Question(R.string.question_android, true),
        Question(R.string.question_linear, false),
        Question(R.string.question_service, false),
        Question(R.string.question_res, true),
        Question(R.string.question_manifest, true),
        Question(R.string.question_oc, true),
        Question(R.string.question_created, true),
        Question(R.string.question_libs, false),
        Question(R.string.question_tiramisu, true),
        Question(R.string.question_vm, false)
    )
    private var deceitQuest = mutableListOf<Int>()

    private var mCurrentIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d(TAG, "onCreate(Bundle) вызван")
        if (savedInstanceState != null) {
            mCurrentIndex = savedInstanceState.getInt(KEY_INDEX, 0);
            mIsDeceiter = savedInstanceState.getBoolean(KEY_BOLLEN, false);
        }
        mQuestionTextView = findViewById(R.id.question_text_view)
        mQuestionTextView.setOnClickListener {
            mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.size
            mIsDeceiter = false
            updateQuestion()
        }


        mTrueButton = findViewById(R.id.true_button)
        mTrueButton.setOnClickListener {

            checkAnswer(true)
        }
        mFalseButton =findViewById(R.id.false_button)
        mFalseButton.setOnClickListener {
            checkAnswer(false)
        }
        mPreviewButton = findViewById(R.id.preview_button)
        mPreviewButton.setOnClickListener {
            if(mCurrentIndex != 0){
                if (mIsDeceiter == true){
                    deceitQuest.add(mCurrentIndex)
                }
                mCurrentIndex = (mCurrentIndex - 1) % mQuestionBank.size
                checkDeceid()
                updateQuestion()

            } else {
                mCurrentIndex = mQuestionBank.size

            }
        }
        mNextButton = findViewById(R.id.next_button)
        mNextButton.setOnClickListener {
            if (mIsDeceiter == true){
                deceitQuest.add(mCurrentIndex)
            }
            mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.size
            checkDeceid()
            updateQuestion()
        }
        mDeceitButton = findViewById(R.id.deceit_button)
        mDeceitButton.setOnClickListener{
            val answerIsTrue = mQuestionBank[mCurrentIndex].answerTrue
            val intent = DeceitActivity.newIntent(this, answerIsTrue)
            startForResult.launch(intent)
//            startActivityForResult(intent!!, REQUEST_CODE_DECEIT)
        }
        updateQuestion()

    }
//    override fun onActivityResult(
//        requestCode: Int, resultCode:
//        Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        if (resultCode != RESULT_OK) {
//            return;
//        }
//        if (requestCode == REQUEST_CODE_DECEIT) {
//            if (data == null) {
//                return;
//            }
//            mIsDeceiter = DeceitActivity.wasAnswerShown(result = data);
//
//        }
//    }
    val startForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            result: ActivityResult ->
        if (result.resultCode == Activity.RESULT_OK) {
            val intent = result.data
            mIsDeceiter = DeceitActivity.wasAnswerShown(intent!!);
// Обработка результата
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.i(TAG, "onSaveInstanceState")
        outState!!.putInt(KEY_INDEX, mCurrentIndex)
        outState!!.putBoolean(KEY_BOLLEN, mIsDeceiter)

    }
    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart() вызван")
    }
    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause() вызван")
    }
    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume() вызван")
    }
    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop() вызван")
    }
    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy() вызван")
    }
    private fun updateQuestion() {
        val question = mQuestionBank[mCurrentIndex].textResId
        mQuestionTextView.setText(question)
    }
    private fun checkAnswer(userPressedTrue: Boolean) {
        val answerIsTrue = mQuestionBank[mCurrentIndex].answerTrue
        val messageResId =  if (mIsDeceiter) R.string.judgment_toast
        else if (userPressedTrue == answerIsTrue) {
            R.string.correct_toast
        } else {
            R.string.incorrect_toast
        }
        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show()
    }
    private fun checkDeceid(){
        if(mCurrentIndex in deceitQuest){
            mIsDeceiter = true
        } else {
            mIsDeceiter = false
        }
    }
}
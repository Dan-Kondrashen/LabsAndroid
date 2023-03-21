package ru.kondrashin.droidquest

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView

class DeceitActivity : AppCompatActivity() {
    companion object {
        private val TAG = "DeceitActivity"
        private val KEY_INDEX = "index"
        val EXTRA_ANSWER_IS_TRUE =
            "ru.kondrashin.droidquest.answer_is_true"
        val EXTRA_ANSWER_SHOWN =
            "ru.kondrashin.droidquest.answer_shown"
        fun newIntent(packageContext: Context?, answerIsTrue: Boolean):
                Intent? {
            val intent = Intent(packageContext,
                DeceitActivity::class.java)
            return intent.putExtra(EXTRA_ANSWER_IS_TRUE, answerIsTrue)
        }
        fun wasAnswerShown(result: Intent) =
            result.getBooleanExtra(EXTRA_ANSWER_SHOWN, false)
    }

    private lateinit var mAnswerTextView: TextView
    private lateinit var mShowAnswer: Button
    private var mCurrentBul: Boolean = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_deceit)

        val mAnswerIsTrue = getIntent().getBooleanExtra(
            EXTRA_ANSWER_IS_TRUE, false)
        mAnswerTextView = findViewById(R.id.answer_text_view);
        mShowAnswer = findViewById(R.id.show_answer_button);
        if (savedInstanceState != null) {
            setAnswerShownResult(savedInstanceState.getBoolean(KEY_INDEX));
            if (savedInstanceState.getBoolean(KEY_INDEX)){
                mAnswerTextView.setText(
                    if (mAnswerIsTrue) R.string.true_button
                    else R.string.false_button)
            }
        }
        mShowAnswer.setOnClickListener {
            mAnswerTextView.setText(
                if (mAnswerIsTrue) R.string.true_button
                else R.string.false_button)
            setAnswerShownResult(true)


        }

    }
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.i(TAG, "onSaveInstanceState")
        outState!!.putBoolean(KEY_INDEX, mCurrentBul)
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
    private fun setAnswerShownResult(isAnswerShown: Boolean) {
        val data = Intent()
        mCurrentBul = isAnswerShown
        data.putExtra(EXTRA_ANSWER_SHOWN, isAnswerShown)
        setResult(Activity.RESULT_OK, data)
    }
}
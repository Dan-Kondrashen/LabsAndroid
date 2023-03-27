package ru.kondrashin.bookkdepository

import android.os.Bundle
import androidx.fragment.app.FragmentActivity


class BookActivity : FragmentActivity()  {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.fragmentContainer,
                    BookFragment.newInstance())
                .commitNow()
        }
    }
}
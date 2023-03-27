package ru.kondrashin.bookkdepository

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.text.format.DateFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.CompoundButton
import android.widget.EditText
import androidx.fragment.app.Fragment
//import java.text.DateFormat

import java.util.zip.DataFormatException


class BookFragment: Fragment() {
    companion object {
        fun newInstance() = BookFragment()
    }
    private lateinit var book: Book
    private lateinit var titleField: EditText
    private lateinit var dateButton: Button
    private lateinit var isReadedCheckBox: CheckBox
    override fun onCreate(savedInstanceState: Bundle?) {
        super. onCreate(savedInstanceState)
        book = Book()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //  Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_book, container, false)
        titleField = v.findViewById(R.id.book_title)
        titleField.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                s: CharSequence, start: Int, count: Int, after: Int) {
                // Здесь намеренно оставлено пустое место
            }
            override fun onTextChanged(
                s: CharSequence, start: Int, before: Int, count: Int) {
                book.title = s.toString()
            }
            override fun afterTextChanged(c: Editable) {
                // И здесь тоже
            }
        })
        dateButton = v.findViewById(R.id.book_date)
        dateButton.text = DateFormat.format("Год: yyyy Месяц: MM День: dd",book.date)
        dateButton.isEnabled = false
        isReadedCheckBox = v.findViewById(R.id.book_readed)
        isReadedCheckBox.setOnCheckedChangeListener {
                compoundButton: CompoundButton,
                isChecked: Boolean ->
                // Назначение флага прочтения книги
                book.isReaded = isChecked
        }
        return v
    }

}
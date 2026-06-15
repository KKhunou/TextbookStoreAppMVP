package com.example.sste.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.sste.R
import com.example.sste.viewmodels.BookViewModel

class BookDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_detail)

        val bookId = intent.getIntExtra("book_id", -1)
        if (bookId == -1) {
            finish()
            return
        }

        val bookViewModel = ViewModelProvider(this)[BookViewModel::class.java]
        val book = bookViewModel.getBookById(bookId)

        if (book == null) {
            finish()
            return
        }

        findViewById<TextView>(R.id.tvDetailTitle).text = book.title
        findViewById<TextView>(R.id.tvDetailAuthor).text = book.author
        findViewById<TextView>(R.id.tvDetailModule).text = book.moduleCode
        findViewById<TextView>(R.id.tvDetailPrice).text = "R${book.price}"
        findViewById<TextView>(R.id.tvDetailEdition).text = book.edition
        findViewById<TextView>(R.id.tvDetailSeller).text = book.sellerName

        val btnContact = findViewById<Button>(R.id.btnContactSeller)
        btnContact.setOnClickListener {
            val intent = Intent(this, InquiryActivity::class.java)
            intent.putExtra("book_title", book.title)
            intent.putExtra("seller", book.sellerName)
            startActivity(intent)
        }
    }
}
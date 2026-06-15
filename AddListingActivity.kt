package com.example.sste.activities

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.sste.R
import com.example.sste.models.Book
import com.example.sste.viewmodels.BookViewModel
import com.google.android.material.snackbar.Snackbar

class AddListingActivity : AppCompatActivity() {
    private lateinit var bookViewModel: BookViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_listing)

        bookViewModel = ViewModelProvider(this)[BookViewModel::class.java]

        val etTitle = findViewById<EditText>(R.id.etTitle)
        val etAuthor = findViewById<EditText>(R.id.etAuthor)
        val etModuleCode = findViewById<EditText>(R.id.etModuleCode)
        val etPrice = findViewById<EditText>(R.id.etPrice)
        val etEdition = findViewById<EditText>(R.id.etEdition)
        val etSeller = findViewById<EditText>(R.id.etSeller)
        val btnSubmit = findViewById<Button>(R.id.btnSubmit)

        btnSubmit.setOnClickListener { view ->
            val title = etTitle.text.toString().trim()
            val author = etAuthor.text.toString().trim()
            val module = etModuleCode.text.toString().trim()
            val priceStr = etPrice.text.toString().trim()
            val edition = etEdition.text.toString().trim()
            val seller = etSeller.text.toString().trim()

            if (title.isEmpty() || author.isEmpty() || module.isEmpty() || priceStr.isEmpty() || edition.isEmpty() || seller.isEmpty()) {
                Snackbar.make(view, "All fields required", Snackbar.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val price = priceStr.toDoubleOrNull()
            if (price == null || price <= 0) {
                Snackbar.make(view, "Valid price required", Snackbar.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val newBook = Book(
                id = 0,
                title = title,
                author = author,
                moduleCode = module,
                price = price,
                edition = edition,
                sellerName = seller
            )
            bookViewModel.addBook(newBook)
            Snackbar.make(view, "Book added successfully", Snackbar.LENGTH_SHORT).show()
            finish()
        }
    }
}
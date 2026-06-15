package com.example.sste.activities

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.sste.R

class InquiryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inquiry)

        val bookTitle = intent.getStringExtra("book_title") ?: "this textbook"
        val seller = intent.getStringExtra("seller") ?: "the seller"

        findViewById<TextView>(R.id.tvInquiryTitle).text = "Inquiry about: $bookTitle"
        findViewById<TextView>(R.id.tvInquirySeller).text = "Seller: $seller"

        val etMessage = findViewById<EditText>(R.id.etMessage)
        val btnSend = findViewById<Button>(R.id.btnSendInquiry)

        btnSend.setOnClickListener {
            val message = etMessage.text.toString().trim()
            if (message.isEmpty()) {
                Toast.makeText(this, "Please type a message", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Inquiry sent (demo) – seller would be notified", Toast.LENGTH_LONG).show()
                finish()
            }
        }
    }
}
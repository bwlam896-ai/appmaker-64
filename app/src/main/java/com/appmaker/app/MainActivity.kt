package com.appmaker.app

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView

class MainActivity : AppCompatActivity() {

    private lateinit var etTitle: EditText
    private lateinit var etContent: EditText
    private lateinit var btnAddNote: Button
    private lateinit var notesContainer: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etTitle = findViewById(R.id.etTitle)
        etContent = findViewById(R.id.etContent)
        btnAddNote = findViewById(R.id.btnAddNote)
        notesContainer = findViewById(R.id.notesContainer)

        btnAddNote.setOnClickListener {
            addNote()
        }
    }

    private fun addNote() {
        val title = etTitle.text.toString().trim()
        val content = etContent.text.toString().trim()

        if (title.isEmpty() && content.isEmpty()) return

        val cardView = CardView(this).apply {
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply {
                setMargins(0, 0, 0, 16)
            }
            radius = 8f
            cardElevation = 4f
        }

        val innerLayout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(16, 16, 16, 16)
        }

        val tvTitle = TextView(this).apply {
            text = title
            textSize = 18f
            setTextColor(resources.getColor(android.R.color.black))
            setTypeface(null, android.graphics.Typeface.BOLD)
        }

        val tvContent = TextView(this).apply {
            text = content
            textSize = 14f
            setTextColor(resources.getColor(android.R.color.darker_gray))
            setPadding(0, 8, 0, 8)
        }

        val btnDelete = Button(this).apply {
            text = getString(R.string.delete)
            setBackgroundColor(resources.getColor(android.R.color.holo_red_dark))
            setTextColor(resources.getColor(android.R.color.white))
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            setOnClickListener {
                notesContainer.removeView(cardView)
            }
        }

        innerLayout.addView(tvTitle)
        innerLayout.addView(tvContent)
        innerLayout.addView(btnDelete)
        cardView.addView(innerLayout)

        notesContainer.addView(cardView, 0)

        etTitle.text.clear()
        etContent.text.clear()
    }
}
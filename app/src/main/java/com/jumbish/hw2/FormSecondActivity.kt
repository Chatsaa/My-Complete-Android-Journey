package com.jumbish.hw2

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class FormSecondActivity : AppCompatActivity() {
    lateinit var rName: TextView
    lateinit var rDob: TextView
    lateinit var rGender: TextView
    lateinit var rHobbies: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_form_second)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        rName = findViewById(R.id.rname)
        rDob = findViewById(R.id.rdob)
        rGender = findViewById(R.id.rGender)
        rHobbies = findViewById(R.id.rHobbies)

        val name = intent.getStringExtra("name")
        val dob = intent.getStringExtra("dob")
        val gender = intent.getStringExtra("gender")
        val hobbies = intent.getStringExtra("hobbies")

        rName.setText(name)
        rDob.text = dob
        rGender.text = gender
        rHobbies.setText(hobbies)
    }
}
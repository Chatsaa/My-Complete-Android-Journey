package com.jumbish.hw2

import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SecondActivity : AppCompatActivity() {
   lateinit var tvName : TextView
   lateinit var tvAge : TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("SecondActivity","Second ActivityOnCreate Called")
        enableEdgeToEdge()
        setContentView(R.layout.activity_second)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    tvName = findViewById(R.id.tvresult)
    tvAge = findViewById(R.id.tvresult2)

        val  name = intent.getStringExtra("name")
        val age = intent.getStringExtra("age")
        tvName.setText(name)
        tvAge.setText(age)

    }




}
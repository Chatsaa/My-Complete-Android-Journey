package com.jumbish.hw2

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
  lateinit var etName : EditText
  lateinit var etAge : EditText
  lateinit var btnGo : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        Log.d("ActivityLifeCycle","onCreate Called")
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets



        }
        etName = findViewById(R.id.etName)
        etAge = findViewById(R.id.etAge)
        btnGo = findViewById(R.id.btnGo)
        btnGo.setOnClickListener {
            val name  = etName.text.toString().trim()
            val age  = etAge.text.toString().trim()


            if (name.isEmpty()){
                etName.error = "Please fill name"
                return@setOnClickListener
            }

            if (age.isEmpty()){
                etAge.error = "Please fill Age"
            return@setOnClickListener
            }

            val intent = Intent(this,SecondActivity::class.java)
            intent.putExtra("name",name)
            intent.putExtra("age",age)

            startActivity(intent)
        }









    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
    outState.putString("name", etName.text.toString())
        outState.putString("age",etAge.text.toString())
    Log.d("Lifecycle","State Saved")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        val name  = savedInstanceState.getString("name","")
        val age = savedInstanceState.getString("age","")
        etName.setText(name)
        etAge.setText(age)
        Log.d("Lifecycle","State restored")
    }

}
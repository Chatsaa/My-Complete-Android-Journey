package com.jumbish.hw2

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    // Top level Property
    lateinit var resultLauncher: ActivityResultLauncher<Intent>
    lateinit var etName: EditText
    lateinit var etAge: EditText
    lateinit var btnGo: Button
    lateinit var btnFormFill: Button
    lateinit var btnRecyclerView: Button
    lateinit var btnRecyclerViewPractice: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        Log.d("ActivityLifeCycle", "onCreate Called")
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets


        }
        resultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == RESULT_OK) {
                    val data = result.data
                    val message = data?.getStringExtra("result")
                    Toast.makeText(
                        this,
                        "Message from second Activity : $message",
                        Toast.LENGTH_SHORT
                    ).show()
                }

            }
        etName = findViewById(R.id.etName)
        etAge = findViewById(R.id.etAge)
        btnGo = findViewById(R.id.btnGo)
        btnFormFill = findViewById(R.id.btnForm)
        btnFormFill = findViewById(R.id.btnForm)
        btnRecyclerView = findViewById(R.id.btnRecyclerView)
        btnRecyclerViewPractice = findViewById(R.id.btnRecyclerViewPractice)

        //btnRecyclerViewPractice
        btnRecyclerViewPractice.setOnClickListener {
            val intent = Intent(this, RecyclerViewPractice::class.java)
            startActivity(intent)
        }

        //btnGo
        btnGo.setOnClickListener {
            val name = etName.text.toString().trim()
            val age = etAge.text.toString().trim()


            if (name.isEmpty()) {
                etName.error = "Please fill name"
                return@setOnClickListener
            }

            if (age.isEmpty()) {
                etAge.error = "Please fill Age"
                return@setOnClickListener
            }

            val intent = Intent(this, SecondActivity::class.java)
            intent.putExtra("name", name)
            intent.putExtra("age", age)

            resultLauncher.launch(intent)
        }

        //btnFillForm
        btnFormFill.setOnClickListener {
            val intent = Intent(this, FormFill::class.java)
            startActivity(intent)
        }


        //btnRecyclerView
        btnRecyclerView.setOnClickListener {
            val intent = Intent(this, RecyclerViewActivity::class.java)
            startActivity(intent)

        }


    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("name", etName.text.toString())
        outState.putString("age", etAge.text.toString())
        Log.d("Lifecycle", "State Saved")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        val name = savedInstanceState.getString("name", "")
        val age = savedInstanceState.getString("age", "")
        etName.setText(name)
        etAge.setText(age)
        Log.d("Lifecycle", "State restored")
    }

}
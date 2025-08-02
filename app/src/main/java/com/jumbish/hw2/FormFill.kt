package com.jumbish.hw2

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.Calendar

class FormFill : AppCompatActivity() {
    lateinit var etName: EditText
    lateinit var etDOB: EditText
    lateinit var rgGender: RadioGroup
    lateinit var rbMale: RadioButton
    lateinit var rbFemale: RadioButton
    lateinit var cbFootBall: CheckBox
    lateinit var cbReading: CheckBox
    lateinit var cbMusic: CheckBox

    //      lateinit var cbTerms: CheckBox
    lateinit var btnSubmit: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_form_fill)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        etName = findViewById(R.id.etName)
        etDOB = findViewById(R.id.etDOB)
        rgGender = findViewById(R.id.rbGroup)
        rbMale = findViewById(R.id.rbMale)
        rbFemale = findViewById(R.id.rbFemale)
        cbFootBall = findViewById(R.id.cbFootball)
        cbReading = findViewById(R.id.cbReading)
        cbMusic = findViewById(R.id.cbMusic)
        btnSubmit = findViewById(R.id.btnSubmit)


        etDOB.setOnClickListener {
            showDatePicker()

        }

        btnSubmit.setOnClickListener {
            validateForm()


        }


    }

    private fun validateForm() {
        val name = etName.text.toString().trim()
        val dob = etDOB.text.toString().trim()
        if (name.isEmpty()) {
            etName.error = "Name is required"
            return
        }

        if (dob.isEmpty()) {
            etDOB.error = "DOB required"
            return
        }
        else{
            etDOB.error = null
        }

        val genderId = rgGender.checkedRadioButtonId
        if (genderId == -1) {
            Toast.makeText(this, "Please select gender", Toast.LENGTH_SHORT).show()
            return
        }

        val gender = when (genderId) {
            R.id.rbMale -> "Male"
            R.id.rbFemale -> "FeMale"
            else -> "Not Selected"
        }

        val hobbies = mutableListOf<String>()
        if (cbFootBall.isChecked) hobbies.add("Football")
        if (cbReading.isChecked) hobbies.add("Reading")
        if (cbMusic.isChecked) hobbies.add("Music")

        if (hobbies.isEmpty()) {
            Toast.makeText(this, "Please select at least one", Toast.LENGTH_SHORT).show()
            return
        }

        val finalData = """
            Name : $name
            DOB : $dob
            Gender : $gender
             Hobbies :${hobbies.joinToString(",")}   
                """.trimIndent()

        Toast.makeText(this, finalData, Toast.LENGTH_SHORT).show()


        val intent  = Intent(this, FormSecondActivity::class.java)
        intent.putExtra("name", name)
        intent.putExtra("dob", dob)
        intent.putExtra("gender", gender)
        intent.putExtra("hobbies", hobbies.joinToString(",") )
        startActivity(intent)


    }





    private fun showDatePicker() {
        val calender = Calendar.getInstance()
        val year  = calender.get(Calendar.YEAR)
        val month = calender.get(Calendar.MONTH)
        val day = calender.get(Calendar.DAY_OF_MONTH)
        val datePickerDialog = DatePickerDialog(this,{_, y, m, d ->
  val selectedDate = "$d/${m + 1}/$y"
 etDOB.setText(selectedDate)
        }, year,month,day)


        datePickerDialog.show()
    }
}


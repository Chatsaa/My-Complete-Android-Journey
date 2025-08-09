package com.jumbish.hw2

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewActivity : AppCompatActivity() {
    private lateinit var rRecyclerView : RecyclerView
    private lateinit var adapter: StudentAdapter
    private lateinit var studentList : List<Student>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_recycler_view)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        rRecyclerView = findViewById(R.id.rRecyclerView)

        studentList = listOf(
            Student("Aman", 20),
            Student("Sneha", 22),
            Student("Rahul", 19),
            Student("Priya", 21),
            Student("Shayaan", 4)
        )

        adapter = StudentAdapter(studentList)
        rRecyclerView.adapter = adapter

    }
}
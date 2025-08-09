package com.jumbish.hw2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class StudentAdapter(private val studentList: List<Student>):RecyclerView.Adapter<StudentAdapter.StudentViewHolder>() {




    class StudentViewHolder(itemView : View):RecyclerView.ViewHolder(itemView) {
        val rtvName : TextView = itemView.findViewById(R.id.rtvName)
        val rtvAge : TextView = itemView.findViewById(R.id.rtvAge)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_student,parent,false)
        return StudentViewHolder(view)
    }

    override fun getItemCount(): Int {
return  studentList.size    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        val student = studentList[position]
        holder.rtvName.text = student.name
        holder.rtvAge.text = "Age : ${student.age}"
    }
}
package com.jeremy.a160420020_week4.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.jeremy.a160420020_week4.R
import com.jeremy.a160420020_week4.model.Student
import com.jeremy.a160420020_week4.util.loadImage

class StudentListAdapter(val studentList: ArrayList<Student>):
    RecyclerView.Adapter<StudentListAdapter.StudentViewHolder>() {
    class StudentViewHolder(var view: View): RecyclerView.ViewHolder(view)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.student_list_item, parent, false)
        return StudentViewHolder(view)
    }

    override fun getItemCount(): Int {
        return studentList.size
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        val id = holder.view.findViewById<TextView>(R.id.txtId)
        val btnDetail = holder.view.findViewById<Button>(R.id.btnDetail)
        holder.view.findViewById<TextView>(R.id.txtId).text = studentList[position].id
        holder.view.findViewById<TextView>(R.id.txtName).text = studentList[position].name
        holder.view.findViewById<Button>(R.id.btnDetail).setOnClickListener {
            val action = StudentListFragmentDirections.actionStudentDetail(studentList[position].id.toString())
            Navigation.findNavController(it).navigate(action)
        }

        var imageView = holder.view.findViewById<ImageView>(R.id.imageView)
        var progress = holder.view.findViewById<ProgressBar>(R.id.progressBar)
        imageView.loadImage(studentList[position].photoUrl, progress)
    }

    fun updateStudentList(newStudentList:ArrayList<Student>) {
        studentList.clear()
        studentList.addAll(newStudentList)
        notifyDataSetChanged()
    }

}
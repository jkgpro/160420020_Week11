package com.jeremy.a160420020_week4.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.jeremy.a160420020_week4.R
import com.jeremy.a160420020_week4.model.DetailStudent
import com.jeremy.a160420020_week4.model.Student

class StudentDetailFragment(val studentDetailList: ArrayList<DetailStudent>):
    RecyclerView.Adapter<StudentDetailFragment.StudentViewHolder>() {
    class StudentViewHolder(var view: View): RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.student_list_item, parent, false)
        return StudentViewHolder(view)
    }

    override fun getItemCount(): Int {
        return studentDetailList.size
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        holder.view.findViewById<EditText>(R.id.txtId).setText(studentDetailList[position].id)
        holder.view.findViewById<EditText>(R.id.txtName).setText(studentDetailList[position].name)
        holder.view.findViewById<EditText>(R.id.txtBOD).setText(studentDetailList[position].BOD)
        holder.view.findViewById<EditText>(R.id.txtPhone).setText( studentDetailList[position].phone)
        holder.view.findViewById<Button>(R.id.btnUpdate).setOnClickListener {

        }
    }

    fun updateStudentDetailList(newStudentList:ArrayList<DetailStudent>) {
        studentDetailList.clear()
        studentDetailList.addAll(newStudentList)
        notifyDataSetChanged()
    }

}
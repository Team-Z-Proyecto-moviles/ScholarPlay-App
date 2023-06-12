package com.example.scholarplay.ui.homepage.student.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.scholarplay.data.models.ClassModel
import com.example.scholarplay.databinding.ClassItemBinding

class StudentHomePageAdapter(private val clickListener: (ClassModel) -> Unit) : RecyclerView.Adapter<StudentHomePageViewHolder>(){

    private val classRooms = ArrayList<ClassModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentHomePageViewHolder {
        val binding = ClassItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return StudentHomePageViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return classRooms.size
    }

    override fun onBindViewHolder(holder: StudentHomePageViewHolder, position: Int) {
        val classRoom = classRooms[position]
        holder.bind(classRoom, clickListener)
    }

    fun setData(classList: List<ClassModel>){
        classRooms.clear()
        classRooms.addAll(classList)

    }
}
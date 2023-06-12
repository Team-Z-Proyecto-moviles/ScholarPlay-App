package com.example.scholarplay.ui.homepage.student.recyclerview

import androidx.recyclerview.widget.RecyclerView
import com.example.scholarplay.data.models.ClassModel
import com.example.scholarplay.databinding.ClassItemBinding
import com.example.scholarplay.network.dto.classroom.ClassRoomResponse

class StudentHomePageViewHolder(private val binding: ClassItemBinding): RecyclerView.ViewHolder(binding.root) {

    fun bind(classRoom: ClassModel){
        binding.classnameTextView.text = classRoom.name
        binding.teacherTextView.text = classRoom.teacher
    }
}
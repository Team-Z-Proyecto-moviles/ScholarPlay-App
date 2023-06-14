package com.example.scholarplay.ui.homepage.teacher.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.scholarplay.R
import com.example.scholarplay.data.models.ClassModel

class TeacherClassRoomAdapter(differCallback: DiffUtil.ItemCallback<ClassModel>):
    PagingDataAdapter<ClassModel, TeacherClassRoomAdapter.TeacherClassRoomViewHolder>(differCallback){


        class TeacherClassRoomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
            private val className: TextView = itemView.findViewById(R.id.classname_text_view)
            private val teacherName: TextView = itemView.findViewById(R.id.teacher_text_view)

            fun bind(classRoom: ClassModel?){
                className.text = classRoom?.name ?: "Classname"
                teacherName.text = ""
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeacherClassRoomViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater
            .inflate(R.layout.class_item, parent, false)
        return TeacherClassRoomViewHolder(view)
    }

    override fun onBindViewHolder(holder: TeacherClassRoomViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    object ClassRoomComparator : DiffUtil.ItemCallback<ClassModel>(){
        override fun areItemsTheSame(oldItem: ClassModel, newItem: ClassModel): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: ClassModel, newItem: ClassModel): Boolean {
            return oldItem == newItem
        }
    }





    }
package com.scholar.scholarplay.ui.homepage.student.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.scholar.scholarplay.R
import com.scholar.scholarplay.data.models.ClassModel

class StudentClassRoomAdapter(
    differCallback: DiffUtil.ItemCallback<ClassModel>,
    private val listener: OnItemClickListener
) : PagingDataAdapter<ClassModel, StudentClassRoomAdapter.StudentClassRoomViewHolder>(differCallback) {

    interface OnItemClickListener {
        fun onItemClick(classRoom: ClassModel)
    }

    inner class StudentClassRoomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val className: TextView = itemView.findViewById(R.id.classname_text_view)
        private val teacherName: TextView = itemView.findViewById(R.id.teacher_text_view)
        private val imageClass: ImageView = itemView.findViewById(R.id.image_class)

        fun bind(classRoom: ClassModel?) {
            className.text = classRoom?.name ?: "Classname"
            teacherName.text = classRoom?.teacher?.name ?: "Teacher"

            Glide
                .with(itemView)
                .load(classRoom?.image)
                .into(imageClass)
        }

        init {
            itemView.setOnClickListener {
                val position = bindingAdapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val classRoom = getItem(position)
                    classRoom?.let {
                        listener.onItemClick(it)
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentClassRoomViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.class_item, parent, false)
        return StudentClassRoomViewHolder(view)
    }

    override fun onBindViewHolder(holder: StudentClassRoomViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    object ClassRoomComparator : DiffUtil.ItemCallback<ClassModel>() {
        override fun areItemsTheSame(oldItem: ClassModel, newItem: ClassModel): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: ClassModel, newItem: ClassModel): Boolean {
            return oldItem == newItem
        }
    }
}
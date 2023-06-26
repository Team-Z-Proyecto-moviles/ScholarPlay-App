package com.example.scholarplay.ui.levelmenu.student.recyclerview

import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.scholarplay.R
import com.example.scholarplay.data.models.LevelModel

class LevelMenuAdapter(differCallback: DiffUtil.ItemCallback<LevelModel>):
    PagingDataAdapter<LevelModel, LevelMenuAdapter.LevelMenuViewHolder>(differCallback){

        class LevelMenuViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
            private val levelNumber: TextView = itemView.findViewById(R.id.level_number_text_view)

            fun bind(Level : LevelModel?, position: Int ){
                levelNumber.text = (position+1).toString()
            }


        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LevelMenuViewHolder {
       val inflater = LayoutInflater.from(parent.context)
        val view = inflater
            .inflate(R.layout.level_item, parent, false)
        return LevelMenuViewHolder(view)
    }

    override fun onBindViewHolder(holder: LevelMenuViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item,position)
    }

    object LevelComparator : DiffUtil.ItemCallback<LevelModel>(){
        override fun areItemsTheSame(oldItem: LevelModel, newItem: LevelModel): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: LevelModel, newItem: LevelModel): Boolean {
            return oldItem == newItem
        }
    }


}
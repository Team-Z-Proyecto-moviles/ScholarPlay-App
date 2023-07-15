package com.scholar.scholarplay.ui.levelmenu.student.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.scholar.scholarplay.R
import com.scholar.scholarplay.data.models.LevelModel

class LevelMenuAdapter(
    differCallback: DiffUtil.ItemCallback<LevelModel>,
    private val listener: OnItemClickListener
): PagingDataAdapter<LevelModel, LevelMenuAdapter.LevelMenuViewHolder>(differCallback){

    interface OnItemClickListener {
        fun OnItemClick(Level: LevelModel)
    }

      inner class LevelMenuViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
            private val levelNumber: TextView = itemView.findViewById(R.id.level_number_text_view)

            fun bind(Level : LevelModel?, position: Int ){
                levelNumber.text = (position+1).toString()
            }

          init {
              itemView.setOnClickListener {
                  val position = bindingAdapterPosition
                  if (position != RecyclerView.NO_POSITION){
                      val level = getItem(position)
                      level?.let {
                          listener.OnItemClick(it)
                      }
                  }
              }
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
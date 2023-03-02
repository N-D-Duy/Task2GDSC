package com.example.task2gdsc

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_menu2.view.*

class RecyclerViewAdapter(list:ArrayList<AppInfo>): RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {
    private val listApp = list
    private lateinit var mListener: OnItemClickListener

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    fun setOnClickListener(clickListener: OnItemClickListener) {
        mListener = clickListener
    }

    class ViewHolder(itemView: View, clickListener: OnItemClickListener) :
        RecyclerView.ViewHolder(itemView) {
        init {
            itemView.setOnClickListener {
                clickListener.onItemClick(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_menu2, parent, false)
        return ViewHolder(itemView, mListener)
    }


    override fun getItemCount(): Int {
        return listApp.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.apply {
            tv_name_app.text = listApp[position].nameApp
            image_icon_app.setImageDrawable(listApp[position].iconApp)
        }
        holder.itemView.setOnClickListener {
            mListener.onItemClick(position)
        }
    }
}
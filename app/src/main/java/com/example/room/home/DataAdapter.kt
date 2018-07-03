package com.example.room.home

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.room.R
import com.example.room.data.Citizen
import kotlinx.android.synthetic.main.item_data.view.*

class DataAdapter(private val citizens: List<Citizen>, val listener: DataAdapterListener): RecyclerView.Adapter<DataAdapter.DataViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_data,parent,false)
        return DataViewHolder(view)
    }

    override fun getItemCount() = citizens.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bindTo(citizens[position])
    }

    inner class DataViewHolder(val view:View): RecyclerView.ViewHolder(view) {

        init {
            view.container.setOnClickListener {
                listener.onDataTapped(citizens[adapterPosition])
            }
        }

        private val nameLabel: TextView by lazy {
            view.name
        }

        private val addressLabel: TextView by lazy {
            view.address
        }

        fun bindTo(data: Citizen) {
            nameLabel.text = data.name
            addressLabel.text = data.address
        }
    }

    interface DataAdapterListener {
        fun onDataTapped(data: Citizen)
    }

}
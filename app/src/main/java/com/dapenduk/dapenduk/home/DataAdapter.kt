package com.dapenduk.dapenduk.home

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.dapenduk.dapenduk.R
import com.dapenduk.dapenduk.data.Dapenduk
import kotlinx.android.synthetic.main.item_data.view.*

class DataAdapter(val datas: List<Dapenduk>): RecyclerView.Adapter<DataAdapter.DataViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_data,parent,false)
        return DataViewHolder(view)
    }

    override fun getItemCount() = datas.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bindTo(datas[position])
    }


    inner class DataViewHolder(val view:View): RecyclerView.ViewHolder(view) {

        private val nameLabel: TextView by lazy {
            view.name
        }

        private val addressLabel: TextView by lazy {
            view.address
        }

        fun bindTo(data: Dapenduk) {
            nameLabel.text = data.name
            addressLabel.text = data.address
        }
    }
}
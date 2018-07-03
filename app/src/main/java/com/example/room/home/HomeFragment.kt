package com.example.room.home


import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.room.R

import com.example.room.data.Citizen
import com.example.room.detail.DetailActivity

class HomeFragment : Fragment(), HomeScreen, DataAdapter.DataAdapterListener {
    lateinit var emptyLabel: TextView
    lateinit var rvData: RecyclerView

    override lateinit var presenter: HomePresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_home, container, false)

        with(root) {
            emptyLabel = findViewById(R.id.emptyLabel)
            rvData = findViewById(R.id.rvData)
            val layoutManager = LinearLayoutManager(activity)
            rvData.layoutManager = layoutManager
            rvData.addItemDecoration(DividerItemDecoration(activity,DividerItemDecoration.VERTICAL))
        }
        return root
    }

    override fun onResume() {
        super.onResume()
        presenter.loadDatas()
    }

    override fun bind(citizens: List<Citizen>) {
        emptyLabel.visibility = View.GONE
        val adapter = DataAdapter(citizens,this)
        rvData.adapter = adapter
        rvData.visibility = View.VISIBLE
    }

    override fun showDetail(id: String) {
        val i = Intent(activity, DetailActivity::class.java).apply {
            putExtra("id",id)
        }
        startActivity(i)
    }

    override fun show(message: String) {
        if (message=="empty")
            emptyLabel.visibility = View.VISIBLE
    }

    override fun onDataTapped(data: Citizen) {
        presenter.onDataClicked(data.id)
    }


    companion object {
        @JvmStatic
        fun newInstance() = HomeFragment()
    }
}

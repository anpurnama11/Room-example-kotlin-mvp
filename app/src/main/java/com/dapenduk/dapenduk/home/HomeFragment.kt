package com.dapenduk.dapenduk.home


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.dapenduk.dapenduk.R
import com.dapenduk.dapenduk.data.Dapenduk
import com.dapenduk.dapenduk.data.DapendukDatabase
import com.dapenduk.dapenduk.data.DapendukRepository
import com.dapenduk.dapenduk.util.AppExecutors

class HomeFragment : Fragment(),HomeScreen {
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
        presenter.loadDatas()
        return root
    }

    override fun bind(datas: List<Dapenduk>) {
        emptyLabel.visibility = View.GONE
        val adapter = DataAdapter(datas)
        rvData.adapter = adapter
        rvData.visibility = View.VISIBLE
    }

    override fun showDetail(id: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun show(message: String) {
        if (message=="empty")
            emptyLabel.visibility = View.VISIBLE
    }


    companion object {
        @JvmStatic
        fun newInstance() = HomeFragment()
    }
}

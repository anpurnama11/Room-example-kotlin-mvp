package com.dapenduk.dapenduk.home


import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dapenduk.dapenduk.R
import com.dapenduk.dapenduk.data.Dapenduk
import com.dapenduk.dapenduk.data.DapendukDatabase
import com.dapenduk.dapenduk.data.DapendukRepository
import com.dapenduk.dapenduk.util.AppExecutors

class HomeFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val database = DapendukDatabase.getInstance(context!!)
        DapendukRepository(AppExecutors(),database.dapendukDAO()).getDatas(object : DapendukRepository.DapendukRepositoryListener.getDatasListener {
            override fun onDatasAvailable(datas: List<Dapenduk>) {
                Log.d("show",datas.size.toString())
            }

            override fun ondatasNotAvailable() {
                Log.d("show","empty as fuck")
            }

        })
        return inflater.inflate(R.layout.fragment_home, container, false)
    }


    companion object {
        @JvmStatic
        fun newInstance() = HomeFragment()
    }
}

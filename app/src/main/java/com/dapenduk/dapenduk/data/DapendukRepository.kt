package com.dapenduk.dapenduk.data

import com.dapenduk.dapenduk.util.AppExecutors

class DapendukRepository(val appExecutors: AppExecutors,val dapendukDao: DapendukDao) {

    fun getDatas(listener: DapendukRepositoryListener.getDatasListener) {
        appExecutors.diskIO.execute {
            val datas = dapendukDao.getDatas()
            appExecutors.mainThread.execute {
                if (datas.isEmpty())
                    listener.ondatasNotAvailable()
                else
                    listener.onDatasAvailable(datas)
            }
        }
    }

    fun insertData(data: Dapenduk) {
        appExecutors.diskIO.execute {
            dapendukDao.insert(data)
        }
    }

    interface DapendukRepositoryListener {
        interface getDatasListener {
            fun onDatasAvailable(datas: List<Dapenduk>)
            fun ondatasNotAvailable()
        }
        interface getDataListener {
            fun onDataAvailable(data: Dapenduk)
            fun onDataNotAvailable()
        }
    }
}
package com.dapenduk.dapenduk.data

import com.dapenduk.dapenduk.util.AppExecutors

class DapendukRepository(val appExecutors: AppExecutors,val dapendukDao: DapendukDao) {

    fun getDatas(listener: DapendukRepositoryListener.GetDatasListener) {
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

    fun getData(id: String,listener: DapendukRepositoryListener.GetDataListener) {
        appExecutors.diskIO.execute {
            val data = dapendukDao.getDataById(id)
            appExecutors.mainThread.execute {
                if (data==null)
                    listener.onDataNotAvailable()
                else
                    listener.onDataAvailable(data)
            }
        }
    }

    fun insertData(data: Dapenduk) {
        appExecutors.diskIO.execute {
            dapendukDao.insert(data)
        }
    }

    fun delete(data: Dapenduk) {
        appExecutors.diskIO.execute {
            dapendukDao.delete(data)
        }
    }

    interface DapendukRepositoryListener {
        interface GetDatasListener {
            fun onDatasAvailable(datas: List<Dapenduk>)
            fun ondatasNotAvailable()
        }
        interface GetDataListener {
            fun onDataAvailable(data: Dapenduk)
            fun onDataNotAvailable()
        }
    }
}
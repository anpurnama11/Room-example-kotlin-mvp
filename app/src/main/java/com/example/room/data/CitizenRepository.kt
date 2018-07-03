package com.example.room.data

import com.example.room.util.AppExecutors

class CitizenRepository(val appExecutors: AppExecutors, val citizenDao: CitizenDao) {

    fun getDatas(listener: DapendukRepositoryListener.GetDatasListener) {
        appExecutors.diskIO.execute {
            val datas = citizenDao.getDatas()
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
            val data = citizenDao.getDataById(id)
            appExecutors.mainThread.execute {
                if (data==null)
                    listener.onDataNotAvailable()
                else
                    listener.onDataAvailable(data)
            }
        }
    }

    fun insertData(data: Citizen) {
        appExecutors.diskIO.execute {
            citizenDao.insert(data)
        }
    }

    fun delete(data: Citizen) {
        appExecutors.diskIO.execute {
            citizenDao.delete(data)
        }
    }

    interface DapendukRepositoryListener {
        interface GetDatasListener {
            fun onDatasAvailable(citizens: List<Citizen>)
            fun ondatasNotAvailable()
        }
        interface GetDataListener {
            fun onDataAvailable(data: Citizen)
            fun onDataNotAvailable()
        }
    }
}
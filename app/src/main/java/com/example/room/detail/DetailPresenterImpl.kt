package com.example.room.detail

import com.example.room.data.Citizen
import com.example.room.data.CitizenRepository

class DetailPresenterImpl(val screen: DetailScreen, val repository: CitizenRepository): DetailPresenter {

    init {
        screen.presenter = this
    }

    override fun loadData(id: String) {
        repository.getData(id, object : CitizenRepository.DapendukRepositoryListener.GetDataListener {
            override fun onDataAvailable(data: Citizen) {
                screen.bind(data)
            }

            override fun onDataNotAvailable() {
                screen.show("error")
            }

        })
    }

    override fun showUpdateForm() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun delete(data: Citizen) {
        repository.delete(data)
        screen.show("success")
        screen.back()
    }
}
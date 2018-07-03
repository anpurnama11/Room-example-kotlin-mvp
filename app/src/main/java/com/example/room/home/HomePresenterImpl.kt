package com.example.room.home

import com.example.room.data.Citizen
import com.example.room.data.CitizenRepository

class HomePresenterImpl(val screen: HomeScreen, private val repository: CitizenRepository): HomePresenter {

    init {
        screen.presenter = this
    }

    override fun loadDatas() {
        repository.getDatas(object : CitizenRepository.DapendukRepositoryListener.GetDatasListener {
            override fun onDatasAvailable(citizens: List<Citizen>) {
                screen.bind(citizens)
            }

            override fun ondatasNotAvailable() {
                screen.show("empty")
            }

        })
    }

    override fun onDataClicked(id: String) {
        screen.showDetail(id)
    }
}
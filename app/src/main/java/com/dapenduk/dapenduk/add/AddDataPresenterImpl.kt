package com.dapenduk.dapenduk.add

import com.dapenduk.dapenduk.data.Dapenduk
import com.dapenduk.dapenduk.data.DapendukRepository

class AddDataPresenterImpl(val screen: AddDataScreen, val repository: DapendukRepository):AddDataPresenter {

    init {
        screen.presenter = this
    }

    override fun onAddButtonTapped(name: String, sex: Int, placeOfBirth: String, dateOfBirth: String,
                                   occupation: String, address: String) {
        when {
            name.isBlank() -> {
                screen.show("name")
                return
            }
            sex<0 -> {
                screen.show("sex")
                return
            }
            address.isBlank() -> {
                screen.show("address")
                return
            }
            placeOfBirth.isBlank() -> {
                screen.show("pob")
                return
            }
            dateOfBirth.isBlank() -> {
                screen.show("dob")
                return
            }
            else -> {
                val data = Dapenduk(name= name,sex = sex, address =  address,
                        dateOfBirth = dateOfBirth,placeOfBirth =  placeOfBirth,occupation =  occupation)
                repository.insertData(data)
            }
        }
    }

    override fun onDateOfBirthTapped() {
        screen.showCalendar()
    }
}
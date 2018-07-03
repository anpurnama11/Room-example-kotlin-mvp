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
            }
            address.isBlank() -> {
                screen.show("address")
            }
            placeOfBirth.isBlank() -> {
                screen.show("pob")
            }
            dateOfBirth.isBlank() -> {
                screen.show("dob")
            }
            else -> {
                val data = Dapenduk(name= name,sex = sex, address =  address,
                        dateOfBirth = dateOfBirth,placeOfBirth =  placeOfBirth,occupation =  occupation)
                repository.insertData(data)
                screen.show("success")
            }
        }
    }

    override fun onDateOfBirthTapped() {
        screen.showCalendar()
    }
}
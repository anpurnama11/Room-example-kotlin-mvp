package com.example.room.add

import com.example.room.data.Citizen
import com.example.room.data.CitizenRepository

class AddDataPresenterImpl(val screen: AddDataScreen, val repository: CitizenRepository): AddDataPresenter {

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
                val data = Citizen(name= name,sex = sex, address =  address,
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
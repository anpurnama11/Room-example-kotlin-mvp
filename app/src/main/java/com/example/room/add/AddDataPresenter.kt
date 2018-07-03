package com.example.room.add

interface AddDataPresenter {
    fun onAddButtonTapped(name: String, sex: Int, placeOfBirth: String, dateOfBirth: String,
                          occupation: String, address: String)
    fun onDateOfBirthTapped()
}
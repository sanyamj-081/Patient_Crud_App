package com.example.crud.data.RegistrationForm

data class RegistrationFormUIState(
    var name : String = "",
    var phone_number : String = "",
    var email  :String = "",
    var aadhar_number :String = "",
    var address :String ="",

    var nameError :Boolean = false,
    var emailError :Boolean = false,
    var aadharNumberError : Boolean = false,
    var phoneNumberError : Boolean = false,
    var addressError:Boolean = false,
)
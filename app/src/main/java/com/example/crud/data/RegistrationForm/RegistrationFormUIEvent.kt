package com.example.crud.data.RegistrationForm


sealed class RegistrationFormUIEvent {
    data class EmailChanged(val emailChanged:String): RegistrationFormUIEvent()
    data class NameChanged(val nameChanged: String) : RegistrationFormUIEvent()
    data class AadharNumberChanged(val aadharNumberChanged: String) : RegistrationFormUIEvent()
    data class AddressChanged(val addressChanged: String) : RegistrationFormUIEvent()
    data class PhoneNumberChanged(val phoneNumberChanged: String) : RegistrationFormUIEvent()

    object RegisterButtonClicked : RegistrationFormUIEvent()



}
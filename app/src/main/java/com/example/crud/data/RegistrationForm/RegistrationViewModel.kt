package com.example.crud.data.RegistrationForm

import android.util.Log
import androidx.compose.material3.Text
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.crud.data.RegistrationUIState
import com.example.data.rules.Validator
import com.example.data.signup.SignupUIEvent
import com.example.data.signup.SignupViewModel
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.util.UUID
class RegistrationViewModel : ViewModel() {

    private val TAG = RegistrationViewModel::class.simpleName

    var registrationFormUIState = mutableStateOf(RegistrationFormUIState())

    var allValidationsPassed = mutableStateOf(false)

    fun onEvent(event: RegistrationFormUIEvent) {
        when (event) {
            is RegistrationFormUIEvent.NameChanged -> {
                registrationFormUIState.value = registrationFormUIState.value.copy(
                    name = event.nameChanged
                )
            }

            is RegistrationFormUIEvent.EmailChanged -> {
                registrationFormUIState.value = registrationFormUIState.value.copy(
                    email = event.emailChanged
                )

            }

            is RegistrationFormUIEvent.AadharNumberChanged -> {
                registrationFormUIState.value = registrationFormUIState.value.copy(
                    aadhar_number = event.aadharNumberChanged
                )

            }


            is RegistrationFormUIEvent.AddressChanged -> {
                registrationFormUIState.value = registrationFormUIState.value.copy(
                    address = event.addressChanged
                )

            }

            is RegistrationFormUIEvent.PhoneNumberChanged -> {
                registrationFormUIState.value = registrationFormUIState.value.copy(
                    phone_number = event.phoneNumberChanged
                )
            }

            RegistrationFormUIEvent.RegisterButtonClicked -> {

                registerform()
                Log.d("regbtn","reg")
            }

        }
        validateDataWithRules()
    }

    private fun validateDataWithRules() {

        val NameResult = Validator.validateFirstName(
            fName = registrationFormUIState.value.name
        )

        val PhoneNumberResult = Validator.validateLastName(
            lName = registrationFormUIState.value.phone_number
        )

        val EmailResult = Validator.validateEmail(
            email = registrationFormUIState.value.email
        )


        val AddressResult = Validator.validatePassword(
            password = registrationFormUIState.value.address
        )

        val AadharResult = Validator.validateAadhar(
            aadhar  = registrationFormUIState.value.aadhar_number
        )

        registrationFormUIState.value = registrationFormUIState.value.copy(
            nameError = NameResult.status,
            phoneNumberError = PhoneNumberResult.status,
            emailError = EmailResult.status,
            addressError = AddressResult.status,
            aadharNumberError = AadharResult.status
        )

        Log.d(TAG, "Inside_validateDataWithRules")

        allValidationsPassed.value = NameResult.status && PhoneNumberResult.status &&
                EmailResult.status && AadharResult.status && AddressResult.status

    }


   fun registerform() {
        val formData = RegistrationFormUIState(
            name = registrationFormUIState.value.name,
            phone_number = registrationFormUIState.value.phone_number,
            email = registrationFormUIState.value.email,
            aadhar_number = registrationFormUIState.value.aadhar_number,
            address = registrationFormUIState.value.address
        )

        Log.d("FormData", "Name: ${formData.name}")
        Log.d("FormData", "Phone Number: ${formData.phone_number}")
        Log.d("FormData", "Email: ${formData.email}")
        Log.d("FormData", "Aadhar Number: ${formData.aadhar_number}")
        Log.d("FormData", "Address: ${formData.address}")


        storeFormDataInFirebase(formData)

        Log.d("button clked", "registerform")


    }

    private fun storeFormDataInFirebase(formData: RegistrationFormUIState) {
        // Initialize the Firebase Realtime Database reference

        Log.d("Firebase", "Storing data in Firebase")
        val database = FirebaseDatabase.getInstance()
        val reference = database.reference.child("users").push()

        // Store the formData as a new entry in the "users" collection
        reference.setValue(formData)
            .addOnSuccessListener {

                Log.d("Firebase", "Data added successfully")
            }
            .addOnFailureListener { e ->
                Log.e("Firebase", "Error writing data to Firebase: ${e.message}")
            }
    }
}
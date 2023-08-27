package com.example.data.rules

import android.util.Log

object Validator {


    fun validateFirstName(name: String): ValidationResult {
        return ValidationResult(
            (!name.isNullOrEmpty() && name.length >= 2)
        )

    }

    fun validateLastName(password: String): ValidationResult {
        return ValidationResult(
            (!password.isNullOrEmpty() && password.length >= 2)
        )
    }

    fun validateEmail(email: String): ValidationResult {
        return ValidationResult(
            (!email.isNullOrEmpty())
        )
    }

    fun validatePassword(password: String): ValidationResult {
        return ValidationResult(
            (!password.isNullOrEmpty() && password.length >= 4)
        )
    }

    fun validatePrivacyPolicyAcceptance(statusValue:Boolean):ValidationResult{
        return ValidationResult(
            statusValue
        )
    }
    fun validateAadhar(aadhar: String):ValidationResult{
        return ValidationResult(
            (!aadhar.isNullOrEmpty() && aadhar.length >= 4)
        )
    }

}

data class ValidationResult(
    val status: Boolean = false
)









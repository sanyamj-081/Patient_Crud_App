package com.example.crud.navigation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf


sealed class Screen(s: String) {

    object SignUpScreen : Screen("signup_screen")
    object TermsAndConditionsScreen : Screen("ter/{id}")
    object LoginScreen : Screen("signup_screen")
    object HomeScreen : Screen("signup_screen")
    object RegistrationFormScreen : Screen("signup_screen")
}


object CrudAppRouter {

    var currentScreen: MutableState<Screen> = mutableStateOf(Screen.SignUpScreen)

    fun navigateTo(destination: Screen) {
        currentScreen.value = destination
    }
}
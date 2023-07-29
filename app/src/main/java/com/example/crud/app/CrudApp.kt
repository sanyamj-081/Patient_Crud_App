package com.example.crud.app

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.crud.Screens.HomeScreen
import com.example.crud.Screens.LoginScreen
import com.example.crud.Screens.SignUpScreen
import com.example.crud.Screens.TermsAndConditionsScreen
import com.example.crud.data.home.HomeViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.crud.Screens.register
import com.example.crud.navigation.CrudAppRouter
import com.example.crud.navigation.Screen

@Composable
fun CrudApp(homeViewModel: HomeViewModel = viewModel()) {

    val currentScreen by remember { CrudAppRouter.currentScreen }

    LaunchedEffect(Unit) {
        homeViewModel.checkForActiveSession()
        if (homeViewModel.isUserLoggedIn.value == true) {
            CrudAppRouter.navigateTo(Screen.HomeScreen)
        }
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {
        if (homeViewModel.isUserLoggedIn.value == true) {
            CrudAppRouter.navigateTo(Screen.HomeScreen)
        }

        Crossfade(targetState = CrudAppRouter.currentScreen) { currentState ->
            when (currentState.value) {
                is Screen.SignUpScreen -> {
                    SignUpScreen()
                }

                is Screen.TermsAndConditionsScreen -> {
                    TermsAndConditionsScreen()
                }

                is Screen.LoginScreen -> {
                    LoginScreen()
                }

                is Screen.HomeScreen -> {
                    HomeScreen()
                }

                is Screen.RegistrationFormScreen -> {
                    register()
                }
            }

        }
    }
}

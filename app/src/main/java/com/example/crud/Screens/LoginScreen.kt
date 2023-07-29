package com.example.crud.Screens

import android.util.Log
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.crud.Components.ButtonComponent
import com.example.crud.Components.ClickableLoginTextComponent
import com.example.crud.Components.DividerTextComponent
import com.example.crud.Components.HeadingTextComponent
import com.example.crud.Components.MyTextFieldComponent
import com.example.crud.Components.NormalTextComponent
import com.example.crud.Components.PasswordTextFieldComponent
import com.example.crud.Components.UnderlinedTextComponent
import com.example.crud.R
import com.example.crud.navigation.CrudAppRouter
import com.example.crud.navigation.Screen
import com.example.crud.navigation.SystemBackButtonHandler
import com.example.data.login.LoginViewModel
import com.example.login.LoginUIEvent

@Composable
fun LoginScreen(loginViewModel: LoginViewModel = viewModel()) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(28.dp)
    ) {
        Column( 
            modifier = Modifier
                .fillMaxSize()
        ) {


            NormalTextComponent(value = stringResource(id = R.string.login))
            HeadingTextComponent(value = stringResource(id = R.string.welcome))
            Spacer(modifier = Modifier.height(40.dp))
          
            MyTextFieldComponent(
                labelValue = stringResource(id = R.string.email),
                painterResource = painterResource(id = R.drawable.message),
                onTextSelected = {
                    loginViewModel.onEvent(LoginUIEvent.EmailChanged(it))
                },
                errorStatus = loginViewModel.loginUIState.value.emailError
            )
            PasswordTextFieldComponent(
                labelValue = stringResource(id = R.string.password),
                painterResource(id = R.drawable.lock),
                onTextSelected = {
                    loginViewModel.onEvent(LoginUIEvent.PasswordChanged(it))
                },
                errorStatus = loginViewModel.loginUIState.value.passwordError
            )
            Spacer(modifier = Modifier.height(40.dp))
            
            UnderlinedTextComponent(value = stringResource(id = R.string.forgot_password))

            ButtonComponent(
                value = stringResource(id = R.string.login),
                onButtonClicked = {
                    Log.d("login Button", "Button Clicked")
                    loginViewModel.onEvent(LoginUIEvent.LoginButtonClicked)
                },
                isEnabled = loginViewModel.allValidationsPassed.value
            )

            DividerTextComponent()

            ClickableLoginTextComponent(
                tryingToLogin = false,
                onTextSelected = {
                    CrudAppRouter.navigateTo(Screen.SignUpScreen)
                }
            )


        }

    }

    SystemBackButtonHandler {
        CrudAppRouter.navigateTo(Screen.SignUpScreen)
    }
}

@Preview
@Composable
fun LoginScreenPreview() {
    LoginScreen()
}
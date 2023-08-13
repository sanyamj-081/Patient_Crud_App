package com.example.crud.Screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.crud.Components.ButtonComponent
import com.example.crud.Components.MyTextFieldComponent
import com.example.crud.Components.PasswordTextFieldComponent
import com.example.crud.R
import com.example.crud.data.RegistrationForm.RegistrationFormUIEvent
import com.example.crud.data.RegistrationForm.RegistrationFormUIState
import com.example.crud.data.RegistrationForm.RegistrationViewModel
import com.example.crud.data.RegistrationUIState
import com.example.crud.navigation.CrudAppRouter
import com.example.crud.navigation.Screen
import com.example.crud.navigation.SystemBackButtonHandler
import com.example.data.signup.SignupUIEvent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.util.jar.Attributes.Name

@Composable
fun register(registrationViewModel: RegistrationViewModel = viewModel()) {

    Column(modifier = Modifier.fillMaxSize() ) {
        
        MyTextFieldComponent(
            labelValue = stringResource(id = R.string.first_name),
            painterResource = painterResource(id = R.drawable.profile),
            onTextSelected = {
                             registrationViewModel.onEvent(RegistrationFormUIEvent.NameChanged(it))
            },
            errorStatus = registrationViewModel.registrationFormUIState.value.nameError
        )
        Spacer(modifier = Modifier.height(20.dp))
        MyTextFieldComponent(
            labelValue = stringResource(id = R.string.email),
            painterResource = painterResource(id = R.drawable.message),
            onTextSelected = {
                registrationViewModel.onEvent(RegistrationFormUIEvent.EmailChanged(it))
            },
            errorStatus = registrationViewModel.registrationFormUIState.value.emailError
        )
        Spacer(modifier = Modifier.height(20.dp))

        MyTextFieldComponent(
            labelValue = stringResource(id = R.string.password),
            painterResource = painterResource(id = R.drawable.ic_lock),
            onTextSelected = {
                registrationViewModel.onEvent(RegistrationFormUIEvent.PhoneNumberChanged(it))
            },
            errorStatus = registrationViewModel.registrationFormUIState.value.phoneNumberError
        )
        Spacer(modifier = Modifier.height(20.dp))

        MyTextFieldComponent(
            labelValue = stringResource(id = R.string.aadhar),
            painterResource = painterResource(id = R.drawable.profile),
            onTextSelected = {
                             registrationViewModel.onEvent(RegistrationFormUIEvent.AadharNumberChanged(it))
            },
            errorStatus = registrationViewModel.registrationFormUIState.value.aadharNumberError
        )
        Spacer(modifier = Modifier.height(20.dp))

        MyTextFieldComponent(
            labelValue = stringResource(id = R.string.address),
            painterResource = painterResource(id = R.drawable.profile),
            onTextSelected = {
                             registrationViewModel.onEvent(RegistrationFormUIEvent.AddressChanged(it))
            },
            errorStatus = registrationViewModel.registrationFormUIState.value.addressError
        )
        Spacer(modifier = Modifier.height(20.dp))
        
        ButtonComponent(
            value = stringResource(id = R.string.register),
            onButtonClicked = {
                              registrationViewModel.onEvent(RegistrationFormUIEvent.RegisterButtonClicked)
            },
            isEnabled = true
        )

    }
    SystemBackButtonHandler {
        CrudAppRouter.navigateTo(Screen.HomeScreen)
    }


}
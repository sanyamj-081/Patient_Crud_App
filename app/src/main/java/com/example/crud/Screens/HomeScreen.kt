package com.example.crud.Screens

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.crud.Components.AppToolbar
import com.example.crud.Components.NavigationDrawerBody
import com.example.crud.Components.NavigationDrawerHeader
import com.example.crud.data.home.HomeViewModel
import com.example.crud.R
import kotlinx.coroutines.launch
import androidx.compose.material.rememberScaffoldState
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import com.example.crud.Components.ButtonComponent
import com.example.crud.navigation.CrudAppRouter
import com.example.crud.navigation.Screen
import com.example.crud.navigation.SystemBackButtonHandler
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase


@Composable
fun HomeScreen(homeViewModel: HomeViewModel = viewModel()) {

    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()

    val firebaseAuth = FirebaseAuth.getInstance()
    val currentUser = firebaseAuth.currentUser

    val databaseReference = FirebaseDatabase.getInstance().getReference("users")
    val userId = currentUser?.uid ?: ""
    val userReference = databaseReference.child(userId);

// Check if a user is currently authenticated


    homeViewModel.getUserData()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            AppToolbar(toolbarTitle = stringResource(id = R.string.home),
                logoutButtonClicked = {
                    homeViewModel.logout()
                },
                navigationIconClicked = {
                    coroutineScope.launch {
                        scaffoldState.drawerState.open()
                    }
                }
            )
        },
        drawerGesturesEnabled = scaffoldState.drawerState.isOpen,
        drawerContent = {
            NavigationDrawerHeader(homeViewModel.emailId.value)
            NavigationDrawerBody(navigationDrawerItems = homeViewModel.navigationItemsList,
                onNavigationItemClicked = {
                    Log.d("ComingHere","inside_NavigationItemClicked")
                    Log.d("ComingHere","${it.itemId} ${it.title}")
                })
        }

    ) { paddingValues ->

        Surface(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(paddingValues)
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(modifier = Modifier
                            .fillMaxSize()
                            .padding(0.dp, 50.dp, 0.dp, 0.dp)) {

//            Row(modifier = Modifier.fillMaxHeight()) {
                            ButtonComponent(
                                value = stringResource(id = R.string.create),
                                onButtonClicked = {
                                  CrudAppRouter.navigateTo(Screen.RegistrationFormScreen)
                                }
                            )

//                            ButtonComponent(
//                                value = stringResource(id = R.string.read),
//                                onButtonClicked = { /*TODO*/ }
//                            )
//
////            }
////            Row(modifier = Modifier.fillMaxSize()) {
//                            ButtonComponent(
//                                value = stringResource(id = R.string.update),
//                                onButtonClicked = { /*TODO*/ }
//                            )

                            ButtonComponent(
                                value = stringResource(id = R.string.delete),
                                onButtonClicked = {

                                    userReference.removeValue()
                                    }
                            )

                            SystemBackButtonHandler {
                                CrudAppRouter.navigateTo(Screen.LoginScreen)
                            }



                        }
                    }
            }
        }
    }
}



@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}
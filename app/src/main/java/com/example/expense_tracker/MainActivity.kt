package com.example.expense_tracker

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.expense_tracker.classes.Screen
import com.example.expense_tracker.composables.custom_composables.CustomBottomNavigation
import com.example.expense_tracker.composables.custom_composables.CustomText
import com.example.expense_tracker.composables.navigation.Nav
import com.example.expense_tracker.strings.Routes
import com.example.expense_tracker.ui.theme.Expense_trackerTheme

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            var currentScreen by remember {
                mutableStateOf<Screen>(Screen.Home)
            }
            var bottomBarState by remember {
                mutableStateOf(false)
            }

            val navController = rememberNavController()

            // Subscribe to navBackStackEntry, required to get current route
            val navBackStackEntry by navController.currentBackStackEntryAsState()

            // Control BottomBar
            when (navBackStackEntry?.destination?.route) {
                Routes.dashboard, Routes.expenseEntry, Routes.reports, Routes.settings -> {
                    bottomBarState = true
                }

                Routes.signIn, Routes.signUp, Routes.forgotPassword -> {
                    bottomBarState = false
                }
            }

            Expense_trackerTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background,

                    ) {
                    Scaffold(
                        bottomBar = {
                            AnimatedVisibility(visible = bottomBarState) {
                                CustomBottomNavigation(
                                    currentScreenId = currentScreen.id,
                                    onItemSelect = {
                                        Log.d("Current Tab", currentScreen.id)
                                        currentScreen = it
                                        when (currentScreen) {
                                            Screen.Home -> {
                                                navController.navigate(Routes.dashboard)
                                            }
                                            Screen.Search -> {
                                                navController.navigate(Routes.expenseEntry)
                                            }
                                            Screen.Settings -> {
                                                navController.navigate(Routes.settings)
                                            }
                                            Screen.Reports -> {
                                                navController.navigate(Routes.reports)
                                            }
                                        }
                                    },
                                )
                            }
                        }
                    ) { innerPadding ->
                        Nav(
                            navController = navController,
                            modifier = Modifier.padding(innerPadding),
                        )
                    }

                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    CustomText(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Expense_trackerTheme {
        Greeting("Android")
    }
}
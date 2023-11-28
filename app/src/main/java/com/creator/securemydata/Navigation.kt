package com.creator.securemydata

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.creator.securemydata.ui.screens.DecryptScreen
import com.creator.securemydata.ui.screens.EncryptScreen
import com.creator.securemydata.ui.screens.Home

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.Home.route) {
        composable(route = Screen.Home.route) {
            Home(navController = navController)
        }
        composable(route = Screen.EncryptScreen.route) {
            EncryptScreen(navController)
        }
        composable(route = Screen.DecryptScreen.route) {
            DecryptScreen(navController)
        }
    }
}
package com.creator.securemydata

sealed class Screen(val route: String) {
    object Home : Screen("Home")
    object EncryptScreen : Screen("Encrypt")
    object DecryptScreen : Screen("Decrypt")
}
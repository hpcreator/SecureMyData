package com.creator.securemydata

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.creator.securemydata.ui.component.SetStatusBarColor
import com.creator.securemydata.ui.theme.SecureMyDataTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SecureDataApp()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SecureDataApp() {
    SecureMyDataTheme {
        SetStatusBarColor(color = Color(0xFF00ff87))
        Scaffold(modifier = Modifier.fillMaxSize(), content = {
            Log.e("TAG", "ComposeDemoApp: $it")
            Navigation()
        })
    }
}

@Preview(name = "LightTheme", showBackground = true, showSystemUi = true)
@Preview(name = "DarkTheme", showBackground = true, showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun MainAppPreview() {
    SecureMyDataTheme {
        Navigation()
    }
}

package com.creator.securemydata.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.creator.securemydata.R
import com.creator.securemydata.Screen
import com.creator.securemydata.ui.component.SetStatusBarColor
import com.creator.securemydata.ui.theme.SecureMyDataTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home(navController: NavHostController?) {
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp
    Box(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxWidth()
            .height(screenHeight)
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF00ff87), Color(0xFF60efff)
                    )
                )
            )
    ) {
        SetStatusBarColor(color = Color(0xFF00ff87))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Card(
                onClick = { navController?.navigate(Screen.EncryptScreen.route) },
                shape = RoundedCornerShape(15.dp),
                modifier = Modifier
                    .padding(50.dp)
                    .fillMaxWidth()
                    .padding(30.dp)
                    .background(
                        brush = Brush.radialGradient(
                            colors = listOf(
                                Color(0xFF60efff), Color(0xFF00ff87)
                            )
                        )
                    )
                    .shadow(15.dp, shape = RectangleShape)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_lock),
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                    colorFilter = ColorFilter.tint(Color.White),
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            brush = Brush.radialGradient(
                                colors = listOf(
                                    Color(0xFF00ff87), Color(0xFF60efff)
                                )
                            )
                        )
                )
            }

            Card(
                onClick = { navController?.navigate(Screen.DecryptScreen.route) },
                shape = RoundedCornerShape(15.dp),
                modifier = Modifier
                    .padding(50.dp)
                    .fillMaxWidth()
                    .padding(30.dp)
                    .background(
                        brush = Brush.radialGradient(
                            colors = listOf(
                                Color(0xFF00ff87), Color(0xFF60efff),
                            )
                        )
                    )
                    .shadow(15.dp, shape = RectangleShape)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_lock_open),
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                    colorFilter = ColorFilter.tint(Color.White),
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            brush = Brush.radialGradient(
                                colors = listOf(
                                    Color(0xFF60efff), Color(0xFF00ff87)
                                )
                            )
                        )
                )
            }
        }
    }
}

@Preview(name = "LightTheme", showBackground = true, showSystemUi = true)
@Composable
fun HomePreview() {
    SecureMyDataTheme {
        Home(null)
    }
}
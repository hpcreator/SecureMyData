package com.creator.securemydata.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.creator.securemydata.R
import com.creator.securemydata.Screen
import com.creator.securemydata.ui.component.SetStatusBarColor
import com.creator.securemydata.ui.theme.SecureMyDataTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home(navController: NavHostController?) {
    val configuration = LocalConfiguration.current
    //val screenHeight = configuration.screenHeightDp.dp

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF00ff87), Color(0xFF60efff)
                    )
                )
            ), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SetStatusBarColor(color = Color(0xFF00ff87))
        Text(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .fillMaxWidth()
                .padding(30.dp, 20.dp, 30.dp, 0.dp),
            text = stringResource(id = R.string.str_home_label),
            fontWeight = FontWeight.SemiBold,
            color = if (isSystemInDarkTheme()) Color.DarkGray else Color.DarkGray,
            fontSize = 24.sp,
            textAlign = TextAlign.Center
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(30.dp, 10.dp, 30.dp, 0.dp),
            text = stringResource(id = R.string.str_home_description),
            fontWeight = FontWeight.Normal,
            color = if (isSystemInDarkTheme()) Color.DarkGray else Color.DarkGray,
            fontSize = 16.sp,
            textAlign = TextAlign.Center
        )
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .weight(1f)
        ) {
            Column {
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth()
                        .padding(10.dp)
                        .weight(1f)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxHeight()
                            .fillMaxWidth()
                    ) {
                        Card(
                            onClick = { navController?.navigate(Screen.EncryptScreen.route) },
                            shape = RoundedCornerShape(15.dp),
                            modifier = Modifier
                                .padding(30.dp, 30.dp, 30.dp, 0.dp)
                                .background(
                                    brush = Brush.radialGradient(
                                        colors = listOf(
                                            Color(0xFF60efff), Color(0xFF00ff87)
                                        )
                                    )
                                )
                                .shadow(15.dp, shape = RectangleShape)
                                .weight(1f)
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.ic_lock),
                                contentDescription = "",
                                colorFilter = ColorFilter.tint(Color.White),
                                modifier = Modifier
                                    .background(
                                        brush = Brush.radialGradient(
                                            colors = listOf(
                                                Color(0xFF00ff87), Color(0xFF60efff)
                                            )
                                        )
                                    )
                                    .fillMaxWidth()
                                    .fillMaxHeight()
                            )
                        }
                        Text(
                            modifier = Modifier
                                .padding(10.dp)
                                .fillMaxWidth(),
                            text = stringResource(id = R.string.str_encrypt),
                            fontWeight = FontWeight.Medium,
                            color = if (isSystemInDarkTheme()) Color.DarkGray else Color.DarkGray,
                            fontSize = 18.sp,
                            textAlign = TextAlign.Center
                        )
                    }
                }
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth()
                        .padding(10.dp)
                        .weight(1f)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxHeight()
                            .fillMaxWidth()
                    ) {
                        Card(
                            onClick = { navController?.navigate(Screen.DecryptScreen.route) },
                            shape = RoundedCornerShape(15.dp),
                            modifier = Modifier
                                .padding(30.dp, 30.dp, 30.dp, 0.dp)
                                .background(
                                    brush = Brush.radialGradient(
                                        colors = listOf(
                                            Color(0xFF60efff), Color(0xFF00ff87)
                                        )
                                    )
                                )
                                .shadow(15.dp, shape = RectangleShape)
                                .weight(1f)
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.ic_lock_open),
                                contentDescription = "",
                                colorFilter = ColorFilter.tint(Color.White),
                                modifier = Modifier
                                    .background(
                                        brush = Brush.radialGradient(
                                            colors = listOf(
                                                Color(0xFF60efff), Color(0xFF00ff87)
                                            )
                                        )
                                    )
                                    .fillMaxWidth()
                                    .fillMaxHeight()

                            )
                        }
                        Text(
                            modifier = Modifier
                                .padding(10.dp)
                                .fillMaxWidth(),
                            text = stringResource(id = R.string.str_decrypt),
                            fontWeight = FontWeight.Medium,
                            color = if (isSystemInDarkTheme()) Color.DarkGray else Color.DarkGray,
                            fontSize = 18.sp,
                            textAlign = TextAlign.Center
                        )
                    }
                }
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
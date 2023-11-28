package com.creator.securemydata.ui.screens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ClipboardManager
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.creator.securemydata.R
import com.creator.securemydata.ui.component.OutlinedTextBox
import com.creator.securemydata.ui.component.SetStatusBarColor
import com.creator.securemydata.ui.theme.Purple40
import com.creator.securemydata.ui.theme.Purple80
import com.creator.securemydata.ui.theme.SecureMyDataTheme
import com.creator.securemydata.utils.EncryptionHelper

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun DecryptScreen(navController: NavHostController?) {
    val inputData = remember { mutableStateOf(TextFieldValue("")) }
    val decryptedText = remember { mutableStateOf("") }
    val clipboardManager: ClipboardManager = LocalClipboardManager.current
    val context = LocalContext.current
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp
    Box(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .height(screenHeight)
            .fillMaxWidth()
            .background(color = if (isSystemInDarkTheme()) Color.DarkGray else Color.LightGray)
    ) {
        SetStatusBarColor(color = Color.LightGray)
        Card(modifier = Modifier.padding(top = 20.dp, start = 10.dp), onClick = {
            navController?.navigateUp()
        }) {
            Image(
                modifier = Modifier
                    .background(color = Color(0x4DFFFFFF))
                    .padding(10.dp),
                painter = painterResource(id = R.drawable.ic_back),
                contentDescription = "",
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_logo),
                contentDescription = "",
                modifier = Modifier.padding(30.dp)
            )
            Text(
                text = "Secure your texts with Us",
                fontWeight = FontWeight.Medium,
                color = if (isSystemInDarkTheme()) Color.White else Color.DarkGray,
                fontStyle = FontStyle.Italic,
                fontSize = 24.sp
            )
            OutlinedTextBox(
                label = "Input Text",
                state = inputData.value,
                onStateChange = { inputData.value = it },
                cornerRadius = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(30.dp, 20.dp, 30.dp, 0.dp)
            )
            Button(
                onClick = {
                    val data = EncryptionHelper.decrypt(inputData.value.text)
                    decryptedText.value = data
                }, shape = RoundedCornerShape(10.dp), colors = ButtonDefaults.buttonColors(
                    containerColor = if (isSystemInDarkTheme()) Purple80 else Purple40,
                ), modifier = Modifier
                    .padding(30.dp, 20.dp, 30.dp, 10.dp)
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Text(text = "Decrypt", fontSize = 18.sp)
            }

            Text(
                text = "Here is your Decrypted string:",
                Modifier.padding(top = 20.dp),
                fontWeight = FontWeight.Normal,
                color = if (isSystemInDarkTheme()) Color.White else Color.DarkGray,
                fontStyle = FontStyle.Normal,
                fontSize = 18.sp
            )

            SelectionContainer {
                Text(
                    text = decryptedText.value,
                    Modifier.padding(top = 20.dp),
                    fontWeight = FontWeight.Bold,
                    color = if (isSystemInDarkTheme()) Color.White else Color.Black,
                    fontStyle = FontStyle.Italic,
                    fontSize = 14.sp
                )
            }
            if (decryptedText.value != "") {
                OutlinedButton(
                    onClick = {
                        clipboardManager.setText(AnnotatedString(decryptedText.value))
                        Toast.makeText(context, "Text copied", Toast.LENGTH_SHORT).show()
                        decryptedText.value = ""
                        inputData.value = TextFieldValue("")
                    },
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier
                        .padding(30.dp, 20.dp, 30.dp, 10.dp)
                        .fillMaxWidth()
                        .height(50.dp)
                ) {
                    Text(text = "Copy Text", fontSize = 18.sp)
                }
            }

        }
    }
}

@Preview(name = "LightTheme", showBackground = true, showSystemUi = true)
@Composable
fun DecryptPreview() {
    SecureMyDataTheme {
        DecryptScreen(null)
    }
}

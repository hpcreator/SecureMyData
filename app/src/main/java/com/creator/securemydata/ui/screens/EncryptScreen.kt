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
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ClipboardManager
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
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

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun EncryptScreen(navController: NavHostController?) {
    val inputData = remember { mutableStateOf(TextFieldValue("")) }
    val inputKey = remember { mutableStateOf(TextFieldValue("")) }
    val encryptedText = remember { mutableStateOf("") }
    val clipboardManager: ClipboardManager = LocalClipboardManager.current
    val keyboardController = LocalSoftwareKeyboardController.current
    val context = LocalContext.current

    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp

    Box(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .height(screenHeight)
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
                modifier = Modifier.padding(50.dp)
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(30.dp, 20.dp, 30.dp, 0.dp),
                text = stringResource(id = R.string.str_secure_content_with_us),
                fontWeight = FontWeight.Medium,
                color = if (isSystemInDarkTheme()) Color.White else Color.DarkGray,
                fontStyle = FontStyle.Italic,
                fontSize = 24.sp,
                textAlign = TextAlign.Center
            )
            OutlinedTextBox(
                label = stringResource(id = R.string.str_enter_content),
                state = inputData.value,
                onStateChange = { inputData.value = it },
                cornerRadius = RoundedCornerShape(12.dp),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next, autoCorrect = false),
                keyboardActions = KeyboardActions(onDone = { keyboardController?.hide() }),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(30.dp, 20.dp, 30.dp, 0.dp)
            )
            OutlinedTextBox(
                label = stringResource(id = R.string.str_enter_key),
                state = inputKey.value,
                onStateChange = { inputKey.value = it },
                cornerRadius = RoundedCornerShape(12.dp),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next, autoCorrect = false),
                keyboardActions = KeyboardActions(onDone = { keyboardController?.hide() }),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(30.dp, 20.dp, 30.dp, 0.dp)
            )
            Button(
                onClick = {
                    if (inputData.value.text.isNotEmpty() && inputKey.value.text.isNotEmpty()) {
                        val data = EncryptionHelper.encrypt(inputData.value.text, inputKey.value.text)
                        encryptedText.value = data
                    } else {
                        //show error to text box
                    }
                }, shape = RoundedCornerShape(10.dp), colors = ButtonDefaults.buttonColors(
                    containerColor = if (isSystemInDarkTheme()) Purple80 else Purple40,
                ), modifier = Modifier
                    .padding(30.dp, 20.dp, 30.dp, 10.dp)
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Text(text = stringResource(id = R.string.str_encrypt), fontSize = 18.sp)
            }

            Text(
                text = stringResource(id = R.string.str_here_is_encrypted_string),
                Modifier.padding(top = 20.dp),
                fontWeight = FontWeight.Normal,
                color = if (isSystemInDarkTheme()) Color.White else Color.DarkGray,
                fontStyle = FontStyle.Normal,
                fontSize = 18.sp
            )

            SelectionContainer {
                Text(
                    text = encryptedText.value,
                    Modifier.padding(top = 20.dp),
                    fontWeight = FontWeight.Bold,
                    color = if (isSystemInDarkTheme()) Color.White else Color.Black,
                    fontStyle = FontStyle.Italic,
                    fontSize = 14.sp
                )
            }
            if (encryptedText.value != "") {
                OutlinedButton(
                    onClick = {
                        clipboardManager.setText(AnnotatedString(encryptedText.value))
                        Toast.makeText(context, "Text copied", Toast.LENGTH_SHORT).show()
                        encryptedText.value = ""
                        inputData.value = TextFieldValue("")
                        inputKey.value = TextFieldValue("")
                    },
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier
                        .padding(30.dp, 20.dp, 30.dp, 10.dp)
                        .fillMaxWidth()
                        .height(50.dp)
                ) {
                    Text(text = stringResource(id = R.string.str_copy_text), fontSize = 18.sp)
                }
            }

        }
    }
}

@Preview(name = "LightTheme", showBackground = true, showSystemUi = true)
@Composable
fun EncryptPreview() {
    SecureMyDataTheme {
        EncryptScreen(null)
    }
}
package com.creator.securemydata.ui.component

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OutlinedTextBox(
    modifier: Modifier = Modifier,
    label: String,
    placeholder: String? = null,
    textStyle: TextStyle = LocalTextStyle.current,
    state: TextFieldValue,
    onStateChange: (TextFieldValue) -> Unit,
    cornerRadius: RoundedCornerShape = RoundedCornerShape(5.dp),
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    singleLine: Boolean = false,
    maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE,
) {
    OutlinedTextField(
        label = { Text(text = label) },
        value = state,
        onValueChange = onStateChange,
        placeholder = { Text(placeholder ?: "", style = textStyle) },
        modifier = modifier,
        shape = cornerRadius,
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        visualTransformation = visualTransformation,
        keyboardActions = keyboardActions,
        keyboardOptions = keyboardOptions,
        singleLine = singleLine,
        maxLines = maxLines,
    )
}

@Composable
fun Icons(
    modifier: Modifier = Modifier,
    imageBitmap: ImageBitmap? = null,
    painter: Painter? = null,
    imageVector: ImageVector? = null,
    contentDescription: String? = null,
    tint: Color = LocalContentColor.current
) {
    when {
        imageBitmap != null -> {
            Icon(
                bitmap = imageBitmap,
                contentDescription = contentDescription,
                modifier = modifier,
            )
        }

        painter != null -> {
            Icon(
                painter = painter,
                contentDescription = contentDescription,
                modifier = modifier,
            )
        }

        imageVector != null -> {
            Icon(
                imageVector = imageVector,
                contentDescription = contentDescription,
                modifier = modifier,
            )
        }
    }
}
package com.adoyo.dictionaryapp.presentation.uistate.components

import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PartsOfSpeech(
    headerText: String,
    size: Int,
    color: Color
) {
    Button(
        onClick = { /*TODO*/ },
        elevation = ButtonDefaults.elevation(0.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = color),
        modifier = Modifier.semantics { contentDescription = "parts of speech" }
    ) {
        Text(text = buildAnnotatedString {
            withStyle(style = SpanStyle(
                fontSize = 18.sp, color = Color.White
            )) {
                append(headerText)
            }
            withStyle(style = SpanStyle(
                fontSize = 18.sp, color = Color.White
            )) {
                append("$size")
            }

        })

    }
}
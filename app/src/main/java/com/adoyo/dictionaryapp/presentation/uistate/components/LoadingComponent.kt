package com.adoyo.dictionaryapp.presentation.uistate.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.adoyo.dictionaryapp.R
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieConstants

@Composable
fun LoadingComponent(isLoading: Boolean) {
    if (isLoading) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                LoadingAnimation()
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Please wait while we get the definition...",
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center
                )
            }
        }
    }

}

@Composable
fun LoadingAnimation() {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.not_found))

    LottieAnimation(
        composition = composition,
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)
            .semantics { contentDescription = "Loading Animation" },
        iterations = LottieConstants.IterateForever
    )
}
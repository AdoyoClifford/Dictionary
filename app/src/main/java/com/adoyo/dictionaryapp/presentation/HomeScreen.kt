package com.adoyo.dictionaryapp.presentation

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.adoyo.common.UiEvents
import com.adoyo.dictionaryapp.ui.theme.DictionaryAppTheme
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest

@Composable
fun HomeScreen(
    definitionViewModel: DefinitionViewModel = hiltViewModel()
) {
    val scaffoldState = rememberScaffoldState()

    LaunchedEffect(key1 = true) {
        definitionViewModel.eventFlow.collectLatest { event ->
            when (event) {
                is UiEvents.SnackBarEvent -> {
                    scaffoldState.snackbarHostState.showSnackbar(event.message)
                }
            }
        }

    }
    val definitionUiState = definitionViewModel.definitionUiState.collectAsState().value
    val typedWord = definitionViewModel.typedWord.value

    val definitions =
        if (definitionUiState.definition?.isNotEmpty() == true) definitionUiState.definition[0].meanings
            ?: emptyList()
        else emptyList()

    DictionaryAppTheme {
        Scaffold(
            scaffoldState = scaffoldState,
            topBar = {
                TopAppBar(title = {
                    Text(text = buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                fontSize = 16.sp, color = Color.White
                            )
                        ) {
                            append("Your\n")
                        }
                        withStyle(
                            style = SpanStyle(
                                fontSize = 14.sp,
                                color = Color.White
                            )
                        ) {
                            append("Dictionary")
                        }
                    })
                },
                    backgroundColor = Color(0xFF4C7AF2)
                )
            }
        ) {paddingValues ->

        }
    }
}

@Composable
fun HomeContent(

) {

}
package com.adoyo.dictionaryapp.presentation

import android.widget.Space
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.adoyo.common.UiEvents
import com.adoyo.dictionaryapp.data.remote.Meaning
import com.adoyo.dictionaryapp.presentation.uistate.DefinitionUiState
import com.adoyo.dictionaryapp.presentation.uistate.components.*
import com.adoyo.dictionaryapp.ui.theme.DictionaryAppTheme
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
                TopAppBar(
                    title = {
                        Text(
                            text = "Dictionary App",
                            fontSize = 20.sp,
                            color = Color.White
                        )
                    },
                )
            }
        ) { paddingValues ->
            HomeContent(
                definitionUiState = definitionUiState,
                typedWord = typedWord,
                setWordToBeSearched = { word ->
                    definitionViewModel.setTypedWord(word)
                },
                searchWord = { definitionViewModel.getDefinition() },
                meanings = definitions,
                paddingValues = paddingValues
            )

        }
    }
}

@Composable
fun HomeContent(
    definitionUiState: DefinitionUiState,
    typedWord: String,
    setWordToBeSearched: (String) -> Unit,
    searchWord: () -> Unit,
    meanings: List<Meaning>,
    paddingValues: PaddingValues = PaddingValues(
        0.dp
    )
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
    ) {
        LazyColumn(contentPadding = PaddingValues(14.dp)) {
            item {
                SearchField(
                    setWordToBeSearched = setWordToBeSearched,
                    searchWord = searchWord,
                    typedWord = typedWord
                )
            }

            if (definitionUiState.isLoading || definitionUiState.definition?.isEmpty() == true) {
                item {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        LoadingComponent(isLoading = definitionUiState.isLoading)

                        EmptyComponent(
                            isLoading = definitionUiState.isLoading,
                            definition = definitionUiState.definition
                        )
                    }
                }

            }

            if (!definitionUiState.isLoading && !definitionUiState.definition.isNullOrEmpty()) {
                item {
                    Spacer(
                        modifier = Modifier.height(15.dp)
                    )
                    PronunciationComponent(
                        word = definitionUiState.definition[0].word ?: "",
                        phonetic = definitionUiState.definition[0].phonetic ?: "---"
                    )
                }
            }
            items(meanings) { meaning ->
                Spacer(modifier = Modifier.height(10.dp))

                PartsDefinition(
                    partsOfSpeech = meaning.partOfSpeech ?: "",
                    definition = meaning.definitions ?: emptyList()
                )
            }
        }
    }
}
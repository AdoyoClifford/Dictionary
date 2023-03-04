package com.adoyo.dictionaryapp.presentation.uistate.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.input.ImeAction
import com.adoyo.dictionaryapp.ui.theme.Shapes

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchField(
    setWordToBeSearched: (String) -> Unit,
    searchWord: () -> Unit,
    typedWord: String
) {
    val keyBoardController = LocalSoftwareKeyboardController.current
    val focusManger = LocalFocusManager.current

    OutlinedTextField(
        value = typedWord,
        onValueChange = { word ->
            setWordToBeSearched(word)
        },
        modifier = Modifier
            .fillMaxWidth()
            .semantics { contentDescription = "SearchTextField" },
        placeholder = { Text(text = "Search here") },
        leadingIcon = { Icon(Icons.Outlined.Search, contentDescription = "Search") },
        trailingIcon = {
            if (typedWord.isNotEmpty()) {
                Icon(Icons.Outlined.Search, contentDescription = "Search")
            }
        }, shape = Shapes.small,
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color(0xFFEBE7E7),

            unfocusedIndicatorColor = Color(0xFFEBE7E7),

            focusedIndicatorColor = Color(0xFF4C7AF2)
        ),
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
        keyboardActions = KeyboardActions(
            onSearch = {
                searchWord()
                keyBoardController?.hide()
                focusManger.clearFocus()
            }
        )
    )
}
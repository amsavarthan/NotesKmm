package com.amsavarthan.note.android.screens.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.amsavarthan.note.android.R


@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    value: String,
    onChange: (String) -> Unit,
    onClear: () -> Unit
) {
    val isKeyboardVisible = WindowInsets.ime.getBottom(LocalDensity.current) > 0
    val focusManager = LocalFocusManager.current

    if (!isKeyboardVisible) {
        focusManager.clearFocus()
    }

    BasicTextField(value,
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .padding(bottom = 16.dp)
            .background(
                MaterialTheme.colorScheme.primary.copy(alpha = 0.1f),
                CircleShape
            ),
        textStyle = TextStyle.Default.copy(
            fontSize = 16.sp, color = MaterialTheme.colorScheme.onSurface
        ),
        onValueChange = onChange,
        cursorBrush = SolidColor(MaterialTheme.colorScheme.primary),
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Search,
            capitalization = KeyboardCapitalization.Sentences,
        ),
        keyboardActions = KeyboardActions(onSearch = {
            focusManager.clearFocus()
        }),
        decorationBox = { innerTextField ->
            Row(
                modifier = Modifier.padding(vertical = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = stringResource(id = R.string.search),
                    modifier = Modifier.padding(start = 16.dp, end = 8.dp),
                    tint = MaterialTheme.colorScheme.outline
                )
                Box(modifier = Modifier.weight(1f)) {
                    if (value.isBlank()) {
                        Text(
                            text = stringResource(id = R.string.search_notes),
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                        )
                    }
                    innerTextField()
                }
                if (value.isNotBlank()) {
                    Icon(
                        modifier = Modifier
                            .padding(end = 16.dp, start = 8.dp)
                            .clip(CircleShape)
                            .clickable(onClick = onClear),
                        imageVector = Icons.Default.Clear,
                        contentDescription = stringResource(id = R.string.clear),
                        tint = MaterialTheme.colorScheme.outline
                    )
                }
            }
        }
    )
}
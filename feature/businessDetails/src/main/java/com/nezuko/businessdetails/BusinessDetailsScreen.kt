package com.nezuko.businessdetails

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.outlined.Create
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nezuko.businessdetails.utils.parseLocalDateTimeToDate
import com.nezuko.businessdetails.utils.parseLocalDateTimeToTime
import com.nezuko.ui.theme.GrayText
import com.nezuko.ui.theme.LightBlue
import com.nezuko.ui.theme.Spacing
import java.time.LocalDateTime

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BusinessDetailsScreen(
    name: String = "",
    description: String = "",
    color: Int = LightBlue.toArgb(),
    timeStart: LocalDateTime = LocalDateTime.now(),
    timeEnd: LocalDateTime = LocalDateTime.now().plusHours(1),
    onCloseClick: () -> Unit,
    onDoneClick: () -> Unit,
    onTimeStartClick: () -> Unit,
    onDateStartClick: () -> Unit,
    onTimeEndClick: () -> Unit,
    onDateEndClick: () -> Unit,
    onColorPick: () -> Unit,
    onNameChanged: (String) -> Unit,
    onDescriptionChanged: (String) -> Unit,
) {
    val focusRequester = remember { FocusRequester() }
    var isFocused by remember { mutableStateOf(false) }

    val defaultColor = MaterialTheme.colorScheme.primary

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Новая задача"
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onCloseClick) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "назад"
                        )
                    }
                },
                actions = {
                    IconButton(onClick = onDoneClick) {
                        Icon(
                            imageVector = Icons.Default.Done,
                            contentDescription = "сохранить"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(horizontal = Spacing.default.medium)
        ) {
            Spacer(modifier = Modifier.padding(Spacing.default.medium))

            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(Spacing.default.medium),
                value = name,
                onValueChange = onNameChanged,
                placeholder = {
                    Text(text = "Введите название")
                },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    disabledContainerColor = Color.White,
                    errorContainerColor = Color.White
                )
            )

            HorizontalDivider(modifier = Modifier.padding(vertical = Spacing.default.medium))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(Spacing.default.small),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = com.nezuko.ui.R.drawable.shcedule),
                    contentDescription = null,
                    modifier = Modifier
                        .size(30.dp)
                )

                Spacer(modifier = Modifier.padding(Spacing.default.small))

                Text(
                    text = parseLocalDateTimeToDate(timeStart),
                    fontSize = 20.sp,
                    modifier = Modifier
                        .weight(1f)
                        .clickable { onDateStartClick() }
                )

                Text(
                    text = parseLocalDateTimeToTime(timeStart),
                    fontSize = 20.sp,
                    modifier = Modifier.clickable { onTimeStartClick() }
                )
            }

            Spacer(modifier = Modifier.padding(Spacing.default.small))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(Spacing.default.small),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = com.nezuko.ui.R.drawable.shcedule),
                    contentDescription = null,
                    modifier = Modifier
                        .size(30.dp)
                )

                Spacer(modifier = Modifier.padding(Spacing.default.small))

                Text(
                    text = parseLocalDateTimeToDate(timeEnd),
                    fontSize = 20.sp,
                    modifier = Modifier
                        .weight(1f)
                        .clickable { onDateEndClick() }
                )

                Text(
                    text = parseLocalDateTimeToTime(timeEnd),
                    fontSize = 20.sp,
                    modifier = Modifier.clickable { onTimeEndClick() }
                )
            }

            HorizontalDivider(modifier = Modifier.padding(vertical = Spacing.default.medium))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(Spacing.default.small)
                    .clickable { onTimeEndClick() },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Outlined.Create,
                    contentDescription = null,
                    modifier = Modifier
                        .size(30.dp)
                )

                Spacer(modifier = Modifier.padding(Spacing.default.small))

                BasicTextField(
                    value = description,
                    onValueChange = onDescriptionChanged,
                    modifier = Modifier
                        .fillMaxWidth()
                        .focusRequester(focusRequester)
                        .focusable()
                        .onFocusChanged { focusState ->
                            isFocused = focusState.isFocused
                        },
                    textStyle = TextStyle(
                        color = if (isFocused) Color.Black else GrayText,
                        fontSize = 20.sp
                    ),
                    decorationBox = { innerTextField ->
                        if (!isFocused) {
                            if (description.isEmpty()) {
                                Text(
                                    text = "Введите описание",
                                    color = Color.Black.copy(0.6f),
                                    fontSize = 18.sp
                                )

                            } else {
                                Text(text = description, fontSize = 18.sp)
                            }
                        } else {
                            innerTextField()
                        }
                    }
                )
            }

            HorizontalDivider(modifier = Modifier.padding(vertical = Spacing.default.medium))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(Spacing.default.small)
                    .clickable { onColorPick() },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(30.dp)
                        .background(
                            color = Color(color),
                            shape = CircleShape
                        )
                )

                Spacer(modifier = Modifier.padding(Spacing.default.small))

                Text(
                    text = "Выберите цвет", fontSize = 18.sp
                )
            }

            Spacer(modifier = Modifier.padding(Spacing.default.small))

            HorizontalDivider()
        }

    }
}

@Preview
@Composable
private fun BusinessDetailsPreview() {
    BusinessDetailsScreen(
        onCloseClick = { /*TODO*/ },
        onDescriptionChanged = {},
        onDoneClick = {},
        onNameChanged = {},
        onColorPick = {},
        onTimeStartClick = {},
        onTimeEndClick = {},
        onDateStartClick = {},
        onDateEndClick = {}
    )
}
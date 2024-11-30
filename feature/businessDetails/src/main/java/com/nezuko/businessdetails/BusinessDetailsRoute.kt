package com.nezuko.businessdetails

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.hilt.navigation.compose.hiltViewModel
import com.nezuko.businessdetails.components.DatePickerModal
import com.nezuko.businessdetails.utils.convertMillisToDate
import com.nezuko.domain.model.Business
import java.time.LocalDateTime

@Composable
fun BusinessDetailsRoute(
    id: Int,
    onNavigateBack: () -> Unit,
    vm: BusinessDetailsViewModel = hiltViewModel(),
) {
    val business by vm.getBusinessById(id).collectAsState(null)
    var isLoaded by remember {
        mutableStateOf(false)
    }

    /*
    0 - не показывать
    1 - показывать для старта
    2 - показывать для конца
     */
    var showDatePicker by remember { mutableStateOf(0) }

    /*
    0 - не показывать
    1 - показывать для старта
    2 - показывать для конца
     */
    var showTimePicker by remember { mutableStateOf(0) }

    BackHandler {
        isLoaded = false
    }

    LaunchedEffect(business) {
        if (id == -1) isLoaded = true
        else if (business != null) isLoaded = true
    }

    var name by remember {
        mutableStateOf(business?.name ?: "")
    }
    var description by remember {
        mutableStateOf(business?.description ?: "")
    }
    var timeStart by remember {
        mutableStateOf(
            business?.timeStart ?: LocalDateTime.now()
        )
    }
    var timeEnd by remember {
        mutableStateOf(
            business?.timeStart ?: LocalDateTime.now().plusHours(1)
        )
    }
    val defaultColor = MaterialTheme.colorScheme.primary
    var color by remember {
        mutableStateOf(
            business?.color ?: defaultColor.toArgb()
        )
    }

    if (showDatePicker != 0) {
        DatePickerModal(
            onDateSelected = when (showDatePicker) {
                1 -> { dateLong ->
                    if (dateLong != null) {
                        val date = convertMillisToDate(dateLong)

                    }
                }
            },
            onDismiss = { showDatePicker = 0 }
        )
    }

    if (isLoaded) {
        BusinessDetailsScreen(
            name = name,
            description = description,
            timeEnd = timeEnd,
            timeStart = timeStart,
            color = color,
            onCloseClick = onNavigateBack,
            onDoneClick = {
                vm.saveBusiness(
                    Business(
                        id = id,
                        timeStart = timeStart,
                        timeEnd = timeEnd,
                        name = name,
                        description = description,
                        color = color
                    )
                )
            },
            onTimeStartClick = { },
            onDateStartClick = {

            },
            onTimeEndClick = { /*TODO*/ },
            onDateEndClick = { /*TODO*/ },
            onColorPick = { /*TODO*/ },
            onNameChanged = { name = it },
            onDescriptionChanged = { description = it }
        )
    } else {
        Box(modifier = Modifier.fillMaxSize()) {
            Text(text = "загрузка", modifier = Modifier.align(Alignment.Center))
        }
    }
}
package com.nezuko.businessdetails.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TimePickerDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog

@Composable
fun TimePicker(modifier: Modifier = Modifier) {

}

@Composable
fun TimePickerDialog(
    onCancel: () -> Unit,
    onConfirm: () -> Unit,
    content: @Composable () -> Unit
) {
    Dialog(onDismissRequest = onCancel) {
        Box(modifier = Modifier.padding(16.dp)) {
            Column {
                content() // Вставьте TimePicker или TimeInput
                Row(horizontalArrangement = Arrangement.End) {
                    Button(onClick = onCancel) { Text("Cancel") }
                    Button(onClick = onConfirm) { Text("OK") }
                }
            }
        }
    }
}
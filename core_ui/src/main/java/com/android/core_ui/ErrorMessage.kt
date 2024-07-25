package com.android.core_ui

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color


/**
 * Displays an error message dialog.
 *
 * This composable function creates a dialog window that displays an error message to the user. It's designed to inform the user of an unexpected error and provide an option to dismiss the dialog.
 *
 * @param message The error message to be displayed. It can be null, in which case no message will be shown in the dialog.
 * @param onDismiss A lambda function that is called when the dialog is dismissed by the user. This allows for cleanup actions or state updates that should occur after the dialog is closed.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ErrorMessage(message: String?, onDismiss: () -> Unit) {
    val openDialog = remember { mutableStateOf(true) }
    if (openDialog.value) {
        AlertDialog(onDismissRequest = {
            openDialog.value = false
            onDismiss()
        }, title = {
            Text(
                text = "An unexpected error occurred", color = Color.Black
            )
        }, text = {
            message?.let { Text(it, color = Color.Black) }
        }, confirmButton = {
            Button(onClick = {
                openDialog.value = false
                onDismiss()
            }) {
                Text("Ok", color = Color.Black)
            }
        })

    }
}
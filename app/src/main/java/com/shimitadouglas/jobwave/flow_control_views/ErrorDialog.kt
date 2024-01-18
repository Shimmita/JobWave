package com.shimitadouglas.jobwave.flow_control_views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.shimitadouglas.jobwave.constant_error_msg.ErrorMessages


@Composable
fun ErrorDialogFailure(errorMessage: String) {


    Dialog(
        onDismissRequest = { return@Dialog},
        properties = DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = false),
    ) {
        OutlinedCard() {
            Column(modifier = Modifier.padding(10.dp)) {
                //error message body
                Text(
                    text = errorMessage,
                    textAlign = TextAlign.Center,
                    fontSize = MaterialTheme.typography.bodyMedium.fontSize,

                    )

                //row buttons home and retry
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    OutlinedButton(onClick = { /*TODO*/ }) {
                        Text(text = "retry")
                    }

                    OutlinedButton(onClick = { /*TODO*/ }) {
                        Text(text = "back")
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun PreviewDialog() {
    ErrorDialogFailure(errorMessage = "${ErrorMessages.GENERAL_ERROR_MESSAGE}")
}
package com.shimitadouglas.jobwave.contact_dev

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.shimitadouglas.jobwave.R
import com.shimitadouglas.jobwave.constant_objects.ObjectConstants


@Composable
fun ContactDev() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        //get the Context
        val context: Context = LocalContext.current

        OutlinedButton(onClick = { funSendEmail(context) }, modifier = Modifier.fillMaxWidth()) {
            Image(
                painter = painterResource(id = R.drawable.gmail),
                contentDescription = "gmail icon",
                modifier = Modifier
                    .size(25.dp)
                    .padding(horizontal = 4.dp)

            )
            Text(text = "Send Email to the Developer ")
        }
        Spacer(modifier = Modifier.height(10.dp))


        OutlinedButton(onClick = { funSendSMS(context) }, modifier = Modifier.fillMaxWidth()) {
            Icon(
                imageVector = Icons.Filled.Email,
                contentDescription = "sms icon",
                modifier = Modifier
                    .size(25.dp)
                    .padding(horizontal = 4.dp)

            )
            Text(text = "Send SMS to the Developer ")

        }

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedButton(onClick = { funSendWhatsapp(context) }, modifier = Modifier.fillMaxWidth()) {

            Image(
                painter = painterResource(id = R.drawable.whatsapp),
                contentDescription = "gmail icon",
                modifier = Modifier
                    .size(30.dp)
                    .padding(horizontal = 4.dp)

            )

            Text(text = "Send Whatsapp Message")

        }

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedButton(
            onClick = { funCall(context) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 20.dp)
        ) {
            Icon(
                imageVector = Icons.Filled.Call,
                contentDescription = "phone call icon",
                modifier = Modifier
                    .size(
                        25.dp
                    )
                    .padding(horizontal = 4.dp)

            )
            Text(text = "Phone Call the Developer")

        }


    }
}

@SuppressLint("QueryPermissionsNeeded")

fun funSendEmail(context: Context) {
    val intent = Intent(Intent.ACTION_SENDTO).apply {
        data = Uri.parse("mailto:")
        putExtra(
            Intent.EXTRA_EMAIL,
            arrayOf("${ObjectConstants.DEV_EMAIL_1}, ${ObjectConstants.DEV_EMAIL_2}")
        )
        putExtra(Intent.EXTRA_SUBJECT, "Subject")
        putExtra(Intent.EXTRA_TEXT, "write your message here")
    }

    if (intent.resolveActivity(context.packageManager) != null) {
        context.startActivity(intent)
    } else {
        Toast.makeText(context, "failed to send open mail app", Toast.LENGTH_LONG).show()

    }
}

@SuppressLint("QueryPermissionsNeeded")

fun funSendSMS(context: Context) {


    context.startActivity(
        Intent(
            Intent.ACTION_VIEW,
            Uri.fromParts("sms", ObjectConstants.DEV_PHONE, null)
        )
    )


}

@SuppressLint("QueryPermissionsNeeded")
fun funSendWhatsapp(context: Context) {
    val intent = Intent(Intent.ACTION_VIEW).apply {
        data =
            Uri.parse("https://wa.me/${ObjectConstants.DEV_PHONE}/?text= write your message .....") // Replace with the recipient's phone number
    }

    if (intent.resolveActivity(context.packageManager) != null) {
        context.startActivity(intent)
    } else {
        Toast.makeText(context, "failed to open whatsapp app", Toast.LENGTH_LONG).show()
    }
}

@SuppressLint("QueryPermissionsNeeded")
fun funCall(context: Context) {

    val phoneNumber = "tel:${ObjectConstants.DEV_PHONE}" //

    val intent = Intent(Intent.ACTION_DIAL).apply {
        data = Uri.parse(phoneNumber)
    }

    if (intent.resolveActivity(context.packageManager) != null) {
        context.startActivity(intent)
    } else {
        Toast.makeText(context, "failed to open phone call app", Toast.LENGTH_LONG).show()
    }


}


@Preview(showBackground = true)
@Composable
private fun PreviewContactScreen() {
    ContactDev()
}
package com.shimitadouglas.jobwave.screens

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.shimitadouglas.jobwave.R
import com.shimitadouglas.jobwave.routes.Routes
import java.util.Locale

@Composable
fun LoginScreen(navController: NavHostController) {
    var isAnimate by rememberSaveable {
        mutableStateOf(false)
    }

    val animateFloatValue by animateFloatAsState(
        targetValue = if (isAnimate) 1.0f else 0.0f,
        animationSpec = tween(durationMillis = 2000, easing = LinearEasing),
        label = "animation login"
    )

    LaunchedEffect(key1 = true) {
        isAnimate = true
    }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .alpha(animateFloatValue)

    ) {

        var isAll by rememberSaveable {
            mutableStateOf(false)
        }
        var isShowPassword by rememberSaveable {

            mutableStateOf(false)
        }

        var email by rememberSaveable {
            mutableStateOf("")
        }

        var password by rememberSaveable {
            mutableStateOf("")
        }


        if (email.trim().isNotEmpty() && password.trim().isNotEmpty()) {
            isAll = true
        }

        Text(
            text = "Login".uppercase(Locale.ROOT),
            fontStyle = MaterialTheme.typography.headlineMedium.fontStyle,
            fontSize = 18.sp
        )

        OutlinedTextField(
            shape = RoundedCornerShape(10.dp),
            label = { Text(text = "Email") },
            value = email,
            modifier = Modifier.fillMaxWidth(.8f),

            leadingIcon = {
                Icon(imageVector = Icons.Filled.Email, contentDescription = "Leading Icon Email")
            },
            onValueChange = {
                email = it
            })

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            shape = RoundedCornerShape(10.dp),
            label = { Text(text = "Password") },
            value = password,
            modifier = Modifier.fillMaxWidth(.8f),

            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            trailingIcon = {
                IconButton(onClick = {

                    //alter password showing
                    isShowPassword = !isShowPassword
                }) {
                    Icon(
                        painterResource(id = R.drawable.baseline_eye),
                        contentDescription = "password Icon"
                    )

                }
            },
            visualTransformation = if (isShowPassword) {
                PasswordVisualTransformation()
            } else VisualTransformation.None,
            leadingIcon = {
                Icon(imageVector = Icons.Filled.Lock, contentDescription = "Leading Icon Password")
            },
            onValueChange = {
                password = it
            })
        Spacer(modifier = Modifier.height(10.dp))

        Button(
            onClick = { funLogin() },
            shape = RoundedCornerShape(10.dp),
            enabled = isAll,
            modifier = Modifier.fillMaxWidth(.8f),
        ) {
            Text(text = "Login")
        }

        Spacer(modifier = Modifier.height(10.dp))
        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "not having account yet?",
                fontStyle = FontStyle.Italic

            )

            TextButton(onClick = {
                //navigate to the registration screen
                navController.navigate(route = Routes.SCREEN_REGISTER)
            }) {
                Text(text = "register", fontStyle = MaterialTheme.typography.bodyMedium.fontStyle)

            }
        }

    }
}


//implement Login
fun funLogin() {
    TODO("Not yet implemented")
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    val navController = rememberNavController()
    LoginScreen(navController)
}




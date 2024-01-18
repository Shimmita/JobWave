package com.shimitadouglas.jobwave.screens

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.shimitadouglas.jobwave.R
import java.util.Locale

@Composable
fun RegisterScreen(navController: NavHostController) {

    var isAnimate by rememberSaveable {
        mutableStateOf(false)
    }

    val animateFloatValue by animateFloatAsState(
        targetValue = if (isAnimate) 1.0f else 0.0f,
        animationSpec = tween(durationMillis = 2000, easing = LinearEasing),
        label = "animation register"
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

        //holds boolean true if all text-fields are filled
        var isAll by rememberSaveable {
            mutableStateOf(false)
        }

        var isShowPassword by rememberSaveable {

            mutableStateOf(false)
        }

        var github by rememberSaveable {
            mutableStateOf("")
        }

        var linkedin by rememberSaveable {
            mutableStateOf("")
        }

        var email by rememberSaveable {
            mutableStateOf("")
        }

        var password by rememberSaveable {
            mutableStateOf("")
        }

        var name by rememberSaveable {
            mutableStateOf("")
        }

        var phone by rememberSaveable {
            mutableStateOf("")
        }

        if (email.isNotEmpty() && password.isNotEmpty() && name.isNotEmpty() && phone.isNotEmpty() && github.trim()
                .isNotEmpty() && linkedin.trim().isNotEmpty()
        ) {
            isAll = true
        }

        Image(
            painterResource(id = R.drawable.lancher),
            contentDescription = "job_wave_icon",
            Modifier
                .size(100.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop

        )

        Text(
            text = "Registration".uppercase(Locale.ROOT),
            fontStyle = MaterialTheme.typography.headlineMedium.fontStyle,
            fontSize = 18.sp
        )

        OutlinedTextField(
            shape = RoundedCornerShape(10.dp),
            label = { Text(text = "full name") },
            value = name,
            leadingIcon = {
                Icon(
                    imageVector = Icons.Filled.Person,
                    contentDescription = "Leading Icon Email"
                )
            },
            onValueChange = {
                name = it
            })

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            shape = RoundedCornerShape(10.dp),
            label = { Text(text = "phone") },
            value = phone,
            leadingIcon = {
                Icon(imageVector = Icons.Filled.Call, contentDescription = "Leading Icon Email")
            },
            onValueChange = {
                phone = it
            })

        Spacer(modifier = Modifier.height(20.dp))


        OutlinedTextField(
            shape = RoundedCornerShape(10.dp),
            label = { Text(text = "github profile link") },
            value = github,
            leadingIcon = {
                Icon(imageVector = Icons.Filled.Face, contentDescription = "Leading Icon Email")
            },
            onValueChange = {
                github = it
            })

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            shape = RoundedCornerShape(10.dp),
            label = { Text(text = "linkedin profile link") },
            value = linkedin,
            leadingIcon = {
                Icon(imageVector = Icons.Filled.Face, contentDescription = "Leading Icon Email")
            },
            onValueChange = {
                linkedin = it
            })

        Spacer(modifier = Modifier.height(20.dp))


        OutlinedTextField(
            shape = RoundedCornerShape(10.dp),
            label = { Text(text = "Email") },
            value = email,
            leadingIcon = {
                Icon(
                    imageVector = Icons.Filled.Email,
                    contentDescription = "Leading Icon Email"
                )
            },
            onValueChange = {
                email = it
            })

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            shape = RoundedCornerShape(10.dp),
            label = { Text(text = "Password") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            visualTransformation = if (isShowPassword) {
                PasswordVisualTransformation()
            } else VisualTransformation.None,
            value = password,
            trailingIcon = {
                IconButton(onClick = {
                    //alter showing of the password
                    isShowPassword = !isShowPassword

                }) {
                    Icon(
                        painterResource(id = R.drawable.baseline_eye),
                        contentDescription = "password icon"
                    )
                }
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Filled.Lock,
                    contentDescription = "Leading Icon Password"
                )
            },
            onValueChange = {
                password = it
            })
        Spacer(modifier = Modifier.height(10.dp))

        Button(onClick = { funRegister() }, shape = RoundedCornerShape(10.dp), enabled = isAll) {
            Text(text = "register")
        }
    }


}

//register handler
fun funRegister() {

}


@Preview(showBackground = true)
@Composable
private fun Preview() {
    val navController = rememberNavController();
    RegisterScreen(navController)
}




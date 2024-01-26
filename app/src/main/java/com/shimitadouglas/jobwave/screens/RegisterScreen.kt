package com.shimitadouglas.jobwave.screens

import android.util.Log
import android.widget.Toast
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.google.gson.Gson
import com.shimitadouglas.jobwave.R
import com.shimitadouglas.jobwave.client.RetrofitClient
import com.shimitadouglas.jobwave.data_class.DataUserCreate
import com.shimitadouglas.jobwave.data_class.ErrorResponse
import com.shimitadouglas.jobwave.data_class.UserCreateResponse
import com.shimitadouglas.jobwave.interface_service.CreateUSerInterface
import com.shimitadouglas.jobwave.routes.Routes
import com.shimitadouglas.jobwave.utilities.Validations
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.Locale


@Composable
fun RegisterScreen(navController: NavHostController) {
    //UI determinants
    var isErrorOccurred by rememberSaveable { mutableStateOf(false) }
    var isShowLoadingDialog by rememberSaveable { mutableStateOf(false) }
    var isShowForm by rememberSaveable { mutableStateOf(true) }
    var isShowAlertDialog by rememberSaveable { mutableStateOf(false) }

    var successServerRegMessage by rememberSaveable {
        mutableStateOf("")
    }

    var errorServerRegMessage by rememberSaveable { mutableStateOf("") }

    //form variables
    val context = LocalContext.current
    //scope launcher for coroutine
    val scope = rememberCoroutineScope()

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

    var firstname by rememberSaveable {
        mutableStateOf("")
    }

    var lastname by rememberSaveable {
        mutableStateOf("")
    }

    var phone by rememberSaveable {
        mutableStateOf("")
    }

    if (email.isNotEmpty() && password.isNotEmpty() && firstname.isNotEmpty() && lastname.isNotEmpty() && phone.isNotEmpty() && github.trim()
            .isNotEmpty() && linkedin.trim().isNotEmpty()
    ) {
        isAll = true
    }

    //animation trigger
    var isAnimate by rememberSaveable {
        mutableStateOf(true)
    }


    val animateFloatValue by animateFloatAsState(
        targetValue = if (isAnimate) 1.0f else 0.0f,
        animationSpec = tween(durationMillis = 2000, easing = LinearEasing),
        label = "animation register"
    )

    LaunchedEffect(key1 = true) {
        isAnimate = true
    }

    if (isShowAlertDialog) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
        ) {
            AlertDialog(
                onDismissRequest = { isShowAlertDialog = !isShowAlertDialog },
                properties = DialogProperties(
                    dismissOnBackPress = false,
                    dismissOnClickOutside = false
                ),
                dismissButton = {
                    //dismiss alert and show the form screen
                    TextButton(onClick = {
                        isShowAlertDialog = !isShowAlertDialog
                        isShowForm = true
                        //navigate to home
                        navController.popBackStack()
                        navController.navigate(route = Routes.SCREEN_HOME)
                    }) {
                        Text(text = "Home")
                    }
                },
                icon = {
                    if (isErrorOccurred) Icon(
                        imageVector = Icons.Filled.Clear,
                        tint = Color.Red,
                        contentDescription = "failed icon"
                    )
                    else Icon(
                        imageVector = Icons.Filled.Done,
                        tint = Color.Green,
                        contentDescription = "done icon"
                    )

                },
                title = {
                    if (isErrorOccurred)
                        Text(text = "failed")
                    else
                        Text(text = "successful")
                },
                text = {
                    if (isErrorOccurred) {
                        Text(text = errorServerRegMessage)
                    } else {
                        Text(text = successServerRegMessage)

                    }

                },
                confirmButton = {
                    if (isErrorOccurred) {
                        //dismiss the dialog and show the registration form
                        TextButton(onClick = {
                            isShowAlertDialog = !isShowAlertDialog
                            isShowForm = true
                        }) {
                            Text(text = "Retry")
                        }
                    } else {
                        //onclick navigate to the login screen
                        TextButton(onClick = {
                            isShowAlertDialog = !isShowAlertDialog
                            navController.popBackStack()
                            navController.navigate(Routes.SCREEN_LOGIN)
                        }) {
                            Text(text = "Login")
                        }
                    }
                })
        }
    } else if (isShowLoadingDialog) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center, modifier = Modifier
                .fillMaxSize()
        ) {
            CircularProgressIndicator(
                modifier = Modifier.width(80.dp),
                color = MaterialTheme.colorScheme.surfaceVariant,
                trackColor = MaterialTheme.colorScheme.secondary,
            )
            Spacer(modifier = Modifier.height(50.dp))
            Text(
                text = "Registering",
                fontStyle = MaterialTheme.typography.titleSmall.fontStyle
            )
        }
    } else if (isShowForm) {
        //main launch
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .alpha(animateFloatValue)
        ) {


            Text(
                text = "Registration".uppercase(Locale.ROOT),
                fontStyle = MaterialTheme.typography.headlineMedium.fontStyle,
                fontSize = 18.sp
            )

            OutlinedTextField(
                shape = RoundedCornerShape(10.dp),
                label = { Text(text = "first name") },
                value = firstname,
                modifier = Modifier.fillMaxWidth(.8f),
                maxLines = 1,
                placeholder = {
                    Text(text = "Michael")
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Filled.Person,
                        contentDescription = "Leading Icon Email"
                    )
                },
                onValueChange = {
                    firstname = it
                })

            Spacer(modifier = Modifier.height(20.dp))


            OutlinedTextField(
                shape = RoundedCornerShape(10.dp),
                label = { Text(text = "last name") },
                value = lastname,
                modifier = Modifier.fillMaxWidth(.8f),
                maxLines = 1,
                placeholder = {
                    Text(text = "Smite")
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Filled.Person,
                        contentDescription = "Leading Icon Email"
                    )
                },
                onValueChange = {
                    lastname = it
                })

            Spacer(modifier = Modifier.height(20.dp))

            OutlinedTextField(
                shape = RoundedCornerShape(10.dp),
                label = { Text(text = "phone") },
                value = phone,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),

                modifier = Modifier.fillMaxWidth(.8f),
                placeholder = {
                    Text(text = "+254xyz")
                },
                leadingIcon = {
                    Icon(imageVector = Icons.Filled.Call, contentDescription = "Leading Icon Email")
                },
                onValueChange = {
                    phone = it
                })

            Spacer(modifier = Modifier.height(20.dp))

            OutlinedTextField(
                shape = RoundedCornerShape(10.dp),
                label = { Text(text = "github profile") },
                value = github,
                maxLines = 1,
                modifier = Modifier.fillMaxWidth(.8f),
                placeholder = {
                    Text(text = "https://github.com/username")
                },
                leadingIcon = {
                    Image(
                        painterResource(id = R.drawable.github),
                        modifier = Modifier
                            .size(25.dp)
                            .clip(CircleShape),
                        contentScale = ContentScale.Crop,
                        contentDescription = "linkedin image"
                    )
                },
                onValueChange = {
                    github = it
                })

            Spacer(modifier = Modifier.height(20.dp))

            OutlinedTextField(
                shape = RoundedCornerShape(10.dp),
                label = { Text(text = "linkedin profile") },
                value = linkedin,
                modifier = Modifier.fillMaxWidth(.8f),
                placeholder = {
                    Text(text = "https://www.linkedin.com/xyz")
                },
                leadingIcon = {
                    Image(
                        painterResource(id = R.drawable.linkedin_b),
                        modifier = Modifier
                            .size(25.dp)
                            .clip(CircleShape),
                        contentScale = ContentScale.Crop,
                        contentDescription = "linkedin image"
                    )
                },
                singleLine = true,
                onValueChange = {
                    linkedin = it
                })

            Spacer(modifier = Modifier.height(20.dp))


            OutlinedTextField(
                shape = RoundedCornerShape(10.dp),
                label = { Text(text = "Email") },
                value = email,
                singleLine = true,
                maxLines = 1,
                modifier = Modifier.fillMaxWidth(.8f),
                placeholder = {
                    Text(text = "michael@gmail.com")
                },
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
                maxLines = 1,
                modifier = Modifier.fillMaxWidth(.8f),

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

            Button(
                onClick = {
                    //trim the user input
                    firstname.trim()
                    phone.trim()
                    github.trim()
                    linkedin.trim()
                    email.trim()
                    password.trim()
                    lastname.trim()

                    if (firstname.isEmpty() || lastname.isEmpty() || phone.isEmpty() || github.isEmpty() || linkedin.isEmpty() || email.isEmpty() || password.isEmpty()
                    ) {
                        Toast.makeText(context, "fill all fields", Toast.LENGTH_SHORT)
                            .show()

                    } else if (!Validations.isValidEmail(email)) {
                        Toast.makeText(context, "email is invalid!", Toast.LENGTH_SHORT)
                            .show()
                    } else if (!Validations.isValidUrl(github)) {
                        Toast.makeText(context, "github url is invalid!", Toast.LENGTH_SHORT).show()
                    } else if (!Validations.isValidUrl(linkedin)) {
                        Toast.makeText(context, "linkedin url is invalid!", Toast.LENGTH_SHORT)
                            .show()

                    } else if (!Validations.isValidPhone(phone)) {
                        Toast.makeText(context, "phone number is invalid!", Toast.LENGTH_SHORT)
                            .show()

                    } else if (password.length < 6) {
                        Toast.makeText(
                            context,
                            "password should be at least 6 characters long!",
                            Toast.LENGTH_SHORT
                        )
                            .show()
                    } else {
                        //initiate registration no detail is missing begin user registration
                        isShowLoadingDialog = true //loading dialog appearance


                        val user = DataUserCreate(
                            name = "$firstname $lastname",
                            phone = phone,
                            github = github,
                            linkedin = linkedin,
                            email = email,
                            password = password
                        )

                        //creating the new user
                        val result = RetrofitClient.getRetrofitClient()
                            ?.create(CreateUSerInterface::class.java)
                            ?.createUser(user)

                        //using scope to launch the enqueue method
                        scope.launch {
                            result?.enqueue(object : Callback<UserCreateResponse?> {
                                override fun onResponse(
                                    call: Call<UserCreateResponse?>,
                                    response: Response<UserCreateResponse?>
                                ) {
                                    //checking if the response is successful
                                    if (response.isSuccessful) {
                                        //user registered successfully
                                        val message = response.body()?.message
                                        val savedUser = response.body()?.data

                                        //update the success message status
                                        if (message != null) {
                                            successServerRegMessage = message
                                        }

                                        //error false, loading false,  form false alertD true
                                        isShowLoadingDialog = false
                                        isErrorOccurred = false
                                        isShowForm = false


                                        //showing the alertDialog. in the prompt navigate to login screen
                                        isShowAlertDialog = true


                                        //logging the info for debugging purposes
                                        Log.d(
                                            "CreateUser",
                                            "funOnResponse:  created successfully:code ${response.code()}\n\n message: $message\n\n user:$savedUser"
                                        )

                                    } else {
                                        //alert true, isError true, loading false
                                        isShowLoadingDialog = false
                                        isErrorOccurred = true
                                        isShowAlertDialog = true

                                        val errorResponse = Gson().fromJson(
                                            response.errorBody()!!.charStream(),
                                            ErrorResponse::class.java
                                        ).message


                                        //update error message from server, user not registered
                                        errorServerRegMessage = errorResponse

                                        Log.e(
                                            "CreateUser",
                                            "funOnResponse: failed to register user.${errorResponse}"
                                        )
                                    }

                                }

                                override fun onFailure(
                                    call: Call<UserCreateResponse?>,
                                    t: Throwable
                                ) {
                                    //registration failed mostly due to the incidence of network failures
                                    //isError true, loading false, alertTrue
                                    isErrorOccurred = true
                                    isShowLoadingDialog = false
                                    isShowAlertDialog = true

                                    //timeout usually connection issues
                                    errorServerRegMessage =
                                        "${t.message},kindly try again and ensure you have a stable internet connection"

                                    //logging the info to the console
                                    Log.e(
                                        "CreateUser",
                                        "funOnFailure: not successful :${t.message}"
                                    )
                                }
                            })
                        }

                    }
                },
                modifier = Modifier.fillMaxWidth(.8f),

                shape = RoundedCornerShape(10.dp),
                enabled = isAll
            ) {
                Text(text = "register")
            }
        }
    }


}


@Preview(showBackground = true)
@Composable
private fun Preview() {
    val navController = rememberNavController();
    RegisterScreen(navController)
}




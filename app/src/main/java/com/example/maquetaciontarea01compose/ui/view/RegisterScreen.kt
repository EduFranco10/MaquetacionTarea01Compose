@file:Suppress("DEPRECATION")

package com.example.maquetaciontarea01compose.ui.view

import android.annotation.SuppressLint
import android.app.Activity
import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.ViewCompat
import com.example.maquetaciontarea01compose.R

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "UnusedBoxWithConstraintsScope")
@Preview(showBackground = true)
@Composable
fun RegisterScreen() {
    var isDarkTheme by remember { mutableStateOf(false) }
    var name by remember { mutableStateOf("") }
    var isValidName by remember { mutableStateOf(false) }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var passwordMatch by remember { mutableStateOf(false) }
    var isGeolocationEnabled by remember { mutableStateOf(false) }
    var isTermsAccepted by remember { mutableStateOf(false) }

    // Orientation
    val configuration = LocalConfiguration.current
    val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(if (isDarkTheme) Color.Black else Color.White)
            .padding(16.dp)
    ) {
        val view = LocalView.current
        val window = (view.context as Activity).window

        val windowInsetsController = ViewCompat.getWindowInsetsController(window.decorView)
        val statusBarColor = if (isDarkTheme) Color.Black.toArgb() else Color.White.toArgb()

        SideEffect {
            window.statusBarColor = statusBarColor
            windowInsetsController?.isAppearanceLightStatusBars = !isDarkTheme
        }


        Header(isDarkTheme, isLandscape) { isDarkTheme = !isDarkTheme }

        val contentModifier =
            if (isLandscape) Modifier
                .fillMaxWidth()
                .padding(16.dp) else Modifier.fillMaxHeight()
        LazyColumn(
            modifier = contentModifier,
            verticalArrangement = if (isLandscape) Arrangement.Center else Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            item {
                InputFields(
                    name = name,
                    onNameChange = {
                        name = it
                        isValidName = name.length >= 3
                    },
                    password = password,
                    onPasswordChange = { password = it },
                    confirmPassword = confirmPassword,
                    onConfirmPasswordChange = {
                        confirmPassword = it
                        passwordMatch =
                            (password == confirmPassword && confirmPassword != "" && password.length >= 6)
                    },
                    isDarkTheme = isDarkTheme
                )
            }

            item {
                AdditionalSettings(
                    isDarkTheme = isDarkTheme,
                    isGeolocationEnabled = isGeolocationEnabled,
                    onGeolocationChange = { isGeolocationEnabled = it }
                )
            }

            item {
                TermsAndConditions(
                    isTermsAccepted = isTermsAccepted,
                    onTermsChange = { isTermsAccepted = it },
                    isDarkTheme = isDarkTheme
                )
            }

            item {
                ActionButtons(
                    isDarkTheme = isDarkTheme,
                    isEnabled = isTermsAccepted && passwordMatch && isValidName,
                    onRegister = {

                    },
                    onReset = {
                        name = ""
                        password = ""
                        confirmPassword = ""
                        isGeolocationEnabled = false
                        isTermsAccepted = false
                        passwordMatch = false
                    }
                )
            }
        }
    }

}

@Composable
fun Header(isDarkTheme: Boolean, isLandscape: Boolean, onThemeToggle: () -> Unit) {
    val contentModifier = if (isLandscape) Modifier.padding(8.dp) else Modifier.padding(32.dp)
    Spacer(contentModifier)
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(horizontalAlignment = Alignment.Start) {
            Text(
                "MEETDEVS", fontSize = 32.sp,
                fontWeight = FontWeight.Medium,
                color = if (isDarkTheme) Color.White else Color.Black
            )
            Text(
                "Coding with love",
                fontSize = 17.sp,
                fontWeight = FontWeight.Medium,
                color = if (isDarkTheme) Color.White else Color.Black
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        Column(horizontalAlignment = Alignment.End) {
            IconButton(onClick = onThemeToggle) {
                Icon(
                    Icons.Default.DarkMode,
                    "mode",
                    tint = if (isDarkTheme) Color.White else Color.Black
                )
            }
            Text(
                if (isDarkTheme) "Light mode" else "Dark mode",
                fontSize = 17.sp,
                fontWeight = FontWeight.Medium,
                color = if (isDarkTheme) Color.White else Color.Black
            )
        }
    }
}

@Composable
fun InputFields(
    name: String,
    onNameChange: (String) -> Unit,
    password: String,
    onPasswordChange: (String) -> Unit,
    confirmPassword: String,
    onConfirmPasswordChange: (String) -> Unit,
    isDarkTheme: Boolean
) {
    ViewOutlinedTextField(
        value = name,
        onValueChange = onNameChange,
        labelText = "Name",
        isDarkTheme = isDarkTheme
    )
    ViewOutlinedTextField(
        value = password,
        onValueChange = onPasswordChange,
        labelText = "Password",
        isPasswordField = true,
        isDarkTheme = isDarkTheme
    )
    ViewOutlinedTextField(
        value = confirmPassword,
        onValueChange = onConfirmPasswordChange,
        labelText = "Confirm password",
        isPasswordField = true,
        isDarkTheme = isDarkTheme
    )
}

@Composable
fun AdditionalSettings(
    isDarkTheme: Boolean,
    isGeolocationEnabled: Boolean,
    onGeolocationChange: (Boolean) -> Unit
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(48.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                "Upload photo",
                fontSize = 17.sp,
                modifier = Modifier.weight(1f),
                color = if (isDarkTheme) Color.White else Color.Black
            )
            Image(
                painter = painterResource(R.drawable.ic_developer),
                contentDescription = "Upload photo",
                modifier = Modifier
                    .weight(0.5f)
                    .background(if (isDarkTheme) Color.Black else Color.White)
            )
        }
        Spacer(modifier = Modifier.padding(8.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                "Allow geolocation",
                fontSize = 17.sp,
                modifier = Modifier.weight(1f),
                color = if (isDarkTheme) Color.White else Color.Black
            )
            Switch(
                checked = isGeolocationEnabled,
                onCheckedChange = onGeolocationChange
            )
        }
    }
}

@Composable
fun TermsAndConditions(
    isTermsAccepted: Boolean,
    onTermsChange: (Boolean) -> Unit,
    isDarkTheme: Boolean
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Checkbox(
            checked = isTermsAccepted,
            onCheckedChange = onTermsChange,
            colors = CheckboxDefaults.colors(
                checkmarkColor = if (isDarkTheme) Color.White else Color.Black,
                uncheckedColor = if (isDarkTheme) Color.White else Color.Gray,
                checkedColor = if (isDarkTheme) Color.White else MaterialTheme.colorScheme.primary
            )
        )
        Spacer(modifier = Modifier.padding(16.dp))
        Text(
            "I agree with Terms & Conditions",
            fontSize = 17.sp,
            fontWeight = FontWeight.Medium,
            color = if (isDarkTheme) Color.White else Color.Black
        )
    }
}

@Composable
fun ActionButtons(
    isDarkTheme: Boolean,
    isEnabled: Boolean,
    onRegister: () -> Unit,
    onReset: () -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Button(
            onClick = onRegister,
            enabled = isEnabled,
            colors = ButtonDefaults.buttonColors(
                containerColor = if (isEnabled && isDarkTheme) Color(0xFF7D208D) else if (isEnabled) Color(
                    0xFF6200EE
                ) else Color(0xFF9E9E9E),
                disabledContainerColor = if (isDarkTheme) Color.Gray else Color(0xFF9E9E9E),
                contentColor = if (isDarkTheme) Color.White else Color.Black,
                disabledContentColor = Color(0xFF9E9E9E)

            )
        ) {
            Text(
                "Register",
                color = Color.White
            )
        }
        Button(
            onClick = onReset,
            colors = ButtonDefaults.buttonColors(
                containerColor = if (isDarkTheme) Color(0xFF757575) else Color.Gray
            )
        ) {
            Text("Reset")
        }
    }
}

@Composable
fun ViewOutlinedTextField(
    value: String,
    onValueChange: (String) -> Unit,
    labelText: String,
    isPasswordField: Boolean = false,
    isDarkTheme: Boolean
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier.background(if (isDarkTheme) Color.Black else Color.White),
        label = {
            Text(
                text = labelText,
                fontSize = 16.sp,
                color = if (isDarkTheme) Color.White else Color.Black
            )
        },
        trailingIcon = {
            IconButton(onClick = { onValueChange("") }) {
                Icon(
                    Icons.Default.Close,
                    contentDescription = "Clear $labelText",
                    tint = if (isDarkTheme) Color.White else Color.Black
                )
            }
        },
        visualTransformation = if (isPasswordField) PasswordVisualTransformation() else VisualTransformation.None,
        colors = TextFieldDefaults.colors(
            focusedContainerColor = if (isDarkTheme) Color.DarkGray else Color.White,
            unfocusedContainerColor = if (isDarkTheme) Color.DarkGray else Color.White,
            focusedTextColor = if (isDarkTheme) Color.White else Color.Black,
            unfocusedTextColor = if (isDarkTheme) Color.White else Color.Black,
            focusedIndicatorColor = if (isDarkTheme) Color.White else Color.Black,
            unfocusedIndicatorColor = if (isDarkTheme) Color.Gray else Color.LightGray,
            cursorColor = if (isDarkTheme) Color.White else Color.Black,
            errorIndicatorColor = Color.Red
        )
    )
    Spacer(modifier = Modifier.padding(16.dp))
}


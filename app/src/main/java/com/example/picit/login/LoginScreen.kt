package com.example.picit.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.picit.R
import com.example.picit.entities.User
import com.example.picit.ui.theme.PicItTheme
import com.example.picit.utils.DBUtils

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    onClickGoToRegistry: () -> Unit={},
    onClickGoToMainScreen: () -> Unit ={},
    currentUserUpdate: (User) -> Unit={},
    viewModel: LoginViewModel = viewModel(),
    dbutils: DBUtils,
    modifier: Modifier = Modifier
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    var baseContext = LocalContext.current

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
//        Image(painter = painterResource(id = R.drawable.picit_logo), contentDescription = "PicIt logo")

        Spacer(modifier = Modifier.height(60.dp))

        TextField(
            value = email,
            onValueChange = {
                if (it.length <= 40) {
                    email = it
                }
            },
            label = { Text("Email") },
            maxLines = 1
        )

        Spacer(modifier = Modifier.height(20.dp))

        TextField(
            value = password,
            onValueChange = {
                if (it.length <= 20) {
                    password = it
                }
            },
            label = { Text("Password") },
            maxLines = 1,
            visualTransformation = PasswordVisualTransformation()
        )

        Spacer(modifier = Modifier.height(60.dp))

        Box {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                Button(onClick = {
                    viewModel.loginAccount(email, password, baseContext, dbutils, onClickGoToMainScreen, currentUserUpdate)
                }) {
                    Text(text = "Login", fontSize = 22.sp)
                }

                Spacer(modifier = Modifier.height(10.dp))

                Button(onClick = {
                    onClickGoToRegistry()
                }) {
                    Text(text = "Register", fontSize = 22.sp)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    PicItTheme {
        LoginScreen(dbutils = DBUtils())
    }
}
package com.example.picit.picdesccreateroom

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.picit.ui.theme.PicItTheme
import com.example.picit.utils.ScreenHeader

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RoomSettingsScreen(
    onClickBackButton: ()->Unit = {},
    onClickNextButton: (String, String, String, String) -> Unit
) {

    var roomName by remember { mutableStateOf("") }
    var roomCapacity by remember { mutableStateOf("") }
    var maxDailyChallenges by remember { mutableStateOf("") }
    var maxCapacity = 50
    var maxChallenges = 20

    var isPublic by remember { mutableStateOf(true) }
    var isPrivate by remember { mutableStateOf(false) }
    var code by remember { mutableStateOf("") }
    var maxCodeDigits = 4

    var distanceToPrivacyButtons by remember { mutableStateOf(120.dp) }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ScreenHeader(
            true,
            "Create room",
            onClickBackButton = onClickBackButton
        )

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            //Room name field
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                TextField(
                    value = roomName,
                    onValueChange = {
                        if (it.length <= 20) {
                            roomName = it
                        }
                    },
                    label = { Text("Room name") },
                    maxLines = 1
                )
            }

            Spacer(modifier = Modifier.height(30.dp))

            //Room capacity
            TextField(
                value = roomCapacity,
                onValueChange = {
                    if (it == "") {
                        roomCapacity = ""
                    } else {
                        if (it.toInt() <= maxCapacity) {
                            roomCapacity = it
                        } else {
                            roomCapacity =
                                maxCapacity.toString()
                        }
                    }
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                label = { Text("Room capacity") },
                maxLines = 1
            )

            Spacer(modifier = Modifier.height(30.dp))

            // Max Daily Challenges
            TextField(
                value = maxDailyChallenges,
                onValueChange = {
                    if (it == "") {
                        maxDailyChallenges = ""
                    } else {
                        if (it.toInt() <= maxChallenges) {
                            maxDailyChallenges = it
                        } else {
                            maxDailyChallenges =
                                maxChallenges.toString()
                        }
                    }
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                label = { Text("Daily challenges quantity") },
                maxLines = 1
            )

            if (isPrivate) {

                Spacer(modifier = Modifier.height(30.dp))

                // Code for private room
                TextField(
                    value = code,
                    onValueChange = {
                        if (it == "") {
                            code = ""
                        } else {
                            if (it.length <= maxCodeDigits) {
                                code = it
                            }
                        }
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    label = { Text("Code for private room") },
                    maxLines = 1
                )

            }

            Spacer(modifier = Modifier.height(distanceToPrivacyButtons))

            // Public Private buttons
            Text(text = "Select room privacy", fontSize = 20.sp)

            Spacer(modifier = Modifier.height(10.dp))

            var publicColor = Color.Black
            var privateColor = Color.Gray

            if(isPublic) {
                var publicColor = Color.Black
                var privateColor = Color.Gray
            }

            if(isPrivate) {
                privateColor = Color.Black
                publicColor = Color.Gray
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(onClick = {
                    isPublic = true
                    isPrivate = false
                    distanceToPrivacyButtons = 120.dp
                },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = publicColor
                    )
                ) {
                    Text(text = "Public", fontSize = 22.sp)
                }

                Button(onClick = {
                    isPublic = false
                    isPrivate = true
                    distanceToPrivacyButtons = 33.5.dp
                },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = privateColor
                    )
                ) {
                    Text(text = "Private", fontSize = 22.sp)
                }
            }

            Spacer(modifier = Modifier.height(30.dp))

            Button(onClick = { onClickNextButton(roomName, roomCapacity, maxDailyChallenges, isPrivate.toString() + ":" + code)}) {
                Text(text = "Next", fontSize = 22.sp)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RoomSettingsPreview() {
    PicItTheme {
//        RoomSettingsScreen()
    }
}
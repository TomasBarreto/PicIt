package com.example.picit

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.picit.ui.theme.PicItTheme

@Composable
fun PreviewRoomsToJoinScreen(){
    Column (
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Spacer(modifier = Modifier.height(60.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp), // Adjust padding as needed
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .weight(1f)
            ) {
                Button(
                    colors = ButtonDefaults.buttonColors(Color.Transparent),
                    onClick = { /*TODO: go to UserRoomsScreen*/ },
                ) {
                    Icon(
                        Icons.Filled.ArrowBack,
                        contentDescription = null,
                        tint =Color.Black,
                        modifier = Modifier
                            .size(32.dp)
                    )
                }
                Text(
                    text = "Rooms to Join",
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }


        }

        Spacer(modifier = Modifier.height(32.dp))

        // Public rooms in the system, get from databse
        var nRooms = 2;
        for (i in 1..nRooms){
            var roomName = "Room Name"
            var roomMaxSize = 10
            var usersInRoom = 9
            var gameType = "RePic" //
            var maxDailyChallenges = 30
            var challengesDone = 13
            Spacer(modifier = Modifier.height(16.dp))
            RoomPreview(roomName, roomMaxSize, usersInRoom,gameType, maxDailyChallenges,challengesDone)
        }

        Spacer(modifier = Modifier.weight(1f))

        Row (
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ){
            Button(onClick = { /*TODO*/ }) {
                Text(text = "Join a Private Room", fontSize = 24.sp,modifier = Modifier.padding(16.dp))
            }
        }
        Spacer(modifier = Modifier.height(32.dp))

        AppBottomMenu(inFriendScreen = false, inHomeScreen = true, inProfileScreen = false)
    }
}
@Preview(showBackground = true)
@Composable
fun PreviewRoomsToJoinScreenPreview() {
    PicItTheme {
        PreviewRoomsToJoinScreen()
    }
}
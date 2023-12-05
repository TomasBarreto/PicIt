package com.example.picit

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.picit.ui.theme.PicItTheme

@Composable
fun UserRoomsScreen(){
    Column (
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        ScreenHeader(withSettings = true, text = "Your rooms", headerFontSize = 30.sp)

        Spacer(modifier = Modifier.height(32.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ){
            SearchBar()
        }

        Spacer(modifier = Modifier.height(32.dp))
        Row(
            horizontalArrangement = Arrangement.End,
            modifier = Modifier.fillMaxWidth(0.8f)
        ){
            Button(onClick = { /*TODO go to request to join room scree*/ }) {
                Icon(Icons.Filled.Email, contentDescription = null)
            }
        }

        // Rooms of the user, get from databse
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
            horizontalArrangement = Arrangement.SpaceAround
        ){
            Button(onClick = { /*TODO*/ }) {
                Text(text = "Join Room")
            }
            Button(onClick = { /*TODO*/ }) {
                Text(text = "Create Room")
            }
        }
        Spacer(modifier = Modifier.height(32.dp))

        AppBottomMenu(selectedItem = 2)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar() {
    var text by remember { mutableStateOf("") } // TODO: check if its this

    TextField(
        value = text,
        onValueChange = { text = it },
        label = { Text("Search") },
        leadingIcon = { Icon(Icons.Filled.Search, contentDescription = null) },
        modifier = Modifier.fillMaxWidth(0.8f)
    )
}

@Preview(showBackground = true)
@Composable
fun UserRoomsScreenPreview() {
    PicItTheme {
        UserRoomsScreen()
    }
}
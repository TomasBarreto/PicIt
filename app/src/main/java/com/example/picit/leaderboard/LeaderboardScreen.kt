package com.example.picit.leaderboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.picit.entities.UserInLeaderboard
import com.example.picit.ui.theme.PicItTheme
import com.example.picit.utils.BackButton
import com.example.picit.utils.RoomHeader
import com.example.picit.utils.ScreenHeader


@Composable
fun LeaderboardScreen(
    roomName: String = "Room Name",
    leaderboard: List<UserInLeaderboard> = emptyList(),
    onClickBackButton: () -> Unit = {}
){
    Column (
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        ScreenHeader(
            text = "Leaderboard",
            headerFontSize = 28.sp,
            withBackButton = true,
            onClickBackButton = onClickBackButton
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(30.dp)
                .fillMaxHeight(0.90F),
            horizontalArrangement = Arrangement.Center
        ) {
            LeaderboardPanel(leaderboard = leaderboard)
        }


    }
}

@Composable
fun LeaderboardPanel(modifier : Modifier = Modifier, leaderboard: List<UserInLeaderboard>) {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxWidth()
            .fillMaxHeight(0.95F)
            .background(Color.LightGray)
            .padding(30.dp)
    ) {
        var position = 1;
        leaderboard.forEach { user ->
            LeaderboardRow(position.toString(), user.userName, user.points.toString())
            position = position+1;
        }
    }
}

@Composable
fun LeaderboardRow(place: String, name: String, points: String, modifier : Modifier = Modifier) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column (modifier = Modifier.padding(end = 5.dp),
            verticalArrangement = Arrangement.Center) {
            Text(text = place, fontSize = 20.sp, fontWeight = FontWeight.Bold)
        }
        Column (modifier = Modifier.padding(end = 1.dp),
            verticalArrangement = Arrangement.Center) {
            Icon(Icons.Filled.Person, contentDescription = null)
        }
        Column (modifier = Modifier.weight(0.80F),
            verticalArrangement = Arrangement.Center){
            Text(text = name, fontSize = 19.sp)
        }
        Column (modifier = Modifier.padding(end=0.dp),
            verticalArrangement = Arrangement.Center){
            Text(text = points, fontSize = 17.sp)
        }

    }
}


@Preview(showBackground = true)
@Composable
fun LeaderboardPreview() {
    PicItTheme {
        LeaderboardScreen()
    }
}
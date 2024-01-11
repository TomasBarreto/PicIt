package com.example.picit.picdesc

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.picit.R
import com.example.picit.utils.ScreenHeader
import com.example.picit.utils.TimeLeftDisplay
import com.example.picit.leaderboard.LeaderboardButton
import com.example.picit.ui.theme.PicItTheme

@Composable
fun PromptRoomVoteUserScreen(
    onClickBackButton: ()->Unit = {}
){
    Column (
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        ScreenHeader(
            withBackButton = true,
            text = "Room name",
            onClickBackButton = onClickBackButton
        )

        Spacer(modifier = Modifier.height(15.dp))
        Row (modifier = Modifier
            .fillMaxWidth()
            .height(50.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically)
        {
            Text(text="Time To Vote!", fontSize = 30.sp)
        }

        PromptDisplay("Pose with the sunset")
        Row (modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically)
        {
            Column {
                Row (modifier = Modifier.height(25.dp),
                    verticalAlignment = Alignment.Bottom){
                    Icon(Icons.Filled.Person, contentDescription = null, modifier = Modifier
                        .height(25.dp)
                        .fillMaxWidth(0.1F))
                    Text(text = "User name", modifier = Modifier
                        .height(22.dp)
                        .fillMaxWidth(0.50F))
                    Image(
                        painter = painterResource(id = R.drawable.clock),
                        contentDescription = "clock",
                        modifier = Modifier
                            .width(15.dp)
                            .fillMaxWidth(0.05F)
                    )
                    Text(text="08:00", fontSize = 12.sp)
                }
                Image(
                    painter = painterResource(id = R.drawable.imagetorepic),
                    contentDescription = "woman kissing the sunset"
                )
                Row (modifier = Modifier.height(25.dp)) {
                    Icon(Icons.Filled.LocationOn, contentDescription = null, modifier = Modifier
                        .fillMaxWidth(0.1F))
                    Text(text = "Location", modifier = Modifier
                        .height(22.dp)
                        .fillMaxWidth(0.55F))
                }
            }
        }
        Spacer(modifier = Modifier.height(8.dp))

        var myRating by remember { mutableStateOf(3) }
        RatingBar(
            currentRating = myRating,
            onRatingChanged = { myRating = it }
        )

        Spacer(modifier = Modifier.height(16.dp))

        TimeLeftDisplay("Vote","1","32")

        Spacer(modifier = Modifier.weight(1f))

        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 20.dp, top = 15.dp, bottom = 20.dp),
            horizontalArrangement = Arrangement.End) {
            LeaderboardButton()
        }

    }
}

@Composable
fun RatingBar(
    maxRating: Int = 5,
    currentRating: Int,
    onRatingChanged: (Int) -> Unit,
    starsColor: Color = Color (251, 201, 1)
) {
    Row{
        for (i in 1..maxRating) {
            Icon(
                imageVector = if (i <= currentRating) Icons.Filled.Star
                else Icons.Outlined.Star,
                contentDescription = null,
                tint = if (i <= currentRating) starsColor
                else Color.LightGray,
                modifier = Modifier
                    .padding(4.dp)
                    .size(44.dp)
                    .clickable { onRatingChanged(i) }

            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PromptRoomVoteUserScreenPreview() {
    PicItTheme {
        PromptRoomVoteUserScreen()
    }
}
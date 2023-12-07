package com.example.picit.picdesc

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.picit.utils.ScreenHeader
import com.example.picit.utils.TakePhotoButton
import com.example.picit.utils.TimeLeftDisplay
import com.example.picit.leaderboard.LeaderboardButton
import com.example.picit.ui.theme.PicItTheme

@Composable
fun PromptRoomTakePicture(
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

        Spacer(modifier = Modifier.height(80.dp))

        Row (modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
            .height(100.dp)
            .background(Color.LightGray),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically)
        {
            Column (horizontalAlignment = Alignment.CenterHorizontally){
                Text(text = "Prompt:", fontSize = 35.sp, fontWeight = FontWeight.Bold)
                Text(text = "Pose with the sunset", fontSize = 30.sp)
            }
        }

        Spacer(modifier = Modifier.height(60.dp))

        TimeLeftDisplay("Photo", 1, 20, 54)
        Spacer(modifier = Modifier.height(70.dp))

        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp),
            horizontalArrangement = Arrangement.Center) {
                TakePhotoButton(onButtonClick = {/*TODO: go to Camera*/ })
        }

        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 20.dp, bottom = 10.dp, top = 30.dp),
            horizontalArrangement = Arrangement.End) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                LeaderboardButton()
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PromptRoomTakePicturePreview() {
    PicItTheme {
        PromptRoomTakePicture()
    }
}
package com.example.picit


import androidx.compose.foundation.Image
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.picit.ui.theme.PicItTheme

@Composable
fun RepicRoomTakePicture(){
    Column (
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        ScreenHeader(text = "Room name", withButton = true)

        Spacer(modifier = Modifier.height(40.dp))

        Row (modifier = Modifier
            .fillMaxWidth()
            .height(50.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically)
        {
            Text(text="Photo To Repic:", fontSize = 30.sp)
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.imagetorepic),
                contentDescription = "woman kissing the sunset",
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        TimeLeftDisplay("Submit Photo",1,24,55)
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 20.dp, top = 65.dp, bottom = 120.dp)) {
            Column (modifier = Modifier.weight(0.70F),
                horizontalAlignment = Alignment.CenterHorizontally){
                TakePhotoButton(onButtonClick = {/*TODO: go to Camera*/ })
            }
            Column(modifier = Modifier.weight(0.30F),
                horizontalAlignment = Alignment.CenterHorizontally) {
                LeaderboardButton()
            }
        }

        AppBottomMenu(inFriendScreen = false, inHomeScreen = false, inProfileScreen = false)
    }
}


@Preview(showBackground = true)
@Composable
fun RepicRoomTakePicturePreview() {
    PicItTheme {
        RepicRoomTakePicture()
    }
}
package com.example.picit

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.picit.navigation.Screens
import com.example.picit.picdesccreateroom.ChooseGame
import com.example.picit.ui.theme.PicItTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PicItTheme {
                PicItApp()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PicItApp() {
    // A surface container using the 'background' color from the theme
    val navController = rememberNavController()
    Scaffold {
        NavHost(
            navController = navController,
            startDestination = Screens.Home.route,
            modifier = Modifier.padding(it)
        ) {
            val bottomNavigationsList = listOf(
                { navController.navigate(Screens.Friends.route){ launchSingleTop = true } },
                { navController.navigate(Screens.Home.route){ launchSingleTop = true } },
                { navController.navigate(Screens.Profile.route){ launchSingleTop = true } },
                )
            val onClickBackButton= {navController.popBackStack()}
            composable(route= Screens.Home.route){
                UserRoomsScreen(
                    bottomNavigationsList= bottomNavigationsList,
                    onClickJoinRoom = { navController.navigate(Screens.RoomsToJoin.route)},
                    onClickCreateRoom = {navController.navigate(Screens.CreateRoomChooseGame.route)},
                    onClickInvitesButton = {navController.navigate(Screens.InvitesNotifications.route)},
                    onClickSettings = {navController.navigate(Screens.Settings.route)}
                )
            }
            // To go to a friend make func (firendId) -> navigate(freindId)
            composable(route= Screens.Friends.route){
                FriendsListScreen(bottomNavigationsList= bottomNavigationsList)
            }
            composable(route = Screens.Profile.route){
                UserProfileScreen(bottomNavigationsList = bottomNavigationsList)
            }
            composable(route= Screens.RoomsToJoin.route){
                PreviewRoomsToJoinScreen(
                    onClickBackButton = {onClickBackButton()}
                )
            }
            composable(route= Screens.CreateRoomChooseGame.route){
                ChooseGame(
                    onClickBackButton = { onClickBackButton() }
                )
            }
            composable(route = Screens.InvitesNotifications.route){
                RoomInviteNotificationsScreen(
                    onClickBackButton = {onClickBackButton()}
                )
            }
            composable(route = Screens.Settings.route){
                SettingsScreen(
                    onClickBackButton = {onClickBackButton()}
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PicItTheme {
        UserRoomsScreen()
    }
}

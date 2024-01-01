package com.example.picit.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.picit.camera.CameraScreen
import com.example.picit.entities.PicDescRoom
import com.example.picit.entities.RePicRoom
import com.example.picit.entities.User
import com.example.picit.friendslist.FriendsListScreen
import com.example.picit.joinroom.PreviewRoomsToJoinScreen
import com.example.picit.joinroom.UserRoomsScreen
import com.example.picit.login.LoginScreen
import com.example.picit.login.LoginViewModel
import com.example.picit.notifications.RoomInviteNotificationsScreen
import com.example.picit.picdesc.PromptRoomTakePicture
import com.example.picit.picdesccreateroom.ChooseGameScreen
import com.example.picit.picdesccreateroom.RoomSettingsScreen
import com.example.picit.picdesccreateroom.RoomTimeSettingsPicDescScreen
import com.example.picit.picdesccreateroom.RoomTimeSettingsRepicScreen
import com.example.picit.profile.UserProfileScreen
import com.example.picit.register.RegisterScreen
import com.example.picit.repic.RepicRoomTakePicture
import com.example.picit.settings.SettingsScreen
import com.google.firebase.Firebase
import com.google.firebase.database.database
import com.google.firebase.database.getValue

private val TAG = "NavHost"

@Composable
fun PicItNavHost(navController: NavHostController, modifier: Modifier = Modifier) {
    var loginViewModel : LoginViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = Screens.Login.route,
        modifier = modifier
    ) {
        val bottomNavigationsList = listOf(
            { navController.navigate(Screens.Friends.route){ launchSingleTop = true } },
            { navController.navigate(Screens.Home.route){ launchSingleTop = true } },
            { navController.navigate(Screens.Profile.route){ launchSingleTop = true } },
        )
        val onClickBackButton = {navController.popBackStack()}
        val onClickCameraButton = {navController.navigate(Screens.Camera.route)}
        val onClickGoToRegistry = {navController.navigate(Screens.Register.route)}
        val onClickGoBackToLogin = {navController.navigate(Screens.Login.route)}
        val onClickGoToMainScreen = {navController.navigate(Screens.Home.route)}

        // done with id instead of the user, because updates with the user needed to be done with
        // listeners and that would have very complex logic
        var currentUser by mutableStateOf(User())

        composable(route= Screens.Login.route) {
            val currentUserUpdate = { user: User -> currentUser = user }

            LoginScreen(
                onClickGoToRegistry = onClickGoToRegistry,
                onClickGoToMainScreen = onClickGoToMainScreen,
                currentUserUpdate = currentUserUpdate,
                viewModel = loginViewModel,
            )
        }
        composable(route= Screens.Register.route) {
            RegisterScreen(onClickBackButton = {onClickBackButton()}, onClickGoBackToLogin = onClickGoBackToLogin)
        }
        composable(route= Screens.Home.route) {
            var currentUser = remember{
                mutableStateOf(User())
            }
            loginViewModel.findUserById(currentUserId, { user: User -> currentUser.value = user })

            UserRoomsScreen(
                bottomNavigationsList= bottomNavigationsList,
                onClickJoinRoom = { navController.navigate(Screens.RoomsToJoin.route)},
                onClickCreateRoom = {navController.navigate(Screens.CreateRoomChooseGame.route)},
                onClickInvitesButton = {navController.navigate(Screens.InvitesNotifications.route)},
                onClickSettings = {navController.navigate(Screens.Settings.route)},
                onClickRooms = {navController.navigate(Screens.RepicRoomTakePicture.route)},
                currentUserRepicRoomsIds = currentUser.value.repicRooms,
                currentUserPicDescRoomsIds = currentUser.value.picDescRooms
            )
        }
        composable(route= Screens.Friends.route){
            FriendsListScreen(bottomNavigationsList= bottomNavigationsList)
        }
        composable(route = Screens.Profile.route){

            UserProfileScreen(
                bottomNavigationsList = bottomNavigationsList,
                name=currentUser.name,
                maxPoints = currentUser.maxPoints.toString(),
                numberOfWins = currentUser.totalWins.toString(),
                maxChallengeWinStreak = currentUser.maxWinStreak.toString(),
                nPhotosTaken = currentUser.nrPhotosTaken.toString()
            )
        }
        composable(route= Screens.RoomsToJoin.route){

            // TODO : TOU AQUI --------------------
            var allRepicRooms = remember{
                mutableStateOf(emptyList<RePicRoom>())
            }
            var allPicdescRooms = remember{
                mutableStateOf(emptyList<PicDescRoom>())
            }

            // TODO: filter to only display rooms that the user is not in
            getAllRooms(
                retrieveRepicRooms = { rePicRooms ->
                    val openRooms = rePicRooms.filterNot{ it.privacy }
                    val roomsUserIsIn = currentUser.repicRooms
                    // rooms are available to join if they're public and the user isnt currently in them

                    val availableRooms = openRooms.filterNot { roomsUserIsIn.contains(it.id) }
                    allRepicRooms.value =availableRooms
                                     },
                retrievePicdescRooms = {picdescRooms ->
                    val openRooms = picdescRooms.filterNot{ it.privacy }
                    val roomsUserIsIn = currentUser.picDescRooms

                    Log.d(TAG, "openRooms: $openRooms")
                    Log.d(TAG, "roomsUserIsIn: $roomsUserIsIn")
                    // rooms are available to join if they're public and the user isnt currently in them
                    val availableRooms = openRooms.filterNot { roomsUserIsIn.contains(it.id) }
                    allPicdescRooms.value =availableRooms
                }
            )

            PreviewRoomsToJoinScreen(
                picdescRoomsAvailable = allPicdescRooms.value,
                repicRoomsAvailable = allRepicRooms.value,
                onClickBackButton = {onClickBackButton()}
            )
        }
        composable(route= Screens.CreateRoomChooseGame.route){
            ChooseGameScreen(
                onClickBackButton = { onClickBackButton() },
                onClickNextButon = {
                        gameType->
                    navController.navigate(Screens.DefineRoomSettings.route
                        .replace("{game_type}",gameType))
                }
            )
        }
        composable(route = Screens.DefineRoomSettings.route){ backStackEntry->
            val gameType = backStackEntry.arguments?.getString("game_type")
            val route = if( gameType.equals("0")) Screens.PicDescTimeSettings.route
                        else Screens.RePicTimeSettings.route

            RoomSettingsScreen(
                onClickBackButton = { onClickBackButton() },
                onClickNextButton = {
                        name, capacity, numChallenges, privacy ->
                    navController.navigate(
                        route
                            .replace("{roomName}", name)
                            .replace("{capacity}", capacity)
                            .replace("{numChallenges}", numChallenges)
                            .replace("{privacy}", privacy)
                    )
                }
            )
        }
        composable(route = Screens.PicDescTimeSettings.route) { backStackEntry->
            val roomName = backStackEntry.arguments?.getString("roomName")
            val roomCapacity = backStackEntry.arguments?.getString("capacity")
            val numChallenges = backStackEntry.arguments?.getString("numChallenges")
            val privacyTokens = backStackEntry.arguments?.getString("privacy")?.split(":")
            val privacy = privacyTokens?.get(0).toBoolean()
            val privacyCode = privacyTokens?.get(1)

            var currentUser = remember{
                mutableStateOf(User())
            }
            loginViewModel.findUserById(currentUserId, { user: User -> currentUser.value = user })

            if (roomName != null && roomCapacity != null && numChallenges != null && privacyCode != null) {
                RoomTimeSettingsPicDescScreen(
                    onClickBackButton = { onClickBackButton() },
                    roomName = roomName,
                    roomCapacity = roomCapacity,
                    numChallenges = numChallenges,
                    privacy = privacy,
                    privacyCode = privacyCode,
                    onClickGoHomeScreen = onClickGoToMainScreen,
                    currentUserId = currentUserId,
                    currentUserRooms = currentUser.value.picDescRooms
                )
            }
        }
        composable(route = Screens.RePicTimeSettings.route){ backStackEntry->
            val roomName = backStackEntry.arguments?.getString("roomName")
            val roomCapacity = backStackEntry.arguments?.getString("capacity")
            val numChallenges = backStackEntry.arguments?.getString("numChallenges")
            val privacyTokens = backStackEntry.arguments?.getString("privacy")?.split(":")
            val privacy = privacyTokens?.get(0).toBoolean()
            val privacyCode = privacyTokens?.get(1)

            var currentUser = remember{
                mutableStateOf(User())
            }
            loginViewModel.findUserById(currentUserId, { user: User -> currentUser.value = user })

            if (roomName != null && roomCapacity != null && numChallenges != null && privacyCode != null) {
                RoomTimeSettingsRepicScreen(
                    onClickBackButton = { onClickBackButton() },
                    roomName = roomName,
                    roomCapacity = roomCapacity,
                    numChallenges = numChallenges,
                    privacy = privacy,
                    privacyCode = privacyCode,
                    onClickGoHomeScreen = onClickGoToMainScreen,
                    currentUserId = currentUserId,
                    currentUserRooms = currentUser.value.repicRooms
                )
            }
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
        composable(route= Screens.Camera.route){
            CameraScreen(onClickBackButton = {onClickBackButton()})
        }
        composable(route = Screens.PromptRoomTakePicture.route){
            PromptRoomTakePicture(
                onClickBackButton = {onClickBackButton()},
                onClickCameraButton = onClickCameraButton
            )
        }
        composable(route = Screens.RepicRoomTakePicture.route){
            RepicRoomTakePicture(
                onClickBackButton = {onClickBackButton()},
                onClickCameraButton = onClickCameraButton
            )
        }
    }

}

// TODO: funcoes do firebase numa classe aparte? incluindo o findUserById
fun getAllRooms(
    retrievePicdescRooms: (List<PicDescRoom>) -> Unit,
    retrieveRepicRooms: (List<RePicRoom>) -> Unit,
//    retrievePicDescRooms: Any
) {
    val db = Firebase.database
    val repicRoomsRef = db.getReference("repicRooms")
    val picdescRoomsRef = db.getReference("picDescRooms")
    repicRoomsRef.get().addOnSuccessListener { repicRoomsListSnapshot ->
        var repicRooms = mutableListOf<RePicRoom>()
        for(roomSnapshtop in repicRoomsListSnapshot.children){
            val repicRoom = roomSnapshtop.getValue<RePicRoom>() // doesnt have the id

            if(repicRoom != null){
                val roomId = roomSnapshtop.key.toString()
                repicRoom.id = roomId
                repicRooms.add(repicRoom)
            }

        }
        Log.d("firebase", "ALL REPIC ROOMS: $repicRooms")
        retrieveRepicRooms(repicRooms)

    }.addOnFailureListener{
        Log.e("firebase", "Error getting data", it)
    }

    picdescRoomsRef.get().addOnSuccessListener { picdescRoomsListSnapshot ->
        var picDescRooms = mutableListOf<PicDescRoom>()
        for(roomSnapshtop in picdescRoomsListSnapshot.children){
            val picdescRoom = roomSnapshtop.getValue<PicDescRoom>() // doesnt have the id

            if(picdescRoom != null){
                val roomId = roomSnapshtop.key.toString()
                picdescRoom.id=roomId
                picDescRooms.add(picdescRoom)
            }

        }
        Log.d("firebase", "ALL PICDESC ROOMS: $picDescRooms")
        retrievePicdescRooms(picDescRooms)

    }.addOnFailureListener{
        Log.e("firebase", "Error getting data", it)
    }
}



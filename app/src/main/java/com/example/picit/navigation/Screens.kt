package com.example.picit.navigation

sealed class Screens(val route:String){
    object Login: Screens("login")
    object Register: Screens("register")
    object Home: Screens("home")
    object Friends: Screens("friends")
    object Profile: Screens("profile")
    object RoomsToJoin: Screens("rooms_to_join")
    object JoinRepicRoom : Screens("join_repic_room/{room_id}")
    object JoinPicDescRoom : Screens("join_picdesc_room/{room_id}")
    object CreateRoomChooseGame: Screens("create_room_choose_game")
    object DefineRoomSettings: Screens("define_room_settings/{game_type}")
    object PicDescTimeSettings: Screens("picdesc_time_settings/{roomName}/{capacity}/{numChallenges}/{privacy}")
    object SubmitPhotoDescription: Screens("picdesc_time_settings/{room_id}")
    object RePicTimeSettings: Screens("repic_time_settings/{roomName}/{capacity}/{numChallenges}/{privacy}")
    object InvitesNotifications: Screens("invite_notifications")
    object Settings: Screens("settings")
    object PicDescCamera: Screens("picdesc_camera")
    object RePicCamera: Screens("repic_camera")
    object PicDescRoomScreen: Screens("picdesc_room/{room_id}")
    object RepicRoomScreen: Screens("repic_room/{room_id}")
}

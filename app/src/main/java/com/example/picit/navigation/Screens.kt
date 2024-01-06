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
    object RePicTimeSettings: Screens("repic_time_settings/{roomName}/{capacity}/{numChallenges}/{privacy}")
    object InvitesNotifications: Screens("invite_notifications")
    object Settings: Screens("settings")
    object Camera: Screens("camera")
    object PromptRoomTakePicture: Screens("prompt_room_take_picture/{room_id}")
    object PromptRoomVoteLeader: Screens("prompt_room_vote_leader/{room_id}")
    object RepicRoomTakePicture: Screens("repic_room_take_picture/{room_id}")
}

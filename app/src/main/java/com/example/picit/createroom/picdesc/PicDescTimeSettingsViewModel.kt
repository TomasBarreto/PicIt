package com.example.picit.createroom.picdesc

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.picit.entities.PicDescRoom
import com.google.firebase.Firebase
import com.google.firebase.database.database

class PicDescTimeSettingsViewModel: ViewModel() {

    fun registerPicDescRoom(
        roomName: String,
        roomCapacity: String,
        roomNumChallenges: String,
        timeDescSubmissionStart: String,
        timeDescSubmissionEnd: String,
        timePictureSubmissionStart:String,
        timePictureSubmissionEnd:String,
        timeWinner:String,
        onClickGoHomeScreen: ()->Unit = {},
        currentUserRooms: List<String>,
        currentUserId: String
    ) {

        val newPicDescRoom = PicDescRoom(name = roomName, maxCapacity = roomCapacity.toInt(), maxNumOfChallenges = roomNumChallenges.toInt(),
            winnerAnnouncementTime = timeWinner, photoSubmissionOpeningTime = timePictureSubmissionStart,
            photoSubmissionClosingTime = timePictureSubmissionEnd, descriptionSubmissionOpeningTime = timeDescSubmissionStart,
            descriptionSubmissionClosingTime = timeDescSubmissionEnd, currentLeader = currentUserId)

        val database = Firebase.database
        val roomRef = database.getReference("rooms").push()
        roomRef.setValue(newPicDescRoom)

        updateUserRooms(currentUserRooms, currentUserId, roomRef.key.toString())
        onClickGoHomeScreen()
    }

    private fun updateUserRooms(currentUserRooms: List<String>, currentUserId: String, roomId: String) {
        val database = Firebase.database

        var userCurrentRooms = mutableStateOf(currentUserRooms)
        userCurrentRooms.value = userCurrentRooms.value + roomId

        val roomsRef = database.getReference("users/" + currentUserId + "/rooms")
        roomsRef.setValue(userCurrentRooms.value)
    }

}
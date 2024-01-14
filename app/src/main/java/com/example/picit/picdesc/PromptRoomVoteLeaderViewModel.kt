package com.example.picit.picdesc

import androidx.lifecycle.ViewModel
import com.example.picit.entities.PicDescPhoto
import com.example.picit.entities.PicDescRoom
import com.example.picit.entities.User
import com.google.firebase.Firebase
import com.google.firebase.database.database

class PromptRoomVoteLeaderViewModel: ViewModel() {

    fun leaderVote(photo: PicDescPhoto, user: User, room:PicDescRoom, vote:Boolean){
        val db = Firebase.database
        val roomRef = db.getReference("picDescRooms/${room.id}")

        var usersThatVoteUpdated = photo.usersThatVoted.toMutableList()
        usersThatVoteUpdated.add(user.id)

        var photoUpdated = photo.copy(leaderVote = vote, usersThatVoted = usersThatVoteUpdated)

        // remove this photo to add the updated one
        val roomPhotosUpdated = room.photosSubmitted.filter{ it.userId != photo.userId }.toMutableList()
        roomPhotosUpdated.add(photoUpdated)
        val roomUpdated = room.copy(photosSubmitted = roomPhotosUpdated)

        roomRef.setValue(roomUpdated)
    }
}
package com.example.picit.friendslist

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.picit.entities.GameType
import com.example.picit.entities.JoinRoomRequest
import com.example.picit.entities.User
import com.google.firebase.Firebase
import com.google.firebase.database.database
import com.google.firebase.database.getValue

class FriendsListViewModel: ViewModel() {
    val friendsToAdd = mutableListOf<User>()
    val friendsSelected = mutableListOf<User>()

    fun getFriendsToAdd(membersOfRoomIds: List<String>) {
        var db = Firebase.database
        val usersRef = db.getReference("users")
        usersRef.get().addOnSuccessListener { usersListSnapshot ->
            for (userSnapshtop in usersListSnapshot.children) {
                val user = userSnapshtop.getValue<User>()
                if (user != null) {
                    val userId = userSnapshtop.key.toString()
                    user.id = userId
                    if (!membersOfRoomIds.contains(userId)) {
                        friendsToAdd.add(user)
                    }
                }
            }
            Log.d("firebase", "ALL USERS TO ADD: $friendsToAdd")
        }.addOnFailureListener {
            Log.e("firebase", "Error getting data", it)
        }
    }

    fun inviteToRoom(roomId: String, gameType: GameType, currentUsername: String) {
        var db = Firebase.database
        friendsSelected.forEach { user ->
            val userRef = db.getReference("users/${user.id}")
            val request = JoinRoomRequest(currentUsername, roomId, gameType)

            val updatedRequests = user.requestsToJoin.toMutableList()
            updatedRequests.add(request)

            val updatedUser = user.copy(requestsToJoin = updatedRequests)

            userRef.setValue(updatedUser)
        }
    }

}
package com.gdlkug.poke.data.repository

import com.gdlkug.poke.domain.model.User

interface UserRepository {
    fun getUserDetails(userId: Int): User
}
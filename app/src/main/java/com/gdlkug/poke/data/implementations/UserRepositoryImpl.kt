package com.gdlkug.poke.data.implementations

import com.gdlkug.poke.data.repository.UserRepository
import com.gdlkug.poke.domain.model.User

class UserRepositoryImpl() : UserRepository {
    override fun getUserDetails(userId: Int): User {
        return User(userId, "John Doe", "johndoe@example.com")
    }
}
package com.gdlkug.poke.domain.usecase

import com.gdlkug.poke.data.repository.UserRepository
import com.gdlkug.poke.domain.model.User

class GetUserDetailsUseCase(private val userRepository: UserRepository) {
    operator fun invoke(userId: Int): User {
        return userRepository.getUserDetails(userId)
    }
}
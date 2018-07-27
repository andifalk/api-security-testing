package com.example.serverapp.user.boundary

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
@Transactional(readOnly = true)
class UserService(@Autowired var userRepository: UserRepository) {

    fun findAllUsers(): List<User> = userRepository.findAll()

    fun findUserByIdentifier(identifier: UUID): User = userRepository.findOneByIdentifier(identifier)

    fun createUser(user: User): User = userRepository.save(user)

}
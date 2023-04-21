package com.cha1se.cleanacrhitecturesample.data.repository

import com.cha1se.cleanacrhitecturesample.data.storage.models.User
import com.cha1se.cleanacrhitecturesample.data.storage.UserStorage
import com.cha1se.cleanacrhitecturesample.domain.models.SaveUserNameParam
import com.cha1se.cleanacrhitecturesample.domain.models.UserName
import com.cha1se.cleanacrhitecturesample.domain.repository.UserRepository



class UserRepositoryImpl(private val userStorage: UserStorage) : UserRepository {


    override fun saveName(saveparam: SaveUserNameParam): Boolean {

        val user = mapToStorage(saveparam)

        return userStorage.save(user)
    }

    override fun getName(): UserName {

        val user = userStorage.get()

        return mapToDomain(user)
    }

    private fun mapToDomain(user: User): UserName {
        return UserName(firstName = user.firstName, lastName = user.lastName)
    }

    private fun mapToStorage(saveParam: SaveUserNameParam): User {
        return User(firstName = saveParam.name, lastName = "")
    }

}
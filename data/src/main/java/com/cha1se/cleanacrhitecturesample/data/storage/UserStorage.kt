package com.cha1se.cleanacrhitecturesample.data.storage

import com.cha1se.cleanacrhitecturesample.data.storage.models.User

interface UserStorage {

    fun save(user : User): Boolean

    fun get() : User

}